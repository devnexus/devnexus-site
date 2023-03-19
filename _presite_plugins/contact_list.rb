require 'json'
require 'net/http'
dataurl = URI('https://sessionize.com/api/v2/g415clv7/view/GridSmart')
event_data_file = Net::HTTP.get(dataurl)
event_data = JSON.parse(event_data_file)
events = event_data[0]["rooms"]
#puts "#{events}"
puts "Reading #{events.length} events from cfp"
for event in events do
  session = event["sessions"][0]
  #puts "#{session}"
  puts "#{session["id"]},#{session["title"]}, #{session["speakers"].map{ |p| p["name"]}.join(";")}"
  #for person in event["persons"] do
    #puts "#{person["id"]}, #{person["full_public_name"]}, #{person["email"]}"
  #end 
end
