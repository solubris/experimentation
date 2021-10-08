#!/usr/bin/env bash

shopt -s nullglob
shopt -s globstar
setopt extended_glob

set -eo pipefail

sp=$(dirname "$0")

bench_files_dir=$1

echo "combining files in $bench_files_dir"
for bench_file in $bench_files_dir/**/*.txt; do
  echo "combining $bench_file into $bench_files_dir/all.txt"
  cat $bench_file >> $bench_files_dir/all.txt
  rm -rf $bench_file
done

echo "resulting file:"
cat $bench_files_dir/all.txt
