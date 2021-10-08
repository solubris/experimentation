#!/usr/bin/env bash

shopt -s nullglob
shopt -s globstar
setopt extended_glob

set -eo pipefail

sp=$(dirname "$0")

SED_OPTIONS=(-i -e)
case "$(uname)" in
Darwin*) SED_OPTIONS=(-i "" -e) ;;
esac

bench_files_dir=$1

echo "combining files in $bench_files_dir"
for bench_file in $bench_files_dir/**/*.txt; do
  echo "combining $bench_file into $bench_files_dir/all.txt"
  bench_file_dir=${bench_file%/*} # strip filename
  bench_file_dir=${bench_file_dir##*/}  # strip parent dirs
  echo "bench_file_dir=$bench_file_dir"

  sed -E "${SED_OPTIONS[@]}" "s/^/$bench_file_dir /" $bench_file
  cat $bench_file >> $bench_files_dir/all.txt
  rm -rf $bench_file
done

echo "resulting file:"
cat $bench_files_dir/all.txt
