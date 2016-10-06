#!/bin/bash          
echo Deploy DevNexus to Staging

cf login
cf delete devnexus
mvn cf:push
cf app devnexus
