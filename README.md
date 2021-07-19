# flights-control-tool

**## Task**
API as a tool for flight control. Entities should be the next:
    - Airport in specific town
    - Airplane of specific company
    - Flight details
    - Staff for airplane
User should be able to see the list of flights and see details related to the chosen flight.
All information about planes, staff, airport etc can be migrated in the system through import 
or you can create endpoints for that.

**_What was done:**
1. A draft version of application is created (structure: entity-dto-request with mappers, 
   endpoint-service-repository with exceptions), some classes do not function properly,
   it's more pseudo-code in some cases.
2. That's my first experience with such frameworks, tools and libraries as Spring Boot, Hibernate, 
   liquibase, swagger, mapstruct, Docker (Docker file was not created but at least application.yml is here)._


**## Acceptance criteria**
1. Application should be able to run from scratch on empty DB as a container or in IDE of your choice.
   _I didn't implement any 'import' (e.g. cvs files with data and some classes that should parse them 
   and populate the database). For now only some sql scripts that are running from liquibase yaml file._  
2. API should be documented (e.g. Swagger).
   _Done, swagger was configured (available on localhost:8080/swagger-ui.html)_
3. Try to follow clean code, SOLID, SMART, KISS, DRY etc principles.
   _I was trying :)_


**## Technical acceptance criteria**
1. Use Spring Boot
   _Done_
2. Use Java 11 or higher
   _Done_
3. Use Hibernate as the ORM implementation
   _Done, but in minimal scope, no validation which I'd like to add_
4. Use Postgres SQL DB running as a container or running in the cloud
   _Done_
5. Use Git as VCS
   _Done, git is used and github for project hosting_
6. Use any tool you like for version control of DB state
   _Done, liquibase is used_
7. Test coverage should be >= 80%
   _In progress, for now no tests were added to PR_