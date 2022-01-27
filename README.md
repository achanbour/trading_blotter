## A trade blotter prototype


### System design architecture

A trade blotter is a system available for traders to book in simple trades. These trades need to be stored and then appear in a blotter (visual display) for other users (sales people, managers etc) to view. There will be some validation rules to prevent entering incorrect data such as:

* Verifying whether the quantity is between 1 and 1000000
* Verifying whether the price is within a tolerence of 0.5 pence from the previous market price
* Verify that the user is authorised to trade

The system design leverages a specific pattern called the Event Driven Architecture (EDA). The initial event, in this case being the trade event (when the trader books a trade ie. buys or sells a stock), generates a sequence of intermediate events (the processing events) before getting recorded or not in the system. These processing events are the quantity, price and user authorisation checks. Such requirement checks are run concurrently and asynchronously on decoupled components called the event processors. Such event processors are usually microservices running on the web, or programs running on indepedent local threads of a mainframe.

The first instance of EDA is the mediator pattern. The core of the system is the mediator that is a complex software and whose role is orchastrating the different steps of the trade booking system. Once the mediator receives the initial trade request, it generates intermediate events and sends them to their appropriate processors via event channels. Event channels are conduits that serve as a communication interface  between the mediator and the event processors. Usually, such channels implement some sort of Messaging Queuing Protocol (eg. Rabbit MQ). Each processor executes the check that has been assigned to it by the mediator and pushes a reponse back to the mediator. For instance, to check whether the market price is valid, the event procesor sends API calls to a remote database containing last market stock prices and thus validates the request. Once all the processors have accomplished their tasks, the mediator aggreagtes all the information received by the processors and generates a final event to the blotter processor, instructing whether or not the trade should be entered in the system.

[](./mediator_pattern.jpg)

The second instance of EDA is the broker pattern. Similar to the previous architecture, the broker does not have a central mediator that manages how the different processing events get executed. Instead, those events get executed sequentially by each event processor one at a time, in a way, that the market price check is not executed before the price has been checked by the previous event processor. Once that processor has finished accomplishing its task, it pushes a callback via the event channel instructing the subsequent processor to execute his check. All callbacks are managed by the event channel and carried onto the final blotter processor which then agregates all the information to decide on whether the trade should be recorded or not. The weakness of such a system is that it is prone to blocking. Whenever mulitple trade requests are received by the system, if some event takes time to get processed by one of the components, then the subsequent trade wouldn't be able to get processed before the previous trade has accomplished its check at that processor. One way to avoid such blocking, is to have each processor open a different instance of itself (a container) and process the trade events independently of one another on those containers.

[](./broker_pattern.jpg)

Full slides are available in the repository.

### Development of the prototype in Java

The prototype is in the process of development at the current stage.



