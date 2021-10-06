#!/usr/bin/env bash

set -eo pipefail
sp=$(dirname "$0")

bench_file=$1
md_file=$2

echo "replacing blocks for $bench_file in $md_file"

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
/```bench::/{
  :b
  /```$/!{N;bb
  }
  '"$1"'
}' "$md_file"
}

# shellcheck disable=SC2207
benchmark_blocks=($(sed -n -E 's/```bench::(.*)/\1/p' "$md_file"))

for benchmark_block in ${benchmark_blocks[*]}; do
# remove text after start of block
# read from $bench_file and append after start of block
# append ``` to complete the block as it was removed earlier
sed -n -E "/$benchmark_block/p" $bench_file > $bench_file.block

replace \
's/(```bench::'$benchmark_block').*```/\1/;{r'$bench_file.block'
a\
```
}
'

rm -f $bench_file.block

done

echo replaced file:
cat "$md_file"
