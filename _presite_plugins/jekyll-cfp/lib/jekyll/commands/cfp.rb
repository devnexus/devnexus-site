require 'net/http'
module Jekyll
  module Commands
    class Cfp < Command
      class << self
        def init_with_program(prog)
          @speakers = Speakers.new()
          prog.syntax 
          pcmd = prog.command(:cfp) do |c|
            c.syntax "cfp <subcommand> [options]"
            c.description "ingest cfp data for site content"
            c.option 'file', '-f FILE', '--file FILE', 'local json data file instead of pulling directly from cfp'
            c.action do |args, options|
            end
          end
          pcmd.command(:events) do |e|
            url = 'https://cfp.devnexus.com/en/dn2021/public/promo_events.json'
            e.description "process promo_events.json"
            e.action do |args, options|
               if (options['file'])
                 process_event_data(read_data (options['file']) )
               else
                 process_event_data(fetch_data(url))
               end
               persist_speakers()
            end
          end
          pcmd.command(:schedule) do |s|
            s.description "process full_schedule.json"
            s.action do |args, options|
              url = 'https://cfp.devnexus.com/en/dn2021/public/full_schedule.json'
              if (options['file'])
                process_schedule_data(read_data (options['file']) )
              else
                process_schedule_data(fetch_data(url))
              end
              persist_speakers()
            end
          end  
        end
	  def fetch_data(path)
      uri = URI(path)
      return Net::HTTP.get(uri)
    end
	  def read_data(path)
	    return File.read(path)
    end
    def process_schedule_data(schedule_data)
      _data_in = JSON.parse(schedule_data)['schedule']['conference']['days']
      Jekyll.logger.info(_data_in)
      days = _data_in.map{ |d| {}.merge( 'index'=> d['index'], 'events' => collect_rooms(d['rooms'])) }
      Jekyll.logger.info("Gathered #{days.length} schedule days")
      _schedule_yaml = YAML.dump(days)
      File.write("_data/schedule.yml", _schedule_yaml)
      #now we can print all the people
    end
   def process_event_data( event_data )
	  #Jekyll.logger.info(event_data_file)
          event_data = JSON.parse(event_data)
          #workshop_data = event_data['events'].select{|item|"workshop" == item['track']}
          events = event_data['events']
	  #puts(events)
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
                        "track" => event['track']&.downcase}
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
          #Jekyll.logger.info(data)
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
          stored_speaker_data = Hash.new  
          stored_speaker_file = File.read("_data/speakers.yml") 
          if !stored_speaker_file.empty?
            stored_speaker_data = YAML.load(stored_speaker_file)
            Jekyll.logger.info("speakers file #{stored_speaker_file}")
        end   	
       Jekyll.logger.info("speakers yaml #{stored_speaker_data}")        
      for person in @speakers.data() do
            abstract = person[1].delete 'abstract'
            public_items = person[1].select{ |k,v| ["full_public_name", "id", "twitter_name", "events"].include?(k)}
            public_items['title'] = public_items['full_public_name']
            public_items['layout'] = "speaker_bio"
            public_items['id'] = person[0]
            #public_items['order'] = public_items['events'].size  #speakers with more talks first
            write_item("speakers", public_items, abstract)
            correct_speaker_data(stored_speaker_data, person[0], person[1])
            Jekyll.logger.info("added person #{person[0]}")
          end
          updated_speaker_yaml = YAML.dump(stored_speaker_data)
          Jekyll.logger.info(updated_speaker_yaml)
          File.write("_data/speakers.yml", updated_speaker_yaml)
        end
        def correct_speaker_data(speaker_db, id, person_details)
          Jekyll.logger.info("arguments: 1 #{speaker_db} 2 #{id} 3 #{person_details} ")
          if speaker_db.has_key?(id) 
             #TODO - update any cfp sourced images?
             Jekyll.logger.info("found #{id}")
          else
            speaker_db.store(id, @speakers.full_path_avatar(person_details))
            Jekyll.logger.info("new>>> #{speaker_db.fetch(id, "new not found")}")
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
