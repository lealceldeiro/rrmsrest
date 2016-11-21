package command.security

import grails.validation.Validateable
import security.ERole

/**
 * Created by Asiel on 11/20/2016.
 */
@Validateable
class RoleCommand {

    long id
    String label
    String description
    boolean active

    static constraints = {
        label nullable: false, blank: false
        active nullable: false
    }

    def call(){
        ERole e = new ERole(label: label, description: description, active: active)
        e.id = id
        return e
    }
}