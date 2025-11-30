require 'json'
require 'net/http'
require 'uri'
require 'base64'
require 'fileutils'
require 'yaml'

class CompanyLogos
  def initialize(json_file_path = '../_data/speakers.json', output_file_path = '../_data/logos.yml')
    @json_file_path = json_file_path
    @output_file_path = output_file_path
    @image_cache = {}
  end

  def fetch_and_encode_image(image_url)
    return @image_cache[image_url] if @image_cache.key?(image_url)

    begin
      uri = URI.parse(image_url)
      response = Net::HTTP.get_response(uri)

      unless response.is_a?(Net::HTTPSuccess)
        puts "Failed to fetch image from #{image_url}: #{response.code} #{response.message}"
        return nil
      end

      image_data = response.body
      ext = File.extname(uri.path.split('?').first)
      mime_type = case ext.downcase
                  when '.jpg', '.jpeg' then 'image/jpeg'
                  when '.png' then 'image/png'
                  when '.gif' then 'image/gif'
                  when '.svg' then 'image/svg+xml'
                  else 'application/octet-stream'
                  end

      base64_image = Base64.strict_encode64(image_data)
      data_uri = "data:#{mime_type};base64,#{base64_image}"
      @image_cache[image_url] = data_uri
      data_uri
    rescue StandardError => e
      puts "Error fetching or encoding image #{image_url}: #{e.message}"
      nil
    end
  end

  def process_logos
    json_data = File.read(@json_file_path)
    speakers = JSON.parse(json_data)
    
    company_logos = {}
    
    speakers.each do |speaker|
      next unless speaker['questionAnswers']
      
      image_url = nil
      company_name = nil
      
      speaker['questionAnswers'].each do |qa|
        if qa['question'] == 'Company Logo' && qa['answer'] 
          image_url = qa['answer']
        end
        if qa['question'] == 'Company' && qa['answer']  
          company_name = qa['answer']
        end
      end
      
      # Only process first occurrence of each company
      if company_name && image_url && !company_logos.key?(company_name)
        # base64_data = fetch_and_encode_image(image_url)
        # company_logos[company_name] = base64_data if base64_data
        company_logos[company_name] = image_url 
      end
    end
    
    # Ensure _data directory exists
    FileUtils.mkdir_p(File.dirname(@output_file_path))
    
    # Write to YAML file
    File.write(@output_file_path, company_logos.to_yaml)
    
    puts "Processed #{company_logos.size} unique company logos to #{@output_file_path}"
    company_logos
  end
end

if __FILE__ == $0
  processor = CompanyLogos.new
  processor.process_logos
end
