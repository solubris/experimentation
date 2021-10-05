#!/usr/bin/env bash

set -eo pipefail
sp=$(dirname "$0")

bench_file=$1
md_file=$2

echo "replacing blocks for $bench_file in $md_file"

sed -v
