#!/bin/bash

rm $(pwd)/.git/hooks/pre-push
rm $(pwd)/.git/hooks/post-commit

ln -s $(pwd)/custom_hooks/pre-push.sh .git/hooks/pre-push
ln -s $(pwd)/custom_hooks/post-commit.sh .git/hooks/post-commit
