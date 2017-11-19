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
              for event in workshop_data do
                  process_event(event)
              end
            end
          end
        end
        def process_event(event)
         event['layout'] = 'preso_details'
         filtered_persons = filter_attributes(event['persons'], "full_public_name", "abstract", "avatar_path")
         full_path_persons = filtered_persons.map{|p_item| full_path_avatar(p_item)}

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
