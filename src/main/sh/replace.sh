#!/usr/bin/env bash

shopt -s nullglob
shopt -s globstar
setopt extended_glob

set -eo pipefail

sp=$(dirname "$0")

bench_file=$1

echo "replacing blocks for: ${bench_file}"
pwd
set

echo "dry run=$DRY_RUN"

for md_file in **/*.md; do
  echo "replacing blocks for $bench_file in $md_file"
  "$sp"/replace_code_blocks.sh "$bench_file" "$md_file"
done
