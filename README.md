# job4j_order

## Микросервисный проект - Доставка еды "Голодный волк".
Общение происходит через брокера сообщений. Принятие ордера на заказ. Уведомление о статусе блюда.
## Микросервисы
+ [kitchen](https://github.com/ferveks3509/job4j_kitchen) - Кухня
+ [dish](https://github.com/ferveks3509/job4j_dish) - Управление блюдом
+ [notification](https://github.com/ferveks3509/job4j_notification) - Уведомления

### API orders
`POST /orders/` - создать ордер

`PUT /orders/{id}` - обновить ордер

`DELETE /orders/{id}` - удалить ордер

`GET /orders/` - загрузить все ордера

`GET /orders/{id}` - загрузить ордер по id

`GET /orders/order/{id}` - загрузить ордер с блюдом

## Технологии

+ **Java 18**
+ **Spring (Boot, Data, WEB)**
+ **kafka**
+ **PostgreSQL**
+ **Maven**
