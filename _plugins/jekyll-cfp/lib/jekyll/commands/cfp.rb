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
                  event['layout'] = 'preso_details'
                 filtered_persons = event['persons'].map{ |p| p.select{|k,v| ["full_public_name", "abstract"].include?(k) }}
                 event['persons'] = filtered_persons
                 File.write( "_events/#{event['id']}.md", YAML.dump(event)+"\r\n---\r\n" )
              end
            end
          end
        end
      end
    end
  end
end