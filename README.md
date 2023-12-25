# localstack-dynamodb-s3-training

AWS DynamoDB, S3 training with LocalStack and Spring Boot


## Workflow / Prerequisite

### Spring initializer

https://start.spring.io/

selected

* Project
  * Gradle - Groovy
* Language
  * Kotlin
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
| cost | ○ | ○ | 従量課金 + リクエスト課金 |


## Reference

* Spring Boot Reference Documentation > docker compose support
https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#features.docker-compose

* Version and name top-level elements | Docker Docs
https://docs.docker.com/compose/compose-file/04-version-and-name/

* DynamoDBのキー・インデックスについてまとめてみた #DynamoDB - Qiita
https://qiita.com/shibataka000/items/e3f3792201d6fcc397fd

* LocalStack Configuration | Docs > DYNAMODB_SHARE_DB
https://docs.localstack.cloud/references/configuration/#:~:text=DYNAMODB_SHARE_DB
