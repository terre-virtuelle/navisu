#!/bin/bash

export PGPASSWORD=admin 
psql -U admin -d s57NP5DB 

for f in *.sql
do
    psql -d s57NP5DB -f $f
done

