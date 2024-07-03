# ni2 Trouble Ticket Plugin for OpenNMS

This project implements an ni2 Trouble Ticket Plugin for OpenNMS

## current status (15/5/2024)

* Client and simulator API code generated from ni2 OpenAPI 3.0 / Swagger definition
* working TT simulator running in spring boot with some methods implemented
* working Apache CXF TT client with some methods implemented
* CXF client tests which connect to both the simulator and the live implementation
* HTTPS connection working but with no certificate checking
* 

## todo
* Complete remaining methods and tests for client and simulator
* Begin work on implementing OpenNMS TTClient api mapping business methods to CXF client calls
* Unit Tests of standalone TTClient against simulator / real implementation
* wrap TTClient in bundle / Feature / Kar definitions and packaging for OpenNMS deployment
* move logging to use slf4j over Log4J2 instead of slf4j over logback - to match OpenNMS logging
* Integration tests with running OpenNMS

## Project design and structure

As far as possible, the project has been designed using a contract first definition using the ni2 OpenAPI/swagger specification. 
This approach will give a firm base for additional integrations beyond the initial TT plugin.

Apache CXF has been chosen for the rest client implementation as this is already used internally by OpennMS and the plugin can utilise the internal library.
The [OpenAPI jaxrs-cxf-client generator](https://openapi-generator.tech/docs/generators/jaxrs-cxf-client/) was used to generate the jax-rs api. 
Some manual workarounds have been applied to address shortcomings in the generated code.

a) A code text substitution is done during the maven build to fix an incorrect type casting for BigDecimal. 

b) Unfortunately both OpenAPI generators do not handle well generating code for the `oneOf' element within the customAttributes model definitions in the ni2 spec

```
   "customAttributes": {
            "type": "object",
            "description": "The map of any additional custom attribute values of the instance",
            "additionalProperties": {
              "oneOf": [
                {
                  "type": "string"
                },
                {
                  "type": "number"
                },
                {
                  "type": "boolean"
                }
              ]
            }
          },

```

Some manual workarounds has had to be done to make this part of the API work. 
This may be an area where the spec could be improved.

Initially it was intended to use the  [OpenAPI jaxrs-cxf  server generator](https://openapi-generator.tech/docs/generators/jaxrs-cxf/) to generate a server as  basis for the test trouble ticket server.
Unfortunately, the generated server used Swagger 1.5 annotations and UI so it was less useful as a test tool.

Instead the [Spring Boot generator](https://openapi-generator.tech/docs/generators/spring/) is used to create a more complete test implementation with an inbuilt OpenAPI 3.0 Swagger UI. 

## Maven build
The project is built using maven and m2e plugin annotations are included to allow the project to be opened and worked on easily using Eclipse.

The current maven project structure is a follows

```
ni2-tt-plugin-parent - parent project for plugin build
   ni2-api-spring    - parent for spring simulator build
      ni2-api-spring-sources - open api generate spring source code
      ni2-tt-plugin-spring-mock-api-mpl - spring boot simulator implementation (using and extending generated code)
   ni2-api-cxf
      ni2-api-cxf-sources - open api generate cxf source code
      ni2-api-cxf-client - generic ni2 client implementation using generated code
   ni2-tt-api - Trouble ticket API (currently contains only tests of tt calls using cxf client)
```

## building and running tests

The complete project can be built and tested locally from the parent project. 
Maven will generate all the code, build the api and simulator, start the simulator in jetty and test the api client against the simulator during the build.

```
ni2-tt-plugin-parent
mvn clean install
```
## run local simulator manually
To run the local simulator using spring boot
```
cd ni2-tt-plugin-spring-mock-api-mpl
mvn spring-boot:run

(ctrl-c to quit)
```
A swagger UI for the simulator will be visable at http://localhost:8080
Note that only the authentication api and the relevant TT event calls are implemented.

(Alternatively using `mvn jetty:run-war` in the `ni2-tt-api` project will run the same implementation using Jetty - but it is probably better not to do this while actively working on the project module.)

## run local API tests for Ni2TroubleTicketApi

The main test class is [Ni2TroubleTicketApiTest.java](../ni2-tt-plugin/ni2-tt-api/src/test/java/org/ni2/v01/api/tt/test/Ni2TroubleTicketApiTest.java)

These tests run against a simulator running at http://localhost:8080
To run these manually in the IDE, make sure the simulator is first running.

## run manual integration API tests for Ni2TroubleTicketApi

The integration test class is [Ni2TroubleTicketApiIntegrationTest.java](../ni2-tt-plugin/ni2-tt-api/src/test/java/org/ni2/v01/api/tt/test/manual/Ni2TroubleTicketIntegrationApiTest.java)

This test does not run as part of the normal build but can be run manually from the IDE against a running production api

You should put the credentials in a file called test-properties.properties in test/resources
which can be copied from the template test-properties.properties.template
(Do not check test-properties.properties into the repo !!). 


