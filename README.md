#devnexus-site

This project is the jekyll static site generator for for the [Devnexus Conference](https://devnexus.com) held every
year in Atlanta, GA.

To run the site and see data on standard port 4000
'''
> bundle exec jekyll s


This project includes a Jekyll plugin in order to process CFP data into the site collections
To see options for how to process and run:
'''
> bundle exec jekyll cfp --help


Notes:
* prior to schedule, CFP data is protected by login.  Download and use --file option early on.

Steps to archive a year
* TODO: remember how to do this!

Steps to Prepare a new year
* Update the _presite_plugins/jekyll_cfp/lib/jekyll/commands/cfp.rb file with CFP uris
* adjust the _data/tracks.yml for any changes


