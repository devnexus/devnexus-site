require 'net/http'
module Jekyll
  module Commands
    class Cfp < Command
      class << self
        def init_with_program(prog)
          prog.syntax 
          pcmd = prog.command(:cfp) do |c|
            c.syntax "cfp <subcommand> [options]"
            c.description "ingest cfp data for site content"
            c.option 'file', '-f FILE', '--file FILE', 'local json data file instead of pulling directly from cfp'
            c.action do |args, options|
            end
          end
          pcmd.command(:events) do |e|
            url = "https://sessionize.com/api/v2/g415clv7/view/Sessions"
            e.description "process promo_events.json"
            e.action do |args, options|
               edata = (options['file']) ? read_data(options['file']) : fetch_data(url)
               write_data_json("events", edata)
               process_event_collection(edata)
            end
          end
          pcmd.command(:schedule) do |s|
            s.description "process full_schedule.json"
            s.action do |args, options|
              url = 'https://sessionize.com/api/v2/g415clv7/view/GridSmart'
              if (options['file'])
                write_data_json("schedule", read_data(options['file']) )
              else
                write_data_json("schedule", fetch_data(url))
              end
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
        def write_data_json(collection, data_in)
          #Jekyll.logger.info(data_in)
          filename = "_data/#{collection}.json"
          File.write( filename, data_in )
          Jekyll.logger.info("#{filename}  written")  
        end
        def process_event_collection( event_data )
              event_json = JSON.parse(event_data)
              #puts(event_json.first())
              events = event_json.first()["sessions"]
              #puts(events)
              Jekyll.logger.info("Reading #{events.length} events from cfp")
              for event in events do
                 write_item("events", event, "")
              end  
        end
        def write_item(collection, item, abstract)
          filename = "_#{collection}/#{item['id']}.md"
          Jekyll.logger.info("#{filename}\n#{item}")
          File.write( filename , YAML.dump(item)+"\r\n---\r\n"+abstract)
          Jekyll.logger.info("#{filename}  written")
        end
      end        
    end
  end
end
