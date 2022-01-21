Scope
----------------------------------------------------------------
    Microservice Pros and Chanllenges
    Microservice Design Patterns
        Decomposition Design Patterns
        Database Design Patterns
        Integration Design Patterns
        Observability Design Patterns
        Cross-Cutting Design Patterns
    Microservice Implementation using
        Spring Boot, Spring Cloud

Pre-Requisites
------------------------------------------------------------------

    Java 8
    Spring IoC
    Spring SpEL
    Spring AOP
    Spring Web REST
    Spring Boot
    Spring Data

Lab Setup
-------------------------------------------------------------------

    JDK 8
    STS latest (4 or above)
    Zipkin-Server
    MySQL 8


E-Comerce
--------------------------------------------------------------------

   custDB <--> Consumer Service       <------>
   ordDB  <--> Orders Service         <------> 
   invDB  <--> Inventory Service      <------>     ApiGateway  <------------->   Angular/ReactJS/Andriod APP
   delDB  <--> Delivery Service       <------>


SAGA means One Bussiness Transaction
    eg: Booking an order
            1. Order Service must gneerate an orderid and insert into OrdersTable
            2. Inventory Service must update the StocksTable
            3. Delivery Service must insert into OrderTracingTable with status='PENDING'

        Choreography

            OrderService                        InventoryService        DeliveryService
                Receives and order from client
                    ----------req stock update--> attempt the update
                    <-------successful completion----
                attemtps inserting orderTable
                    -------------req tp book delivery----------------------->  atytemtp to insert into OrderTracingTable
                    <---------------attempts susccessful compeltion-------------

                Receives and order from client
                    ----------req stock update--> attempt the update
                    <-------failure status----
                terminates the operation
                adn reports the saem to the client


        Orchestration

                          | -----Book Order---->  OrderService
                          |<-----replay success---
            Orchestrator  | ------update stock--> InventoryService
                          |<-----replay success---
                          | ----Book delivery---> DeliveryService
                          |<-----replay success---
                          

                                        AndriodApp/AngualrApp/ReactJSApp....
                                                ↑↓
                                            API GateWay <---------------------->  DiscoveryService
                                    (spring cloud api gateway)              (NetFlix Eureka Discovery Service)
                                                ↑|                                     ↑|
                                                ||    Each time a micro-service needs  ||  every tiem a new instacne of the 
                                                ||           the address of another    ||  micro-service starts, it regsisters
                                                ||    microservice, it gets from here  ||  here..
                                                |↓                                     |↓ 
                            -----------------------------------------------------------------------------
                            ↑↓                  ↑↓                          ↑↓                          ↑↓        
        crDB <-> customer-service    orDB <-> order-service  invDB <-> inventory-service  dlvryDb <-> delivery-service
                            ↑↓                  ↑↓                          ↑↓                          ↑↓
                            -----------------------------------------------------------------------------
                            (spring cloud slueth)                                           ↑
                                    |                                                       |
                                    |                                               External Config Server 
                                    |                                            (spring cloud config server)
                                    |                                                       ↑
                                    ↓                                                       |
                     DistributedTracingService                                         FileRepository
                                 (Zipkin)                                                   custoemr-serivce.properties
                                                                                            order-service.properties
                                                                                            .....etc

                                
                                           




