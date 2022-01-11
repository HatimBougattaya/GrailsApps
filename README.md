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

