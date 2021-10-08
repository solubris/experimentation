#!/usr/bin/env bash

shopt -s nullglob
shopt -s globstar
setopt extended_glob

set -eo pipefail

sp=$(dirname "$0")

SED_OPTIONS=(-i)
case "$(uname)" in
Darwin*) SED_OPTIONS=(-i "") ;;
esac

bench_files_dir=$1

echo "combining files in $bench_files_dir"
for bench_file in $bench_files_dir/**/*.txt; do
  echo "combining $bench_file into $bench_files_dir/all.txt"
  bench_file_dir=${bench_file%/*} # strip filename
  bench_file_dir=${bench_file_dir##*/}  # strip parent dirs
  if [ ${#bench_file_dir} -lt 6 ]; then # add padding to ensure column alignment
    bench_file_dir="$bench_file_dir "
  fi
  echo "bench_file_dir=$bench_file_dir"

  sed -E "${SED_OPTIONS[@]}" -e "1s/^/JDK    /" -e '2,$s/^/'"$bench_file_dir"' /' "$bench_file"
  cat $bench_file >> $bench_files_dir/all.txt
done

echo "resulting file:"
cat $bench_files_dir/all.txt
