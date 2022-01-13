# Grails : Le CoinCoin (GRAILS + GROOVY + GSP)

## Requirements:

Be sure to :

- Use Java 8 (download jdk 8 and use update-alternatives --config java for linux users)

- Add your own path for :
	- the project in config files 
	- the final path var in IllustrationService 


## Main Updates to original code:
 

### UPLOAD AN IMAGE FOR "Illustration" ENTITY
---

- Frontend :
	- views/illustration/create.gsp : new form for creating an "illustration" 

***

- Backend :
	- Using :
		- MultipartFile Interface
		- UploadIlusService.groovy
		- calling the service in save method of the controller
	- Exeption handling :
		- IO through try catch in service and rendering notFound through
        controller if error was thrown by the service
		- An other try catch for eventual erros

*** 

- Config :
	- In Application.yml in controllers section add this section:
		- upload:
			- maxFileSize: 2000
			- maxRequestSize: 2000
	- In application.groovy :
		- grails.web.disable.multipart = false

###API
---

Work done :
***

- USER / ANNONCE REQUESTS HANDLED 
	- Clean up slave entity in associations while deleting

- Init lastUpdated dateCreated in API

- Handle HTTP responses

- SpringSecurity (required token for acces to api)

***

- Using :

	- services doing the necessary tasks required by the request to the gorm

	- respond with :
		- 2 forms : XML / JSON for get requests
		- with an appropriate http status code using a service for a clean code

- COMMENT:

WE DONT NEED ACTUALLY TO HANDLE ILUSTRATION ASSCOATION SINCE WE DONT UPDATE THIS PART OF THE ENTITY 'ANNONCE'
WITH OTHER PARTS IN THE SAME REQUEST ( WE JUST AD AN ILLUSTRATION TO AN EXISTING ANNONCE )

