require 'net/http'
module Jekyll
  module Commands
    class Cfp < Command
      class << self
        def init_with_program(prog)
          @speakers = Speakers.new()
          prog.command(:cfp) do |c|
            c.action do |args, options|
              if("events".eql?(args[0]))
                process_event_data()
              elsif("schedule".eql?(args[0]))
                process_schedule_data()
              else
                Jekyll.logger.info("I do not understand #{args}")
              end
              persist_speakers()
            end
          end
        end
        def process_schedule_data()
           url = 'https://cfp.devnexus.com/en/dn2019/public/full_schedule.json'
            uri = URI(url)
            #schedule_data_file = File.read("_cfp/full_schedule.json")
            schedule_data_file = Net::HTTP.get(uri)
            _data_in = JSON.parse(schedule_data_file)['schedule']['conference']['days']
            #Jekyll.logger.info(_data_in)
            days = _data_in.map{ |d| {}.merge( 'index'=> d['index'], 'events' => collect_rooms(d['rooms'])) }
            Jekyll.logger.info("Gathered #{days.length} schedule days")
            _schedule_yaml = YAML.dump(days)
            File.write("_data/schedule.yml", _schedule_yaml)
            #now we can print all the people
        end
        def process_event_data()
          event_data_file = File.read("_cfp/promo_events.json")
          event_data = JSON.parse(event_data_file)
          #workshop_data = event_data['events'].select{|item|"workshop" == item['track']}
          events = event_data['events']
          Jekyll.logger.info("Reading #{events.length} events from cfp")
          process_event_collection(events);
        end
        def collect_rooms(room_event_data)
          #Jekyll.logger.info(room_event_data)
          _data = room_event_data.reduce([]){ |hash, (k,v)| hash.concat(collect_room_events(k, v)) }
          return _data
        end
        def collect_room_events(room, array_of_events)
           times =  array_of_events.map{ |e| e.select{ |k,v| ["id", "start", "track"].include?(k)}.merge("room" => room.strip) }
           process_event_collection(array_of_events)
           return times
        end
        def process_event_collection(cfp_data)
          @speakers.collect_people_events(cfp_data)
          for event in cfp_data do
              person_details = event.delete 'persons'
              process_event(event, person_details)
          end
        end
        def process_event(event, persons)
         event_header = { "id" => event["id"],
                         "title" => event["title"],
                         "layout" => "preso_details",
                        "track" => event['track']&.downcase }
         #keeping keys to person records for later rendering
         filtered_persons = filter_attributes(persons, "full_public_name", "id")
         if (persons && persons.length > 0)
            primary_person = persons[0].select{ |k,v| ["id"].include?(k)}
            event_header['primary'] = primary_person
         end
         event_header['persons'] = filtered_persons
         write_item("events", event_header, event['abstract'])
        end
        def filter_attributes(data, *props)
          Jekyll.logger.info(data)
           return data.map{|p| p.select{ |k,v| props.include?(k) }}
        end
        def write_item(collection, item, abstract)
          filename = "_#{collection}/#{item['id']}.md"
          #Jekyll.logger.info("#{filename}\n#{item}")
          #don't overwrite existing files to preserve CMS edits
          #if !(File.file?(filename))
          File.write( filename , YAML.dump(item)+"\r\n---\r\n"+abstract)
          #end
        end

        def persist_speakers()
          stored_speaker_file = File.read("_data/speakers.yml")
          stored_speaker_data = YAML.load(stored_speaker_file)
          for person in @speakers.data() do
            abstract = person[1].delete 'abstract'
            public_items = person[1].select{ |k,v| ["full_public_name", "id", "twitter_name", "events"].include?(k)}
            public_items['title'] = public_items['full_public_name']
            public_items['layout'] = "speaker_bio"
            public_items['id'] = person[0]
            write_item("speakers", public_items, abstract)
            correct_speaker_data(stored_speaker_data, person[0], person[1])
          end
          updated_speaker_yaml = YAML.dump(stored_speaker_data)
          #Jekyll.logger.info(updated_speaker_yaml)
          File.write("_data/speakers.yml", updated_speaker_yaml)
        end
        def correct_speaker_data(speaker_db, id, person_details)
          speaker_record = speaker_db.fetch(id, nil)
          if(speaker_record)
            #TODO - update any cfp sourced images?
            #Jekyll.logger.info("found")
          else
            speaker_db.store(id, @speakers.full_path_avatar(person_details))
            #Jekyll.logger.info("new>>> #{speaker_db.fetch(person_details['id'])}")
          end
        end
        def speakers(people, person_details)
          captured = people | person_details
          new_people = person_details - people
        end
      end
    end
  end
end
