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

# multiline match to find full code block ```bench::...```
function replace() {
  sed -E "${SED_OPTIONS[@]}" '
/```bench::'"$bench_file_name"'/{
  :b
  /```$/!{N;bb
  }
  '"$1"'
}' "$md_file"
}

# remove text after start of block
# read from $bench_file and append after start of block
# append ``` to complete the block as it was removed earlier
replace \
's/(```bench::'$bench_file_name').*```/\1/;{r'$bench_file'
a\
```
}'

echo replaced file:
cat "$md_file"
