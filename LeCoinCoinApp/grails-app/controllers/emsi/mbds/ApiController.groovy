package emsi.mbds

import grails.converters.JSON
import grails.converters.XML
import grails.plugin.springsecurity.annotation.Secured

class ApiController {

    /**
     * GET / PUT / PATCH / DELETE
     * Pour une note max : Gérer la notion de role en plus de l'utilisateur
     */
    @Secured('ROLE_ADMIN')
    def user() {
        switch (request.getMethod()) {
            case "GET":
                    if (!params.id)
                        return response.status = 400
                    def userInstance = User.get(params.id)
                    if (!userInstance)
                        return response.status = 404
                    response.withFormat {
                        xml { render userInstance as XML }
                        json { render userInstance as JSON }
                    }
                break;
            case "PUT":
                // Mise à jour complète d'un user
                break;
            case "PATCH":
                // Mise à jour partielle d'un user
                break;
            case "DELETE":
                // Supprimer un user
                break;
            default:
                return response.status = 405
                break;
        }
        return response.status = 406
    }

    /**
     * POST / GET
     */
    def users() {

    }
    /**
     * GET / PUT / PATCH / DELETE
     */
    def annonce() {

    }

    /**
     * POST / GET
     * Pour une note maximale : gérer la notion d'illustration
     */
    def annonces() {

    }
}

