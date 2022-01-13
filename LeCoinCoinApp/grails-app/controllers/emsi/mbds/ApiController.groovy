package emsi.mbds

import grails.converters.JSON
import grails.converters.XML
import grails.plugin.springsecurity.annotation.Secured

class ApiController {

    // Services
    ApiCheckService apiCheckService
    ApiModifyService apiModifyService

    UserService userService
    AnnonceService annonceService

    /**
     * GET / PUT / PATCH / DELETE
     */

    @Secured('ROLE_ADMIN')
    def user() {
        switch (request.getMethod()) {
            case "GET":
                //check
                if(apiCheckService.checkErrorUser(params) != 0)
                    return response.status = apiCheckService.checkErrorUser(params)

                //RENDER
                def userInstance = userService.get(params.id)
                response.withFormat {
                    xml { render userInstance as XML }
                    json { render userInstance as JSON }
                }
                break;

            case "PUT":
                // Mise à jour complète d'un user
                //CHECK
                if(apiCheckService.checkErrorUser(params) != 0)
                    return response.status = apiCheckService.checkErrorUser(params)
                //INIT
                def usJson = request.JSON
                def userInstance = userService.get(params.id)
                //good request?
                if(!(usJson.getAt("username") && usJson.getAt("password")))
                    return response.status = 400

                //RENDER
                apiModifyService.putUser(userInstance,usJson)
                return apiCheckService.responseSuccess("PUT")
                break;

            case "PATCH":
                // Mise à jour partielle d'un user
                //CHECK
                if(apiCheckService.checkErrorUser(params) != 0)
                    return response.status = apiCheckService.checkErrorUser(params)
                //INIT
                def usJson = request.JSON
                def userInstance = userService.get(params.id)

                //RENDER
                apiModifyService.patchUser(userInstance,usJson)
                return apiCheckService.responseSuccess("PATCH")
                break;

            case "DELETE":
                // Supprimer un user
                //CHECK
                if(apiCheckService.checkErrorUser(params) != 0)
                    return response.status = apiCheckService.checkErrorUser(params)
                //INIT
                def usJson = request.JSON
                def userInstance = userService.get(params.id)

                //RENDER
                //CleanUP Association
                apiModifyService.deleteUser(userInstance)
                //actual delete
                userService.delete(userInstance.id)
                return apiCheckService.responseSuccess("DELETE")
                break;

            default:
                return apiCheckService.responseSuccess("")
                break;
        }
        return response.status = 406
    }

    /**
     * POST / GET
     */
    @Secured('ROLE_ADMIN')
    def users() {
        switch (request.getMethod()) {
            case "GET":
                def userList = userService.list()
                response.withFormat {
                    xml { render userList as XML }
                    json { render userList as JSON }
                }
                break;

            case "POST":
                //INIT
                def usJson = request.JSON
                apiModifyService.createUser(usJson)
                return apiCheckService.responseSuccess("POST")
                break;

            default:
                return apiCheckService.responseSuccess("")
                break;
        }
        return response.status = 406
    }
    /**
     * GET / PUT / PATCH / DELETE
     *
     * IMPORTANT:
     * WE DONT NEED ACTUALLY TO HANDLE ILUSTRATION ASSCOATION SINCE WE DONT UPDATE THIS PART OF THE ENTITY 'ANNONCE'
     * WITH OTHER PARTS IN THE SAME REQUEST ( WE JUST AD AN ILLUSTRATION TO AN EXISTING ANNONCE )
     */
    @Secured('ROLE_ADMIN')
    def annonce() {
        switch (request.getMethod()) {
            case "GET":
                //check
                if(apiCheckService.checkErrorAno(params) != 0)
                    return response.status = apiCheckService.checkErrorAno(params)

                //RENDER
                def anoInstance = annonceService.get(params.id)
                response.withFormat {
                    xml { render anoInstance as XML }
                    json { render anoInstance as JSON }
                }
                break;

            case "PUT":
                // Mise à jour complète d'un user
                //CHECK
                if(apiCheckService.checkErrorAno(params) != 0)
                    return response.status = apiCheckService.checkErrorAno(params)
                //INIT
                def anoJson = request.JSON
                def anoInstance = annonceService.get(params.id)
                //good request?
                if(!(anoJson.getAt("title") && anoJson.getAt("description") && anoJson.getAt("price") && anoJson.getAt("ref") && anoJson.getAt("status")))
                    return response.status = 400

                //RENDER
                apiModifyService.putAno(anoInstance,anoJson)
                return apiCheckService.responseSuccess("PUT")
                break;

            case "PATCH":
                // Mise à jour partielle d'un user
                //CHECK
                if(apiCheckService.checkErrorAno(params) != 0)
                    return response.status = apiCheckService.checkErrorUser(params)
                //INIT
                def anoJson = request.JSON
                def anoInstance = annonceService.get(params.id)

                //RENDER
                apiModifyService.patchAno(anoInstance,anoJson)
                return apiCheckService.responseSuccess("PATCH")
                break;

            case "DELETE":
                // Supprimer un user
                //CHECK
                if(apiCheckService.checkErrorAno(params) != 0)
                    return response.status = apiCheckService.checkErrorAno(params)
                //INIT
                def anoJson = request.JSON
                def anoInstance = annonceService.get(params.id)

                //RENDER
                //CleanUP Association
                apiModifyService.deleteAno(anoInstance)
                //actual delete
                annonceService.delete(anoInstance.id)
                return apiCheckService.responseSuccess("DELETE")
                break;

            default:
                return apiCheckService.responseSuccess("")
                break;
        }
        return response.status = 406
    }

    /**
     * POST / GET
     * Pour une note maximale : gérer la notion d'illustration
     */
    @Secured('ROLE_ADMIN')
    def annonces() {
        switch (request.getMethod()) {
            case "GET":
                def anoList = annonceService.list()
                response.withFormat {
                    xml { render anoList as XML }
                    json { render anoList as JSON }
                }
                break;

            case "POST":
                //INIT
                def anoJson = request.JSON
                apiModifyService.createAno(anoJson)
                return apiCheckService.responseSuccess("POST")
                break;

            default:
                return apiCheckService.responseSuccess("")
                break;
        }
        return response.status = 406
    }
}

