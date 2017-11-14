module Jekyll
  module Commands
    class Cfp < Command
      class << self
        def init_with_program(prog)
          prog.command(:cfp) do |c|
            c.action do |args, options|
              track_data_file = File.read("_data/promo_events.json")
              event_data = JSON.parse(track_data_file)
              track_data = event_data['events'].select{|item|"workshop" == item['track']}
              Jekyll.logger.info track_data.size
            end
          end
        end
      end
    end
  end
end