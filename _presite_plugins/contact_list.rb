require 'json'
require 'net/http'
dataurl = URI('https://cfp.devnexus.com/en/dn2020/public/promo_events.json')
event_data_file = Net::HTTP.get(dataurl)
event_data = JSON.parse(event_data_file)
events = event_data['events'].select{|item|"Full day Workshops (Wednesday Only)" == item['track']}
#events = event_data['events']
puts "Reading #{events.length} events from cfp"
for event in events do
  puts "#{event["id"]},#{event["title"]}, #{event["persons"].map{ |p| p["email"]}.join(";")}"
  #for person in event["persons"] do
    #puts "#{person["id"]}, #{person["full_public_name"]}, #{person["email"]}"
  #end 
end
