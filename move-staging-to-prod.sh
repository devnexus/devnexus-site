#!/bin/bash          
echo Moving Staging to Prod

cf rename devnexus devnexus-prod
cf map-route   devnexus-prod devnexus.com
cf map-route   devnexus-prod devnexus.com --hostname www
cf map-route   devnexus-prod devnexus.org
cf map-route   devnexus-prod devnexus.org --hostname www
cf map-route   devnexus-prod cfapps.io --hostname devnexus
cf unmap-route devnexus-prod cfapps.io --hostname devnexus-staging

cf unmap-route devnexus-old devnexus.com
cf unmap-route devnexus-old devnexus.org
cf unmap-route devnexus-old devnexus.com --hostname www
cf unmap-route devnexus-old devnexus.org --hostname www
cf unmap-route devnexus-old cfapps.io    --hostname devnexus

cf app devnexus-prod
