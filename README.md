# localstack-dynamodb-training

AWS DynamoDB training with LocalStack and Spring Boot


## Prerequisite

* IntelliJ IDEA

* awscli-local
https://github.com/localstack/awscli-local


## Workflow

### Spring initializer

https://start.spring.io/

selected

* Project
  * Gradle - Groovy
* Language
  * Java
* Spring Boot
  * 3.2.1
* Packaging
  * Jar
* Java
  * 17
* Dependencies
  * Lombok
  * Spring Web
  * Docker Compose Support


## Note

### RDS vs DDB

| Requirement | RDS | DynamoDB | 備考 |
| ---- | ---- | ---- | ---- |
| massive volume data | ○ | ○ | |
| database transaction support/data consistency | ◎ | ○ | |
| complex query and data join | ○ | △ | |
| read performance | △ | ○ | |
| write performance | △ | ○ | |
| stability, availability | ○ | ◎ | |
| ease of management | ○ | ○ | |
| ease of analytics | ○ | △ | |
| ease of dvelopment | ◎ | ○ | |
| schema flexibility | △ | ○ | |
| cost | ○ | △ | 従量課金 + リクエスト課金。DDBは実はリクエスト課金が高いので、バッチや順アーカイブ向き |


### Sample curl

```sh
# Get a record in DDB
curl "localhost:8080/v1/user/1"

# Delete a record in DDB
curl -X PUT "localhost:8080/v1/user/1?userName=name1"

# Update a record in DDB
curl -X POST "localhost:8080/v1/user" \
-H "Content-Type: application/json" \
-d '{"userName": "username3", "birthday": "20001010"}'
```

### Dynamo Admin (DDB data viewer)

http://localhost:8001/


## Reference

* Spring Boot Reference Documentation > docker compose support
https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#features.docker-compose

* Version and name top-level elements | Docker Docs
https://docs.docker.com/compose/compose-file/04-version-and-name/

* DynamoDBのキー・インデックスについてまとめてみた #DynamoDB - Qiita
https://qiita.com/shibataka000/items/e3f3792201d6fcc397fd

* localstack/localstack - Docker Image | Docker Hub
https://hub.docker.com/r/localstack/localstack

* LocalStack Configuration | Docs > DYNAMODB_SHARE_DB
https://docs.localstack.cloud/references/configuration/#:~:text=DYNAMODB_SHARE_DB
