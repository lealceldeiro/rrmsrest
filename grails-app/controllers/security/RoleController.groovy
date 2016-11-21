package security

import command.security.RoleCommand
import grails.converters.JSON
import org.springframework.http.HttpMethod

class RoleController {

    def roleService

    static allowedMethods = [
            create  : HttpMethod.POST.name()
    ]

    def search() {
        def body = ['success': false]
        def roles = roleService.search(params)

        body.success = true
        body.items = roles

        render body as JSON
    }

    /**
     * Creates a new Role
     * @param cmd Role information:
     *                              label:          unique identifier for this role
     *                              description:    (optional) a brief description for this role
     *                              active:         whether the role is active or not
     * @return JSON informing whether the action was successful or not. If successful, it also contains the id of the
     * just created role
     */
    def create(RoleCommand cmd){
        def body = [:]

        final e = roleService.create(cmd);
        if(e){
            body.sucess = true
            body.id = e.id
        }
        else{
            body.success = false;
        }

        render body as JSON
    }
}
