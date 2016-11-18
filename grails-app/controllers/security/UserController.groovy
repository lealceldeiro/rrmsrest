package security

import command.security.UserCommand
import grails.converters.JSON
import org.springframework.http.HttpMethod

class UserController{

    def userService

    static allowedMethods = [
            create  : HttpMethod.POST.name()
    ]

    def list(){
        render EUser.list() as JSON
    }

    /**
     * Creates a new user
     * @param cmd User information:
     *                              username:   unique identifier for this user
     *                              name:       full name of the user
     *                              password:   password for this user
     * @return
     */
    def create(UserCommand cmd){
        def body = [:]

        if(cmd.validate()){
            final u = userService.create(cmd);
            if(u){
                body.sucess = true
                body.id = u.id
            }
            else{
                body.success = false;
            }
        }
        else{
            body.success = false;
        }
        render body as JSON
    }
}
