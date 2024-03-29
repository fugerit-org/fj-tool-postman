# **Fugerit tool postman**

[![Keep a Changelog v1.1.0 badge](https://img.shields.io/badge/changelog-Keep%20a%20Changelog%20v1.1.0-%23E05735)](CHANGELOG.md)
[![Maven Central](https://img.shields.io/maven-central/v/org.fugerit.java/fj-tool-postman.svg)](https://mvnrepository.com/artifact/org.fugerit.java/fj-tool-postman)
[![license](https://img.shields.io/badge/License-Apache%20License%202.0-teal.svg)](https://opensource.org/licenses/Apache-2.0)
[![code of conduct](https://img.shields.io/badge/conduct-Contributor%20Covenant-purple.svg)](https://github.com/fugerit-org/fj-universe/blob/main/CODE_OF_CONDUCT.md)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=fugerit-org_fj-tool-postman&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=fugerit-org_fj-tool-postman)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=fugerit-org_fj-tool-postman&metric=coverage)](https://sonarcloud.io/summary/new_code?id=fugerit-org_fj-tool-postman)

[![Java runtime version](https://img.shields.io/badge/run%20on-java%208+-%23113366.svg?style=for-the-badge&logo=openjdk&logoColor=white)](https://universe.fugerit.org/src/docs/versions/java11.html)
[![Java build version](https://img.shields.io/badge/build%20on-java%2011+-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)](https://universe.fugerit.org/src/docs/versions/java11.html)
[![Apache Maven](https://img.shields.io/badge/Apache%20Maven-3.9.0+-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)](https://universe.fugerit.org/src/docs/versions/maven3_9.html)

Sample utilities for Postman.

## **Quickstart**

Example :

```shell
java -jar target/dist-fj-tool-postman-*.jar \
 --fun CollectionEnvMerge\
 --collection-path src/test/resources/sample/fj-doc-playground-quarkus.postman_collection.json\
 --environment-path src/test/resources/sample/fugerit_ampere04.postman_environment.json\
 --output-path target/fj-doc-playground-quarkus-sample.postman_collection.json
```

## **parameter help**

| **name**           | **required** | **default** | **description**                       | **since** | **info**                                                                                        |
|--------------------|--------------|-------------|---------------------------------------|-----------|-------------------------------------------------------------------------------------------------|
| `fun`              | `true`       | none        | The function to activate.             | 1.0.0     | possible values are : CollectionEnvMerge (Merge a collection and an environment variables file) |
| `collection-path`  | `true`       | none        | Path of postman collection to merge.  | 1.0.0     | Required only for fun 'CollectionEnvMerge'.                                                     |
| `environment-path` | `true`       | none        | Path of postman environment to merge. | 1.0.0     | Required only for fun 'CollectionEnvMerge'.                                                     |
| `output-path`      | `true`       | none        | Path of the merged collection.        | 1.0.0     | Required only for fun 'CollectionEnvMerge'.                                                     |
