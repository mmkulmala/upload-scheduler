# Simple file upload scheduler done with Quarkus
This is scheduled file uploader can be set to periodically upload file to server, which can then be downloaded to local computer.
For more about Quarkus see [Quarkus get started](https://quarkus.io/get-started/).

This application has application.properties file contains the following fields:

| Syntax               | Description                                              | Example                                    |
|----------------------|----------------------------------------------------------|--------------------------------------------|
| cron.upload.expr     | Cron expression                                          | * */5 * * * ?                                    |
| uploadfile.directory | What directory files goto                                | src/main/resources/out_files/              |
| uploadfile.name      | Name of file (real name also includes date to file name) | some_file.txt                              |
| uploadfile.url       | What file to upload                                      | https://standards-oui.ieee.org/oui/oui.txt |

All the fields are needed to run the application.

## How to run
This is Quarkus application and there for need either Quarkus or Docker to run. Below instructions for both ways.

## Run application with Quarkus
Running application with Quarkus can be done by typing in application root in shell:
```
quarkus dev
```
This opens Quarkus shell with different options. These options are show when running in shell. 

Typing command:
```
quarkus build
```
will build application and to run it one needs to type:
```
java -jar target/quarkus-app/quarkus-run.jar
```

## Run using docker
This information is also under docker folder. Before building the container image run:
```
./mvnw package
```
Then, build the image with:
```
docker build -f src/main/docker/Dockerfile.jvm -t quarkus/scheduler-jvm .
```
Then run the container using:
```
docker run -i --rm -p 8080:8080 quarkus/scheduler-jvm
```
If you want to include the debug port into your docker image
you will have to expose the debug port (default 5005) like this :  EXPOSE 8080 5050

Then run the container using :
```
docker run -i --rm -p 8080:8080 -p 5005:5005 -e JAVA_ENABLE_DEBUG="true" quarkus/scheduler-jvm
```