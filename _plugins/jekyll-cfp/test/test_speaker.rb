require 'minitest/autorun'
require 'jekyll/commands/speakers'

class SpeakersTest < Minitest::Test
  def test_has_default_avatar_path
     speakers = Speakers.new()
     person = {}
     speakers.full_path_avatar(person)
     assert_equal "https://cfp.devnexus.com", person['avatar_path']
  end
  def test_collects_1_event_1_speaker
     speakers = Speakers.new()
     event_arr = [{ "id" => 1,
                    "persons" => [
                       { "id" => 11, "abstract" => "foo", "img" => "/foo", "full_public_name" => "Foo A"}
                    ]
                  }]
     results = speakers.people_from_events(event_arr)
     assert_equal 1, results.size()
     assert_equal 11, results[0]["id"]
  end
  def test_1_event_2_speakers
     speakers = Speakers.new()
     event_arr = [{ "id" => 1,
                    "persons" => [
                       { "id" => 11, "abstract" => "foo", "img" => "/foo", "full_public_name" => "Foo A"},
                       { "id" => 12, "abstract" => "bar", "img" => "/bar", "full_public_name" => "Bar B"}
                    ]
                  }]
     results = speakers.people_from_events(event_arr)
     assert_equal 2, results.size()
     assert_equal 12, results[1]["id"]
  end
  def test_2_events_2_speakers_1_common
     speakers = Speakers.new()
     event_arr = [{ "id" => 1,
                    "persons" => [
                       { "id" => 11, "abstract" => "foo", "img" => "/foo", "full_public_name" => "Foo A"},
                       { "id" => 12, "abstract" => "bar", "img" => "/bar", "full_public_name" => "Bar B"}
                    ]
                  },
                  {"id" => 2,
                      "persons" => [
                          { "id" => 14, "abstract" => "Boo", "img" => "/boo", "full_public_name" => "D Boo"},
                          { "id" => 13, "abstract" => "baz", "img" => "/baz", "full_public_name" => "C Baz"}
                        ]
                    }]
     results = speakers.people_from_events(event_arr)
     assert_equal 4, results.size()
  end
  def test_2_people_to_1
    speakers = Speakers.new()
    people = [
      { "id" => 11, "abstract" => "foo", "img" => "/foo", "full_public_name" => "Foo A", "events" => [1]},
      { "id" => 11, "abstract" => "foo", "img" => "/foo", "full_public_name" => "Foo A", "events" => [2]}
    ]
    results = speakers.organize_people(people)
    assert_equal 1, results.size()
    p_eleven = results[11]
    assert_equal([1, 2], p_eleven["events"])
  end
  def test_combine_events_to_persons
    speakers = Speakers.new()
    event_arr = [{ "id" => 1,
                   "persons" => [
                      { "id" => 11, "abstract" => "foo", "img" => "/foo", "full_public_name" => "Foo A"}
                   ]
                 },
                 {"id" => 2,
                     "persons" => [
                         { "id" => 11, "abstract" => "foo", "img" => "/foo", "full_public_name" => "Foo A"},
                       ]
                  }]
    speakers.collect_people_events(event_arr)
    results = speakers.data()
    assert_equal 1, results.size()
    p_eleven = results[11]
    assert_equal([1, 2], p_eleven["events"])
    event_arr_next = [{ "id" => 3,
                   "persons" => [
                      { "id" => 12, "abstract" => "bar", "img" => "/bar", "full_public_name" => "Bar B"}
                   ]
                 },
                 {"id" => 4,
                     "persons" => [
                         { "id" => 11, "abstract" => "foo", "img" => "/foo", "full_public_name" => "Foo A"},
                       ]
                  }]
    speakers.collect_people_events(event_arr_next)
    results_next = speakers.data()
    assert_equal 2, results_next.size()
    p_eleven_next = results[11]
    assert_equal([1, 2, 4], p_eleven_next["events"])
  end
end
