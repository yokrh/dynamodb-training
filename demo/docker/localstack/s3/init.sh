#!/usr/bin/env bash
# Don't forget chmod +x *.sh

echo "-- s3 init start --"

BUCKET_NAME="test-bucket"
DIR_NAME="test-dir"
awslocal s3 mb "s3://${BUCKET_NAME}"
awslocal s3 cp /home/localstack/data/s3/sample.json "s3://${BUCKET_NAME}/${DIR_NAME}"

# aws s3 ls --endpoint-url http://localhost:4566
# aws s3 rb "s3://${BUCKET_NAME}" --endpoint-url http://localhost:4566
echo "-- s3 init end --"
