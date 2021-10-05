#!/usr/bin/env bash

set -eo pipefail
sp=$(dirname "$0")

bench_file=$1
md_file=$2

echo "replacing blocks for $bench_file in $md_file"

bench_file_name=${bench_file##*/}

echo bench_file_name=$bench_file_name

# Handle the different ways of running `sed` without generating a backup file based on OS
# - GNU sed (Linux) uses `-i`
# - BSD sed (macOS) uses `-i ''`
SED_OPTIONS=(-i -e)
case "$(uname)" in
Darwin*) SED_OPTIONS=(-i "" -e) ;;
esac

function replace() {
  sed -E "${SED_OPTIONS[@]}" '
/```bench::'"$1"'/{
  :b
  /```$/!{N;bb
  }
  s/'$bench_file_name'.*```/'$bench_file_name'xxx```/
}' "$md_file"
}

replace $bench_file_name

echo replaced file:
cat "$md_file"
