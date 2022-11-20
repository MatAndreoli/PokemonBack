#!/bin/bash

echo "Updating version"

CWD=$(pwd)
MAIN_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
gitdir="$(git rev-parse --git-dir)"
hook="$gitdir/hooks/post-commit"

cd "$MAIN_DIR"/../.. || return

python3 update_version.py
if [ $? -eq 0 ]; then
  [ -x $hook ] && chmod -x $hook
  git add .
  git commit -m "Version Update by post-commit"
  chmod +x $hook
  exit 0
elif [ $? -ne 0 ]; then
  echo "####################################"
  echo "    Error while updating version    "
  echo "####################################"
  cd "$CWD" || return
  exit 1
fi

cd "$CWD" || return
