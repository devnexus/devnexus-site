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
1.  Change and commit _config.yml so that site uses post conference header.
```
active-header: navigation-post-show.html
registration: closed
```

2. repath collection output to new archive location and run jekyll
collections:
  speakers:
    title: Speakers 2020
    output: true
    permalink: archive/devnexus2020/:collection/:path
  events:
     title: Presentations 2020
     output: true
     permalink: archive/devnexus2020/presentations/:path/
  event-instructions:
     title: Workshop Prep 2020
     output: true
     permalink: archive/devnexus2020/presentations/:path/instructions

```

2. Move generated files so that they become archive source files
* mv _site/devnexus2020  archive/
* mv _site/presentations/index.html  archive/devnexus2020/presentations
* mv _site/speakers/index.html  archive/devnexus2020/speakers

3. remove contents from _events, _speakers, _event-instruction folders
4. restore _config.yml (git checkout _config.yml )

Steps to Prepare a new year
* Update the _presite_plugins/jekyll_cfp/lib/jekyll/commands/cfp.rb file with CFP uris
* adjust the _data/tracks.yml for any changes


