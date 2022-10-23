docker-compose down
gradle clean build -x test
gradle bootJar
docker-compose build
docker-compose up
