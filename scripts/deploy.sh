#! /usr/bin/env bash
set -x
set -e

if [[ -z $DOCKER_REGISRTY ]]; then
  DOCKER_REGISRTY=10.202.129.239:5000
fi
IMAGE_NAME=${DOCKER_REGISRTY}/tw-ms-train/user-service:${GO_PIPELINE_COUNTER}

sed -i "s#<IMAGE_NAME>#$IMAGE_NAME#g" docker-compose.yml

export RANCHER_URL=http://10.202.129.239:8080/v2-beta/projects/1a5
export RANCHER_ACCESS_KEY=B3B549F4A5D7B0B7A6B1
export RANCHER_SECRET_KEY=n2GMALmnutCu2vLcHRSn7XS9FD2Au9QtEHNycCas

rancher-compose -p mst-user-service up -d -c --upgrade