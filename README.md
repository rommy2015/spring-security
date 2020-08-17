# spring-security
 Рассматривается, как провести первоначальную настройку `Spring Security` и какие возможности для этого есть в `Spring Boot`.
Рассматривается, как закрыть наше приложение с помощью `HTTP based authentication`, 
обоъясняется что это такое, а также то, как оставить публичные точки входа (`end points`), 
такие как
`css`, `images` и `js` ресурсы доступными,
без всякой авторизации.
 Также посмотрим, какие параметры для быстрой настройки предоставляются `Spring Boot` "из коробки" 
 без дополнительного программирования.

## 1. Минимальная настройка авторизации

### Ветка : master

Рассматривается минимальная настройка.

## 2. Минимальная настройка авторизации

### Ветка : auth-types-2

 Виды авторизации.
 
## 3. Авторизация основанная на  session cookies

### Ветка : session-cookies

  Здесь рассматривается отмена авторизации, представленная "из коробки" и настраивается
  пользовательская авторизация.
  
 
## 4. Настройка пользовательской реализации интерфейса от Spring security - UserService.

### Ветка : spring-userservice

Рассматривается, как настроить свою имплементацию компонента Spring Security - UserService. 
  В качестве контейнера для хранения информации о пользователях будем использовать MongoDB. 
Также посмотрим, почему метод шифрования пароля Plain Text, 
используемый Spring Security по умолчанию, должен быть переопределен. 
 Посмотрим, как это сделать и какая имплементация на сегодняшний день наиболее предпочтительна.
 
  Сначала проверим на выдуманных данных.

### Ветка : user-service-repo

Описываем взаимодействие UserDetailsService с репозиторием Mongo. Используем Spring Data Jpa.
