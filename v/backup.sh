#!/bin/env bash
# File archival
# 2021 05 13 - niranga@gmail.com
# v1.02

# Set resource URIs
base=$1
target='/root/'

tar cvfz $target"$(date '+%Y-%m-%d').tar.gz" --exclude='.[^/]*' --exclude='node_modules/*' --exclude='snap/*' --exclude='*.tar.gz' $base