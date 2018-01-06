module Jekyll
  module Commands
    class Cfp < Command
      class << self
        def init_with_program(prog)
          prog.command(:cfp) do |c|
            c.action do |args, options|
              if("events".eql?(args[0]))
                process_event_data()
              elsif("schedule".eql?(args[0]))
                process_schedule_data()
              else
                Jekyll.logger.info("I do not understand #{args}")
              end
            end
          end
        end
        def process_schedule_data()
            Jekyll.logger.info("todo")
        end
        def process_event_data()
          event_data_file = File.read("_cfp/promo_events.json")
          event_data = JSON.parse(event_data_file)
          #workshop_data = event_data['events'].select{|item|"workshop" == item['track']}
          events = event_data['events']
          Jekyll.logger.info("Reading #{events.length} events from cfp")
          stored_speaker_file = File.read("_data/speakers.yml")
          stored_speaker_data = YAML.load(stored_speaker_file)
          #Jekyll.logger.info(stored_speaker_data)
          updated_speaker_data = process_event_collection(stored_speaker_data, events);
          updated_speaker_yaml = YAML.dump(updated_speaker_data)
          #Jekyll.logger.info(updated_speaker_yaml)
          File.write("_data/speakers.yml", updated_speaker_yaml)
        end
        def speakers(people, person_details)
          captured = people | person_details
          new_people = person_details - people
        end
        def correct_speaker_data(speaker_db, person_details)
          speaker_record = speaker_db.fetch(person_details['id'], nil)
          if(speaker_record)
            #TODO - update any cfp sourced images?
            #Jekyll.logger.info("found")
          else
            new_record = { "avatar_path" => person_details['avatar_path'], "name" => person_details['full_public_name']}
            speaker_db.store(person_details['id'], new_record)
            #Jekyll.logger.info("new>>> #{speaker_db.fetch(person_details['id'])}")
          end
        end
        def process_event_collection(speaker_db, cfp_data)
          people = {};
          for event in cfp_data do
              person_details = event.delete 'persons'
              #correct relative avatar paths
              full_path_persons = person_details.map{|p_item| full_path_avatar(p_item)}
              process_event(event, full_path_persons)
              event_link = event.select{ |k,v| ["id", "title"].include?(k)}
              for person in full_path_persons do
                person_record = people.fetch(person['id'], person)
                person_events = person_record.fetch('events', [])
                person_record['events'] = person_events
                person_events.push(event_link)
                people.store(person['id'], person_record)
              end
          end
          Jekyll.logger.info("Collected #{people.values.length} presenters with events")
          for person in people.values do
            abstract = person.delete 'abstract'
            public_items = person.select{ |k,v| ["full_public_name", "id", "twitter_name", "events"].include?(k)}
            public_items['title'] = person['full_public_name']
            public_items['layout'] = "speaker_bio"
            write_item("speakers", public_items, abstract)
            correct_speaker_data(speaker_db, person)
          end
          return speaker_db
        end
        def process_event(event, persons)
         #Jekyll.logger.info(event)
         #Jekyll.logger.info(persons)
         event['layout'] = 'preso_details'
         #keeping keys to person records for later rendering
         filtered_persons = filter_attributes(persons, "full_public_name", "id")
         if (persons && persons.length > 0)
            primary_person = persons[0].select{ |k,v| ["id"].include?(k)}
            event['primary'] = primary_person
         end
         event['persons'] = filtered_persons
         abstract = event.delete('abstract')
         write_item("events", event, abstract)
        end
        def filter_attributes(data, *props)
           return data.map{|p| p.select{ |k,v| props.include?(k) }}
        end
        def write_item(collection, item, abstract)
          filename = "_#{collection}/#{item['id']}.md"
          #don't overwrite existing files to preserve CMS edits
          #if !(File.file?(filename))
          File.write( filename , YAML.dump(item)+"\r\n---\r\n"+abstract)
          #end
        end
        def full_path_avatar(person)
          img_path = person['avatar_path']
          person['avatar_path'] = "https://cfp.devnexus.com#{img_path}"
          return person
        end
      end
    end
  end
end
