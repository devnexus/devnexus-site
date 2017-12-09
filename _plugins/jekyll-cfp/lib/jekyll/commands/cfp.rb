module Jekyll
  module Commands
    class Cfp < Command
      class << self
        def init_with_program(prog)
          prog.command(:cfp) do |c|
            c.action do |args, options|
              event_data_file = File.read("_cfp/promo_events.json")
              event_data = JSON.parse(event_data_file)
              #workshop_data = event_data['events'].select{|item|"workshop" == item['track']}
              events = event_data['events']
              Jekyll.logger.info("Reading #{events.length} events from cfp")
              process_event_collection(events);
            end
          end
        end
        def process_event_collection(cfp_data)
          people = {};
          for event in cfp_data do
              person_details = event.delete 'persons'
              #correct relative avatar paths
              full_path_persons = person_details.map{|p_item| full_path_avatar(p_item)}
              process_event(event, full_path_persons)
              for person in full_path_persons do
                #TODO - eventually will have more than one event per person...
                person['event'] = { :id => event['id'], :title => event['title']}
                people.store(person['id'], person)
              end
          end
          Jekyll.logger.info("Collected #{people.values.length} presenters with events")
          for person in people.values do
            abstract = person.delete 'abstract'
            public_items = person.select{ |k,v| ["full_public_name", "id", "avatar_path", "twitter_name", "event"].include?(k)}
            public_items['title'] = person['full_public_name']
            public_items['layout'] = "speaker_bio"
            write_item("speakers", public_items, abstract)
          end
        end
        def process_event(event, persons)
         #Jekyll.logger.info(event)
         #Jekyll.logger.info(persons)
         event['layout'] = 'preso_details'
         #keeping keys to person records for later rendering
         filtered_persons = filter_attributes(persons, "full_public_name", "id")
         if (persons && persons.length > 0)
            primary_person = persons[0].select{ |k,v| ["full_public_name", "id", "avatar_path"].include?(k)}
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
