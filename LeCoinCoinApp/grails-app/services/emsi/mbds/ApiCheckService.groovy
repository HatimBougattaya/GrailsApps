package emsi.mbds

import grails.gorm.transactions.Transactional

@Transactional
class ApiCheckService {
    //USER
    int checkErrorUser(p) {
        def result = 0
        if (!p.id){
            result = 400
        }
        else{
            def userInstance = User.get(p.id)
            if (!userInstance)
                result = 404
        }
        return result
    }

    //ANNONCE
    int checkErrorAno(p) {
        def result = 0
        if (!p.id){
            result = 400
        }
        else{
            def annonceInstance = Annonce.get(p.id)
            if (!annonceInstance)
                result = 404
        }
        return result
    }

    //PUT/PATCH/POST/DELETE
    // get respond with an object
    int responseSuccess(String str){
        switch (str){
            case "PUT":
                return 200
                break;
            case "PATCH":
                return 200
                break;
            case "POST":
                return 201
                break;
            case "DELETE":
                return 200
                break;
            default:
                return 405
                break;
        }
    }
}
