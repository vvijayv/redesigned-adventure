# Integration testing with `TestContainers`

`TestContainers` helps create any dependent tools like, databases, message-brokers, REST backend services, as and when required to test the integration. All it requires is an environment with Docker. The images are downloaded from private image repository, or from public repo like Dockerhub.

This code was written to test spring-boot that uses Redis, Amazon S3 and Apache Kafka using TestContainers.

More details on this confluence [doc](https://fourkites.atlassian.net/wiki/spaces/RD20/pages/1691910413/Integration+testing+microservices)