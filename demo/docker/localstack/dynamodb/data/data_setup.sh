awslocal dynamodb put-item \
--table-name User \
--item '{"userId": {"N": "1"}, "userName": {"S": "name1"}, "birthday": {"S": "19991101"},
 "isDeleted": {"S": "false"} }'

awslocal dynamodb put-item \
--table-name User \
--item '{"userId": {"N": "2"}, "userName": {"S": "name2"}, "birthday": {"S": "19991201"}, "isDeleted": {"S": "true"}, "deletedAt": {"S": "1703507252178"} }'
