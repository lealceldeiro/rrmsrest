package security

import command.security.user.UserCommand
import grails.converters.JSON
import org.springframework.http.HttpMethod

class UserController{

    def userService

    static allowedMethods = [
            create  : HttpMethod.POST.name()
    ]

    def search(){
        def body = ['success': false]
        def roles = userService.search(params)

        body.success = true
        body.items = roles

        render body as JSON
    }

    /**
     * Creates a new user
     * @param cmd User information:
     *                              username:   unique identifier for this user
     *                              name:       full name for this user
     *                              password:   password for this user
     * @return
     */
    def create(UserCommand cmd){
        def body = [:]

        final u = userService.create(cmd);
        if(u){
            body.sucess = true
            body.id = u.id
        }
        else{
            body.success = false;
        }

        render body as JSON
    }
}
