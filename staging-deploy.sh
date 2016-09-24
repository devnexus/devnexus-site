#!/bin/bash          
echo Deploy DevNexus to Staging

cf login
cf delete devnexus
cf delete devnexus-old
cf delete-orphaned-routes
cf rename devnexus-prod devnexus-old
mvn cf:push
cf app devnexus
