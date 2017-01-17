package command.security.user

import grails.validation.Validateable
import org.grails.databinding.BindUsing
import security.EUser

/**
 * Created by Asiel on 10/23/2016.
 */
@Validateable
class UserCommand {

    long id
    String username
    String email
    String name
    String password
    boolean enabled

    @BindUsing({object, source ->
        String r = (source['roles'] as String)
        r = r.substring(1, r.length() - 1).replaceAll(" ", "")
        r.split(',')
    })
    List<Long> roles

    static constraints = {
        username nullable: false, blank: false
        email nullable: true, blank: false
        name nullable: false, blank: false
        password nullable: false, blank: false
        roles nullable: false
        enabled nullable: true
    }

    def call(){
        //roles are not added here, but in the controller, using the BUser_Role domain class
        EUser u = new EUser(username: username, email: email, name: name, password: password, enabled: enabled)
        u.id = id
        return u
    }
}