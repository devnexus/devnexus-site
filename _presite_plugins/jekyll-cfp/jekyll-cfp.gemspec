# coding: utf-8
lib = File.expand_path("../lib", __FILE__)
$LOAD_PATH.unshift(lib) unless $LOAD_PATH.include?(lib)
require "jekyll/cfp/version"

Gem::Specification.new do |spec|
  spec.name          = "jekyll-cfp"
  spec.version       = Jekyll::Cfp::VERSION
  spec.authors       = ["lauramoore"]
  spec.email         = ["laura.moore@rightbox.com"]

  spec.summary       = %q{handle converting cfp data to jekyll.}
  spec.description   = %q{read json data into pages.}
  spec.homepage      = "https://gihub.com/lauramoore/devnexus-site"
  spec.license       = "MIT"

  # Prevent pushing this gem to RubyGems.org. To allow pushes either set the 'allowed_push_host'
  # to allow pushing to a single host or delete this section to allow pushing to any host.
  if spec.respond_to?(:metadata)
    spec.metadata["allowed_push_host"] = "TODO: Set to 'http://mygemserver.com'"
  else
    raise "RubyGems 2.0 or newer is required to protect against " \
      "public gem pushes."
  end

  spec.files         = "lib/**"
  spec.bindir        = "exe"
  spec.executables   = spec.files.grep(%r{^exe/}) { |f| File.basename(f) }
  spec.require_paths = ["lib"]

  spec.add_dependency "jekyll", "~> 3.0"
  spec.add_development_dependency "bundler", "~> 1.15"
  spec.add_development_dependency "rake", "~> 10.0"
  spec.add_development_dependency "minitest", "~> 5.11"
end
