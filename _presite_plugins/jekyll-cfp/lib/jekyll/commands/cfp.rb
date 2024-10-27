require 'net/http'
require 'fileutils'
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
            url = "https://sessionize.com/api/v2/csk3n1qx/view/Sessions"
            e.description "process promo_events.json"
            e.action do |args, options|
               edata = (options['file']) ? read_data(options['file']) : fetch_data(url)
               write_data_json("events", edata)
               process_event_collection(edata)
            end
          end
          pcmd.command(:speakers) do |e|
            url = "https://sessionize.com/api/v2/csk3n1qx/view/Speakers"
            e.description "download sessionize speaker details"
            e.action do |args, options|
               edata = (options['file']) ? read_data(options['file']) : fetch_data(url)
               write_data_json("speakers", edata)
               process_speaker_collection(edata)
            end
          end
          pcmd.command(:schedule) do |s|
            s.description "process full_schedule.json"
            s.action do |args, options|
              url = 'https://sessionize.com/api/v2/csk3n1qx/view/GridSmart'
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
        def process_speaker_collection( event_data )
          speaker_json = JSON.parse(event_data)
          Jekyll.logger.info("Reading #{speaker_json.length} speakers from cfp")
          FileUtils.rm Dir.glob("_speakers/*.md")
          for speaker in speaker_json do
             speaker["slug"] = Jekyll::Utils.slugify(speaker["fullName"])
             write_item("speakers", speaker, "")
          end  
        end
        def process_event_collection( event_data )
          event_json = JSON.parse(event_data)
          #puts(event_json[0]["sessions"])
          events = event_json[0]["sessions"]
          #puts(events)
          Jekyll.logger.info("Reading #{events.length} events from cfp")
          FileUtils.rm Dir.glob("_events/*.md")
          for event in events do
             #puts(event)
            if (! event["categories"].empty?)
               #puts(event["categories"])
               track_category = event["categories"].select{ |k,v| k["name"] == "Track" }
               format_category = event["categories"].select{ |k,v| k["name"] == "Session Format" }
               #puts("track", track_category, track_category.empty?)
               if ( ! track_category.empty? )
                # puts(track_category.first()["categoryItems"])
                 event["track"] = track_category.first()["categoryItems"].first()["name"]
                 #puts(event["track"])
               end
               if (! format_category.empty? ) 
                # puts(format_category.first()["categoryItems"])
                event["format"] = format_category.first()["categoryItems"].first()["name"]
                # puts(event["format"])
               end  
             end
             event["slug"] = Jekyll::Utils.slugify(event["title"])
             write_item("events", event, "")
          end  
        end
        def write_item(collection, item, abstract)
          filename = "_#{collection}/#{item['slug']}.md"
          #Jekyll.logger.info("#{filename}\n#{item}")
          File.write( filename , YAML.dump(item)+"\r\n---\r\n"+abstract)
          Jekyll.logger.info("#{filename}  written")
        end
      end        
    end
  end
end
