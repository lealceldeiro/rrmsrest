package security

import grails.transaction.Transactional
import mapping.security.OwnedEntityBean

@Transactional
class OwnedEntityService {


    def createDefaultOwnedEntity(){
        EOwnedEntity e = new EOwnedEntity(name: 'HOME', username: 'home')
        e.save(flush: true, failOnError: true)

        return e
    }

    def getDefaultOwnedEntity(){
        return EOwnedEntity.findByNameAndUsername('HOME', 'home')
    }

    /**
     * Shows some entity's info
     * @param id Identifier of the entity that is going to be shown
     * @return An OwnedEntityBean entity with the entity's info or false if none role is found
     */
    def show (long id){
        def e = Optional.ofNullable(EOwnedEntity.get(id))
        if(e.isPresent()){
            def i = e.value
            if(i){
                return new OwnedEntityBean(name: i.name, username: i.username, id: i.id)
            }
        }
        //todo: inform about the error
        return false
    }
}
