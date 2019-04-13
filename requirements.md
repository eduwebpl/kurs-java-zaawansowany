### Project: Outcome registry 

#### Interface: Console

#### Required:
* add new outcome 
    * optional datetime (now if empty) 
    * optional comment
    * amount
* list all outcomes
* delete outcome (all or by id/s)
* update outcome (by id)
    * everything except id can be changed
* sum outcomes betwees two dates (with time)
 
###### Nice to have:
* export everything to file(csv)
* import from file(csv)
* ability to specify path(in case of sqlite) or url to used DB

###### Suggested libs:
* connection with db:
    * *plain JDBC*
    * Hibernate / Spring data
* commandline arguments parser:
    * *picocli*
    * Apache Commons CLI
    * own implementation
    
