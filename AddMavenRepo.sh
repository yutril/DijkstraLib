#!/bin/sh

git add -f ./repository

# shellcheck disable=SC2006
TREE_ID=`git write-tree --prefix=repository`

git reset -- ./repository

# shellcheck disable=SC2006
COMMIT_ID=`git commit-tree -p gh-pages -m "add $1 for maven repository" "$TREE_ID"`

git update-ref refs/heads/gh-pages "$COMMIT_ID"