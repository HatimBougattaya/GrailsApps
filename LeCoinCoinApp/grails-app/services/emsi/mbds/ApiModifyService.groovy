package emsi.mbds

import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.SpringSecurityService

@Transactional
class ApiModifyService {

    SpringSecurityService springSecurityService
    /*
                        USER
    *
    */
    //PUT
    void putUser(User user, uJson) {
        user.setUsername(uJson.getAt("username"))
        user.setPassword(uJson.getAt("password"))
        user.save(flush: true)
    }
    //patch
    void patchUser(User user, uJson) {
        if (uJson.getAt("username"))
            user.setUsername(uJson.getAt("username"))

        if (uJson.getAt("password"))
            user.setPassword(uJson.getAt("password"))

        user.save(flush : true)
    }
    //CleanUP Association
    void deleteUser(User user) {
        UserRole.removeAll(user)
    }
    //POST
    void createUser(uJson){
        User user = new User(
                username: uJson.getAt("username"),
                password: uJson.getAt("password")
        ).save()
        UserRole.create(user,  Role.findByAuthority("ROLE_USER") , true)
    }

    /*
                            ANNONCE
    *
    */
    //PUT
    void putAno(Annonce annonce, anJson) {
        annonce.setTitle(anJson.getAt("title"))
        annonce.setDescription(anJson.getAt("description"))
        annonce.setPrice(anJson.getAt("price"))
        //System.out.println("*************" +annonce.getPrice())
        annonce.setRef(anJson.getAt("ref"))
        annonce.setStatus(anJson.getAt("status"))
        annonce.setIllustrations(anJson.getAt("illustrations"))
        //update date
        annonce.setLastUpdated(new Date())
        annonce.save(flush: true)
    }

    //PATCH
    void patchAno(Annonce annonce, anJson) {
        if (anJson.getAt("title")) {
            annonce.setTitle(anJson.getAt("title"))
        }

        if (anJson.getAt("description")) {
            annonce.setDescription(anJson.getAt("description"))
        }

        if (anJson.getAt("price")) {
            annonce.setPrice(anJson.getAt("price"))
        }

        if (anJson.getAt("ref")) {
            annonce.setRef(anJson.getAt("ref"))
        }

        if (anJson.getAt("status")) {
            annonce.setStatus(anJson.getAt("status"))
        }

        if (anJson.getAt("illustrations")) {
            annonce.setIllustrations(anJson.getAt("illustrations"))
        }

        //update date
        annonce.setLastUpdated(new Date())
        //needs nmore
        annonce.save(flush: true)
    }

    //CleanUP Association
    Boolean deleteAno(Annonce ano) {
        return ano.getAuthor().getAnnonces().remove(ano)
    }

    //POST
    void createAno(anJson){
        //get user in session
        User actualAuthor = springSecurityService.getCurrentUser()
        Annonce annonce = new Annonce(
                title: anJson.getAt("title"),
                description: anJson.getAt("description"),
                price: anJson.getAt("price"),
                ref: anJson.getAt("ref"),
                status: anJson.getAt("status"),
                illustarions: anJson.getAt("illustrations"),
                author: actualAuthor
        )
        annonce.setDateCreated(new Date())
        annonce.save(flush: true)
        //save association
        actualAuthor.addToAnnonces(annonce)
        actualAuthor.save()
    }

}
