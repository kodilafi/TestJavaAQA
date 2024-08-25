# Тестовое задание

## API тест на сайт [Reqres](https://reqres.in/)
* [Кейс 1](src/test/java/APItestforReqres/RegisterTest/RegisterUser.java): Протестировать регистрацию пользователя в системе, необходимо создание 2 тестов: успешная регистрация с валидными данными и регистрация с ошибкой из-за отсутствия пароля и проверить, что статус-код в ответе 400.
* [Кейс 2](src/test/java/APItestforReqres/GetTest/GetUser.java): Получить список пользователей страницы и убедиться, что email пользователей имеет окончание @reqres.in.
* [Кейс 3](src/test/java/APItestforReqres/DeleteTest/DeleteUser.java): Удалить второго пользователя и проверить что статус-код 204.
* [Кейс 4](src/test/java/APItestforReqres/UpdateTest/UpdateUserData.java): Обновить информацию о пользователе методом patch и сравнить дату обновления с текущей датой в системе.

## UI тест на сайт [Demoblaze](https://www.demoblaze.com/)

Кейс 1:
* [Зарегистрироваться под любыми данными](src/test/java/UItestforDemoblaze/LogInPage.java).
* [Залогиниться с этими данными](src/test/java/UItestforDemoblaze/RegistrationPage.java).
* [Добавить в корзину по одному любому гаджету из каждой категории](src/test/java/UItestforDemoblaze/CatalogPage.java).
* [Сравнить цену из общего списка и с карточки товара](src/test/java/UItestforDemoblaze/CatalogPage.java).
* [Перейти в корзину и убедиться, что общая цена посчитана верно](src/test/java/UItestforDemoblaze/CartPage.java).
* [Оформить заказ](src/test/java/UItestforDemoblaze/CartPage.java).
* [Убедиться что дата в конце заказа совпадает с датой в системе](src/test/java/UItestforDemoblaze/CartPage.java).


# Стек используемых технологий
* Selenium
* RestAssured
* Lombok
* AllureReport
* WebDriver
* JUnit5
* Faker
