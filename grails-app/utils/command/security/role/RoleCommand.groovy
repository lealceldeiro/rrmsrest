package command.security.role

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
        active nullable: true
        description nullable: true
    }

    def call(){
        ERole e = new ERole(label: label, description: description ? description : "security.role.no_description",
                active: active ? active : false)
        e.id = id
        return e
    }
}