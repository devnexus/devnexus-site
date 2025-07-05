require 'open-uri'
require 'base64'
require 'uri'

if ARGV.length != 1
  puts "Usage: ruby replace_all_img_srcs.rb /path/to/html/directory"
  exit 1
end

dir = ARGV[0]

Dir.glob(File.join(dir, '*.html')).each do |html_file|
  html = File.read(html_file)
  changed = false
  puts "Starting #{html_file}..."
  new_html = html.gsub(/(<img[^>]+src=")([^"]+)(")/i) do |match|
    prefix, img_url, suffix = $1, $2, $3
    begin
      # Skip if already data URI or empty
      next match if img_url.strip.empty? || img_url.start_with?('data:')

      # Add localhost if no host in URL
      uri = URI.parse(img_url)
      if !uri.host
        img_url = "http://localhost:4000#{img_url.start_with?('/') ? '' : '/'}#{img_url}"
      end

      image_data = URI.open(img_url) { |f| f.read }
      ext = File.extname(img_url.split('?').first)
      mime = case ext.downcase
             when '.jpg', '.jpeg' then 'image/jpeg'
             when '.png' then 'image/png'
             when '.gif' then 'image/gif'
             else 'application/octet-stream'
             end
      base64_image = Base64.strict_encode64(image_data)
      data_uri = "data:#{mime};base64,#{base64_image}"
      changed = true
      "#{prefix}#{data_uri}#{suffix}"
    rescue => e
      puts "Failed to process image #{img_url} in #{html_file}: #{e}"
      match
    end
  end

  if changed
    File.write(html_file, new_html)
    puts "Processed #{html_file}"
  else
    puts "No images replaced in #{html_file}"
  end
end