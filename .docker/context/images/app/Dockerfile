# Используем образ OpenJDK в качестве базового образа
FROM maven:3.8.7-openjdk-18-slim AS build
# Создаем каталог приложения
RUN mkdir /app
# Устанавливаем директорию приложения
WORKDIR /app
# Копируем только pom.xml и устанавливаем зависимости
COPY pom.xml .
RUN mvn dependency:resolve
# Копируем файлы проекта в контейнер
COPY . .
# Собираем приложение
RUN mvn package -DskipTests

# Копируем `JAR` файл собранного приложения в контейнер
# Создаем финальный образ с Java
FROM openjdk:18-jdk-alpine3.14
COPY --from=build /app/target/BankProj.jar /app/app.jar
# Устанавливаем директорию приложения
WORKDIR /app
# Запускаем приложение при старте контейнера
CMD ["java", "-jar", "app.jar"]
# Открываем порт, который будет использоваться для обращения к приложению
EXPOSE 8080