Нужно создать RESTful сервис по работе интернет магазина. Каждое действие пользователя должно быть сохранено в файл в виде:
время - название метода, имя юзера.
07/19/2022 16:47:23 - Igor, register

Методы
Заказ
1. Сделать
2. Посмотреть статус

Юзер
1. Регистрация
2. Логин

Валидация
Сделать заказ могут только зарегистрированные люди. Для этого они должны залогинится и получить в ответ токен. Токен использовать как ключ для аутентификации.

Сущности
1. Order: id, description,total price, items
2. Item: id, name, price
3. User: id, name