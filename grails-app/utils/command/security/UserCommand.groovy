package command.security

import grails.validation.Validateable
import security.EUser

/**
 * Created by Asiel on 10/23/2016.
 */
@Validateable
class UserCommand {

    long id
    String username
    String name
    String password

    static constraints = {
        username nullable: false, blank: false
        name nullable: false, blank: false
        password nullable: false, blank: false
    }

    def call(){
        EUser u = new EUser(username: username, name: name, password: password)
        u.id = id
        return u
    }
}