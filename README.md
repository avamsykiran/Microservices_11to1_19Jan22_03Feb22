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

                                
BudgetTrackerSystem - Case Study
-----------------------------------------------------------------------------------------------------

    High Level Requirements
        1. Account Holder can add/edit his profile
        2. Add/Edit/Delete his Transactions
        3. Generate Period Account Statement

    Monolythic Approach
        com.cts.budget-tracker
            com.cts.budget-tracker.entities
                AccountHolder
                    ahId : Long
                    firstName : String
                    lastName : String
                    emailId : String
                    mobileNumber : String
                    txns : Set<Transactions>
                    currentBal : Double

                TransactionType {CREDIT,DEBIT}
                
                Transaction
                    txnId : Long
                    header : String
                    amount : Double
                    txnType : TransactionType
                    dateOfTxn: LocalDate
                    holder : AccountHolder
                
            com.cts.budget-tracker.model
                Statement
                    holder : AccountHolder
                    startDate : LocalDate
                    endDate : LocalDate
                    txns : Set<Transaction>
                    totalCredit : double
                    totalDebit : double
                    balance : double

            com.cts.budget-tracker.repo
                AccountHolderRepo
                TransactionRepo
            com.cts.budget-tracker.service
                AccountHolderService
                TransactionService
                StatementService
            com.cts.budget-tracker.exception
                BudgetTrackerException
            com.cts.budget-tracker.controller
                AccountHolderController
                TransactionController
                StatementController

BudgetTrackerSystem - Case Study - MicroServices Approach 
---------------------------------------------------------------------------------------------
   
   Decompistion Design Pattern (Domain Driven Design and Bounded-context)

        profile-service
            com.cts.budget-tracker.profile
                com.cts.budget-tracker.profile.entities
                     AccountHolder
                        ahId : Long
                        firstName : String
                        lastName : String
                        emailId : String
                        mobileNumber : String
                com.cts.budget-tracker.profile.repo
                    AccountHolderRepo
                com.cts.budget-tracker.profile.service
                    AccountHolderService
                com.cts.budget-tracker.profile.controller
                    AccountHolderController
                com.cts.budget-tracker.profile.exception
                    ProfileException

        txns-service
            com.cts.budget-tracker.txns
                com.cts.budget-tracker.txns.entities
                    AccountHolder
                        ahId : Long
                        txns : Set<Transactions>
                        currentBal : Double

                    TransactionType {CREDIT,DEBIT}
                    
                    Transaction
                        txnId : Long
                        header : String
                        amount : Double
                        txnType : TransactionType
                        dateOfTxn: LocalDate
                        holder : AccountHolder

                com.cts.budget-tracker.txns.repo
                    AccountHolderRepo
                    TransactionRepo
                com.cts.budget-tracker.txns.service
                    TransactionService
                com.cts.budget-tracker.txns.controller
                    TransctionController
                com.cts.budget-tracker.txns.exception
                    TransactionException

        statement-service
             com.cts.budget-tracker.statement
                com.cts.budget-tracker.statement.models
                     AccountHolder
                        ahId : Long
                        firstName : String
                        lastName : String
                        emailId : String
                        mobileNumber : String
                        txns : Set<Transactions>
                        currentBal : Double

                    TransactionType {CREDIT,DEBIT}
                
                    Transaction
                        txnId : Long
                        header : String
                        amount : Double
                        txnType : TransactionType
                        dateOfTxn: LocalDate
                        holder : AccountHolder

                    Statement
                        holder : AccountHolder
                        startDate : LocalDate
                        endDate : LocalDate
                        txns : Set<Transaction>
                        totalCredit : double
                        totalDebit : double
                        balance : double

                com.cts.budget-tracker.statement.service
                    StatementService
                com.cts.budget-tracker.statement.controller
                    StatementController
                com.cts.budget-tracker.statement.exception
                    StatementException

    Database Design Pattern (Database per Service)

        profileDB <--------> profile-service
        txnsDB    <--------> txns-service
                             statement-service 

    Cross Cutting Design Pattern - Discovery Service Design Pattern

                        discovery-service
                            |
                            |
            -------------------------------------------------
            |                       |                       |
            profile-service     txns-service            statement-service


    Integration Design Pattern (Api Gateway Design Pattern)

                   discovery-service        gateway-service
                            |                   |
                            |                   |
            -------------------------------------------------
            |                       |                       |
            profile-service     txns-service            statement-service

     Cross Cutting Design Pattern - External Config Design Pattern

                discovery-service        gateway-service
                            |                   |
                            |                   |
            -------------------------------------------------
            |                       |                       |
            profile-service     txns-service            statement-service
                        |               |                           |
                    --------------------------------------------------
                            |
                            |
                        config-service

    Observability Design Pattern - Distributed Tracing

                discovery-service        gateway-service
                            |                   |
                            |                   |
            -------------------------------------------------
            |                       |                       |
            profile-service     txns-service            statement-service
                        |               |                           |
                    --------------------------------------------------
                            |                               |
                            |                               |
                        config-service                     tracing-service 


BudgetTrackerSystem - Case Study - MicroServices Approach - implementation
---------------------------------------------------------------------------------------------
   
    Step1: Base Micro Services and code the interaction between them

        profile-service
            spring-boot-starter-web
            spring-boot-starter-data-jpa
            spring-cloud-starter-openfeign
            mysql-connector-java
            spring-boot-devtools
        txns-service
            spring-boot-starter-web
            spring-boot-starter-data-jpa
            spring-cloud-starter-openfeign
            mysql-connector-java
            spring-boot-devtools
            @EnableFiegnClients     on Application class

        statement-service                                   (Aggregator Design Pattern)
            spring-boot-starter-web
            spring-cloud-starter-openfeign
            spring-boot-devtools
             @EnableFiegnClients     on Application class

    Step2: Discovery Service and Load Balancing

        discovery-service
            spring-boot-devtools
            spring-cloud-starter-netflix-eureka-server
             @EnableEurekaServer    on Application class

            spring.application.name=discovery-service
            server.port=9000

            eureka.instance.hostname=localhost
            eureka.client.registerWithEureka=false
            eureka.client.fetchRegistry=false
            eureka.client.serviceUrl.defaultZone=http://${eureka.instance.hostname}:${server.port}/eureka/
            eureka.server.waitTimeInMsWhenSyncEmpty=0

        profile-service
            ++spring-cloud-starter-netflix-eureka-client
            ++spring-cloud-starter-loadbalancer
            ++@EnableDiscoveryClient  on Application class

            eureka.client.serviceUrl.defaultZone=http://localhost:9000/eureka/
            eureka.client.initialInstanceInfoReplicationIntervalSeconds=5
            eureka.client.registryFetchIntervalSeconds=5
            eureka.instance.leaseRenewalIntervalInSeconds=5
            eureka.instance.leaseExpirationDurationInSeconds=5

            spring.cloud.loadbalancer.ribbon.enabled=false

        txns-service
            ++spring-cloud-starter-netflix-eureka-client
            ++spring-cloud-starter-loadbalancer
            ++@EnableDiscoveryClient  on Application class

            eureka.client.serviceUrl.defaultZone=http://localhost:9000/eureka/
            eureka.client.initialInstanceInfoReplicationIntervalSeconds=5
            eureka.client.registryFetchIntervalSeconds=5
            eureka.instance.leaseRenewalIntervalInSeconds=5
            eureka.instance.leaseExpirationDurationInSeconds=5

            spring.cloud.loadbalancer.ribbon.enabled=false
            
        statement-service
             ++spring-cloud-starter-netflix-eureka-client
            ++spring-cloud-starter-loadbalancer
            ++@EnableDiscoveryClient  on Application class

            eureka.client.serviceUrl.defaultZone=http://localhost:9000/eureka/
            eureka.client.initialInstanceInfoReplicationIntervalSeconds=5
            eureka.client.registryFetchIntervalSeconds=5
            eureka.instance.leaseRenewalIntervalInSeconds=5
            eureka.instance.leaseExpirationDurationInSeconds=5

            spring.cloud.loadbalancer.ribbon.enabled=false

    Step3: Api Gateway

        gateway-service
            spring-boot-devtools
            Spring Cloud Api Gateway
            spring-cloud-starter-netflix-eureka-client,
            spring-cloud-starter-loadbalancer
            @EnableDiscoveryClient          on Application class

            spring.application.name=gateway-service
            server.port=9999

            eureka.client.serviceUrl.defaultZone=http://localhost:9000/eureka/
            eureka.client.initialInstanceInfoReplicationIntervalSeconds=5
            eureka.client.registryFetchIntervalSeconds=5
            eureka.instance.leaseRenewalIntervalInSeconds=5
            eureka.instance.leaseExpirationDurationInSeconds=5

            spring.cloud.gateway.discovery.locator.enabled=true
            spring.cloud.gateway.discovery.locator.lower-case-service-id=true

            # refer for more configuarable properties 
            # https://docs.spring.io/spring-cloud-gateway/docs/current/reference/html/appendix.html

        discovery-service
        profile-service
        txns-service
        statement-service

    Step4: Distributed Tracing

        discovery-service
        gateway-service
            ++spring-boot-starter-actuator
            ++spring-cloud-starter-sleuth
            ++spring-cloud-starter-zipkin : 2.2.8.RELEASE
            logger.level.org.springramework.web=debug
            management.endpoints.web.exposure.include=*
        profile-service
            ++spring-boot-starter-actuator
            ++spring-cloud-starter-sleuth
            ++spring-cloud-starter-zipkin : 2.2.8.RELEASE
            logger.level.org.springramework.web=debug
            management.endpoints.web.exposure.include=*
        txns-service
            ++spring-boot-starter-actuator
            ++spring-cloud-starter-sleuth
            ++spring-cloud-starter-zipkin : 2.2.8.RELEASE
            logger.level.org.springramework.web=debug
            management.endpoints.web.exposure.include=*
        statement-service
            ++spring-boot-starter-actuator
            ++spring-cloud-starter-sleuth
            ++spring-cloud-starter-zipkin : 2.2.8.RELEASE
            logger.level.org.springramework.web=debug
            management.endpoints.web.exposure.include=*

        tracing-service
            zipkin-server
             https://search.maven.org/remote_content?g=io.zipkin&a=zipkin-server&v=LATEST&c=exec 
                java -jar zipkin.jar

    Step5: Circuit Breaker 
        discovery-service
        gateway-service
        profile-service
        txns-service
            ++spring-cloud-starter-circuitbreaker-resilience4j
            
            resilience4j.circuitbreaker.configs.default.registerHealthIndicator=true
            resilience4j.circuitbreaker.configs.default.ringBufferSizeInClosedState=4
            resilience4j.circuitbreaker.configs.default.ringBufferSizeInHalfOpenState=2
            resilience4j.circuitbreaker.configs.default.automaticTransitionFromOpenToHalfOpenEnabled=true
            resilience4j.circuitbreaker.configs.default.waitDurationInOpenState= 20s
            resilience4j.circuitbreaker.configs.default.failureRateThreshold= 50
            resilience4j.circuitbreaker.configs.default.eventConsumerBufferSize= 10
        statement-service
            ++spring-cloud-starter-circuitbreaker-resilience4j
            
            resilience4j.circuitbreaker.configs.default.registerHealthIndicator=true
            resilience4j.circuitbreaker.configs.default.ringBufferSizeInClosedState=4
            resilience4j.circuitbreaker.configs.default.ringBufferSizeInHalfOpenState=2
            resilience4j.circuitbreaker.configs.default.automaticTransitionFromOpenToHalfOpenEnabled=true
            resilience4j.circuitbreaker.configs.default.waitDurationInOpenState= 20s
            resilience4j.circuitbreaker.configs.default.failureRateThreshold= 50
            resilience4j.circuitbreaker.configs.default.eventConsumerBufferSize= 10            
        tracing-service

    Step6: External Configuaration

        inTheWorkSpace> md bt-props-repo
         //and then create these files in this directory
            // gateway-service.properties
            // profile-service.properties
            // txns-service.properties
            // statement-service.properties
            
        inTheWorkSpace> cd bt-props-repo
        inTheWorkSpace\bt-props-repo> git init           
        inTheWorkSpace\bt-props-repo> git add .
        inTheWorkSpace\bt-props-repo> git commit -m "all service properties"
        
    
        config-service
            spring-cloud-config-server
            spring-cloud-starter-netflix-eureka-client
            spring-boot-devtools

            @EnableDiscoveryClient
            @EnableConfigServer             on Application class

            spring.application.name=config-service
            server.port=9090

            spring.cloud.config.server.git.uri=file:///local/git/repo/path

            eureka.client.serviceUrl.defaultZone=http://localhost:9000/eureka/
            eureka.client.initialInstanceInfoReplicationIntervalSeconds=5
            eureka.client.registryFetchIntervalSeconds=5
            eureka.instance.leaseRenewalIntervalInSeconds=5
            eureka.instance.leaseExpirationDurationInSeconds=5

        discovery-service

        gateway-service        
            ++spring-cloud-starter-bootstrap
            ++spring-cloud-config-client

            spring.cloud.config.name=gateway-service
            spring.cloud.config.discovery.service-id=config-service
            spring.cloud.config.discovery.enabled=true
            
            eureka.client.serviceUrl.defaultZone=http://localhost:9000/eureka/

        profile-service
            ++spring-cloud-starter-bootstrap
            ++spring-cloud-config-client

            spring.cloud.config.name=profile-service
            spring.cloud.config.discovery.service-id=config-service
            spring.cloud.config.discovery.enabled=true
            
            eureka.client.serviceUrl.defaultZone=http://localhost:9000/eureka/

        txns-service
            ++spring-cloud-starter-bootstrap
            ++spring-cloud-config-client

            spring.cloud.config.name=txns-service
            spring.cloud.config.discovery.service-id=config-service
            spring.cloud.config.discovery.enabled=true
            
            eureka.client.serviceUrl.defaultZone=http://localhost:9000/eureka/

        statement-service
            ++spring-cloud-starter-bootstrap
            ++spring-cloud-config-client

            spring.cloud.config.name=statement-service
            spring.cloud.config.discovery.service-id=config-service
            spring.cloud.config.discovery.enabled=true
            
            eureka.client.serviceUrl.defaultZone=http://localhost:9000/eureka/

        tracing-service