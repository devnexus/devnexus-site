require 'base64'
require 'open-uri'

# converts attached PNGs into base64 strings

# Eg
# <img src="my.png" /> to <img src="data:image/png;base64,..." />

class To64Html

  def initialize(path)
    @path = path
  end

  def self.img64(path)
    File.open(path, 'rb') do |img|
      'data:image/png;base64,' + Base64.strict_encode64(img.read)
    end
  end

  def self.downloadImg(name="speaker.png", uri)
    unless (uri.start_with?("http"))
      To64Html::downloadImg(name, "https://devnexus.com/#{uri}");
    else
      File.open(name, 'wb') do |fo|
        fo.write open(uri).read
      end
    end
  end

  def html(name='based64.html')
    File.open(@path, 'r') do |h|
      html = h.read.gsub(/src="(.*)"/) do |g|
        To64Html::downloadImg("_tmp.png", $1)
        "src='#{To64Html::img64("./_tmp.png")}'"
      end

      IO.write(name, html)

      p html
    end
  end

end

# Single PNG to base64 string
# p To64Html::img64('r_files/c264ce0e71c20d0eb31686641f83deda.png')

if ARGV.length > 0
  #ARGV.each do |a| puts a end
  ARGV.each do |a| To64Html.new(a).html(a) end
end
