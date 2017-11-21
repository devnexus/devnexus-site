module Jekyll
  module Commands
    class Cfp < Command
      class << self
        def init_with_program(prog)
          prog.command(:cfp) do |c|
            c.action do |args, options|
              event_data_file = File.read("_cfp/promo_events.json")
              event_data = JSON.parse(event_data_file)
              workshop_data = event_data['events'].select{|item|"workshop" == item['track']}
              people = {};
              for event in workshop_data do
                  person_details = event.delete 'persons'
                  #correct relative avatar paths
                  full_path_persons = person_details.map{|p_item| full_path_avatar(p_item)}
                  process_event(event, full_path_persons)
                  for person in full_path_persons do
                    people.store(person['id'], person)
                  end
              end
              for person in people.values do
                abstract = person.delete 'abstract'
                public_items = person.select{ |k,v| ["full_public_name", "id", "avatar_path", "twitter_name"].include?(k)}
                write_item("speakers", public_items, abstract)
              end
            end
          end
        end
        def process_event(event, persons)
         event['layout'] = 'preso_details'
         #keeping keys to person records for later rendering
         filtered_persons = filter_attributes(persons, "full_public_name", "id")
         primary_person = persons[0].select{ |k,v| ["full_public_name", "id", "avatar_path"].include?(k)}
         event['primary'] = primary_person
         event['persons'] = filtered_persons
         abstract = event.delete('abstract')
         write_item("events", event, abstract)
        end
        def filter_attributes(data, *props)
           return data.map{|p| p.select{ |k,v| props.include?(k) }}
        end
        def write_item(collection, item, abstract)
          File.write( "_#{collection}/#{item['id']}.md", YAML.dump(item)+"\r\n---\r\n"+abstract)
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
