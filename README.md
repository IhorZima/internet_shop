## For implementation

Create RESTful internet-shop service. Every user action should be stored in a file with following content example:  
07/19/2022 16:47:23 - Igor, login
07/19/2022 16:47:23 - Igor, createOrder


#### Notes
1. Simple and forward. Create every component and inject into others. 
2. Loggers. **TODO:** Complete covering with logs and MDC usage. Try both logback/log4j
3. Custom spring bean post processor (OR other AOP implementation)
   * Create custom annotation
   * Create BeanPostProcessor for processing custom annotation for logging
4. RequestContext
## Methods

Order

1. Create
2. Show status

### User

1. Register
2. Login

## Validation

Only registered people can make an order. To do this, they must log in and receive a token in return. Token
use as an authentication key.


## Entities

1. Order: id, description,total price, items
2. Item: id, name, price
3. User: id, name

## TODO`s:

- [ ] Create test for logging file path bean
- [ ] Combine two controllers into one `OrderController`
