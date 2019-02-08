require 'json'
event_data_file = File.read("promo_events.json")
event_data = JSON.parse(event_data_file)
events = event_data['events'].select{|item|"Workshop" == item['track']}
#events = event_data['events']
puts "Reading #{events.length} events from cfp"
for event in events do
  puts "#{event["id"]},#{event["title"]}"
  for person in event["persons"] do
    puts "#{person["id"]}, #{person["full_public_name"]}, #{person["email"]}"
  end 
end
