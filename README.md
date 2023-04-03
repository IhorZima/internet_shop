## For implementation

Create RESTful internet-shop service. Every user action should be stored in a file with following content example:  
07/19/2022 16:47:23 - Igor, register

## Methods

Заказ

1. Create
2. Show status

### Юзер

1. Register
2. Login

## Валидация

Only registered people can make an order. To do this, they must log in and receive a token in return. Token
use as an authentication key.


## Сущности

1. Order: id, description,total price, items
2. Item: id, name, price
3. User: id, name

## TODO`s:

- [ ] Create test for logging file path bean
- [ ] Combine two controllers into one `OrderController`
