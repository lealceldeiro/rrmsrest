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
     * creates a new user
     * @param cmd
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
