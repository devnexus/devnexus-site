require 'minitest/autorun'
require 'jekyll/commands/speakers'

class SpeakersTest < Minitest::Test
  def test_hello
     speakers = Speakers.new()
     person = {}
     speakers.full_path_avatar(person)
     assert_equal "https://cfp.devnexus.com", person['avatar_path']
  end
end
