#!/bin/sh
export DOCKER_HOST=tcp://hadoop-2:2375
mvn docker:build