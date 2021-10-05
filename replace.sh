#!/usr/bin/env bash

set -eo pipefail

shopt -s nullglob
shopt -s globstar
setopt extended_glob

sp=$(dirname "$0")

bench_files=($*)

echo "replacing blocks for: ${bench_files[*]}"
pwd
set

echo "dry run=$DRY_RUN"

for bench_file in ${bench_files[*]}; do
  for md_file in **/*.md; do
    echo "replacing blocks for $bench_file in $md_file"
    "$sp"/replace_code_blocks.sh "$bench_file" "$md_file"
  done
done
