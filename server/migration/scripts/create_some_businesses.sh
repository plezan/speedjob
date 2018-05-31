#!/usr/bin/env bash

# Considering that root user has a root password
mysql -u"root" -p"root" <./db/CREATE_DEV_BUSINESSES.sql

exit