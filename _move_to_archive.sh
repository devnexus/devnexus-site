#!/bin/sh
read -p 'Archive Year': archiveYear
mv _site/archive/devnexus${archiveYear} archive/
mv _site/presentations/index.html  archive/devnexus${archiveYear}/presentations
mv _site/speakers/index.html  archive/devnexus${archiveYear}/speakers
rm _speakers/* _events/* _event-instructions/*