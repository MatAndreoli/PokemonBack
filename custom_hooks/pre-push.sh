#!/bin/bash

echo "Running Maven clean test for errors"

CWD=$(pwd)
MAIN_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

cd "$MAIN_DIR"/../..
mvn clean test
if [ $? -ne 0 ]; then
  echo "################################"
  echo "    Error while testing code    "
  echo "################################"
  cd "$CWD"
  exit 1
fi

cd "$CWD"
