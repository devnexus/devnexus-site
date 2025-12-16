require 'fileutils'
require 'yaml'

# Ensure the _logos directory exists
FileUtils.mkdir_p('../_logos')

# Get all image files from assets/images/scroll_logos
logo_dir = '../assets/images/scroll_logos'
image_extensions = %w[.jpg .jpeg .png .gif .svg .webp]

Dir.glob("#{logo_dir}/*").each do |image_path|
  next unless image_extensions.include?(File.extname(image_path).downcase)
  
  filename = File.basename(image_path)
  name_without_ext = File.basename(image_path, File.extname(image_path))
  
  # Create YAML front matter
  yaml_content = {
    'layout' => 'logo',
    'company' => name_without_ext.tr('_-', ' ').capitalize,
    'image' => "/#{image_path}"
  }
  
  # Create YAML file
  yaml_filename = "../_logos/#{name_without_ext}.md"
  
  File.open(yaml_filename, 'w') do |file|
    file.puts yaml_content.to_yaml
    file.puts '---'
  end
  
  puts "Created #{yaml_filename}"
end