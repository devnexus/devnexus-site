module Jekyll
  module Commands
    class Cfp < Command
      class << self
        def init_with_program(prog)
          prog.command(:cfp) do |c|
            c.action do |args, options|
              Jekyll.logger.info "TODO CFP!"
            end
          end
        end
      end
    end
  end
end