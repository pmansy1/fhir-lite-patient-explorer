# ---- Build stage ----
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /workspace

# dependency cache
COPY pom.xml .
RUN mvn -B -e -DskipTests dependency:go-offline

# copy sources and build
COPY src ./src
RUN mvn -B -e -DskipTests package spring-boot:repackage

# ---- Run stage ----
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /workspace/target/*.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
