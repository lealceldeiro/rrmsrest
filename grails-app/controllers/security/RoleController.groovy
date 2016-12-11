package security

import command.security.RoleCommand
import grails.converters.JSON
import org.springframework.http.HttpMethod

class RoleController {

    def roleService

    static allowedMethods = [
            search  : HttpMethod.GET.name(),
            save  : HttpMethod.POST.name(),
            show  : HttpMethod.GET.name(),
            delete  : HttpMethod.POST.name()
    ]

    /**
     * Searches for roles which match with the specified params
     * @return Roles
     */
    def search() {
        def body = ['success': false]
        def roles = roleService.search(params)

        body.success = true
        body.total = roles.size()
        body.items = roles

        render body as JSON
    }

    /**
     * Creates a new Role or update an existing one if an id si supplied as last parameter
     * @param cmd Role information:
     *                              label:          unique identifier for this role
     *                              description:    (optional) a brief description for this role
     *                              active:         whether the role is active or not
     * @param id [optional] Identifier of Role which is going to be edited
     * @return JSON informing whether the action was successful or not. If successful, it also contains the id of the
     * just created/edited role
     */
    def save(RoleCommand cmd, long id){
        def body = ['success' : false]

        final e = roleService.save(cmd, id);
        if(e){
            body.success = true
            body.id = e.id
        }

        render body as JSON
    }

    /**
     * Return a role's info
     * @param id Role's identifier
     */
    def show(long id){
        def body = ['success' : false]
        def e = roleService.show(id);
        if(e){
            body.success = true;
            body.item = e;
        }
        render body as JSON
    }

    /**
     * Deletes a Role
     * @param id Role's idetifier
     * @return
     */
    def delete(long id){
        def body = ['success': false]
        final e = roleService.delete(id)
        if(e){
            body.success = true
            body.id = id
        }
        render body as JSON
    }
}
