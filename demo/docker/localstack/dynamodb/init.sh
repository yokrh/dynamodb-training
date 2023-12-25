#!/usr/bin/env bash
# Don't forget chmod +x *.sh

echo "-- ddb init start --"

USER_TABLE_NAME=User
USER_TABLE_ATTRIBUTE_DEFINITIONS='[
  {"AttributeName": "userId", "AttributeType": "N"},
  {"AttributeName": "userName", "AttributeType": "S"}
]'
USER_TABLE_KEY_SCHEMA='[
  {"AttributeName": "userId", "KeyType": "HASH"},
  {"AttributeName": "userName", "KeyType": "RANGE"}
]'
USER_TABLE_PROVISIONED_THROUGHPUT='{
  "ReadCapacityUnits": 10,
  "WriteCapacityUnits": 10
}'
USER_TABLE_GSI='{
  "IndexName": "UserGSI",
  "KeySchema": [
    {"AttributeName": "userName", "KeyType": "HASH"},
    {"AttributeName": "userId", "KeyType": "RANGE"}
  ],
  "Projection": {"ProjectionType": "ALL"},
  "ProvisionedThroughput": {
    "ReadCapacityUnits": 10,
    "WriteCapacityUnits": 10
  }
}'

awslocal dynamodb create-table --table-name "${USER_TABLE_NAME}" \
--attribute-definitions "${USER_TABLE_ATTRIBUTE_DEFINITIONS}" \
--key-schema "${USER_TABLE_KEY_SCHEMA}" \
--provisioned-throughput "${USER_TABLE_PROVISIONED_THROUGHPUT}" \
--global-secondary-index "${USER_TABLE_GSI}"

source "/home/localstack/data/dynamodb/data_setup.sh"

# aws dynamodb list-tables --endpoint-url http://localhost:4566
echo "-- ddb init end --"
