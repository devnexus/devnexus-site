class Speakers
  def initialize(img_host="https://cfp.devnexus.com")
      @img_host = img_host
  end
  def full_path_avatar(person)
      img_path = person['avatar_path']
      person['avatar_path'] = "#{@img_host}#{img_path}"
      return person
  end
end
