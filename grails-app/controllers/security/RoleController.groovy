package security

import command.SearchCommand
import command.security.role.RoleCommand
import grails.converters.JSON
import org.springframework.http.HttpMethod

class RoleController {

    def roleService

    static allowedMethods = [
            search  : HttpMethod.GET.name(),
            save    : HttpMethod.POST.name(),
            show    : HttpMethod.GET.name(),
            delete  : HttpMethod.DELETE.name()
    ]

    //region CRUD
    /**
     * Searches for roles which match with the specified params
     * @param cmd Search criteria:
     *                              q: Criteria for searching the roles
     * @return A json containing the roles' info if the operation was successful with the following structure
     * <p><code>{success: true|false, items:[{<param1>,...,<paramN>}}]</code></p>
     */
    def search(SearchCommand cmd) {
        def body = ['success': false]
        if(cmd.validate()){
            def result = roleService.search(cmd, params)

            body.success = true
            body.total = result['total']
            body.items = result['items']
        }


        render body as JSON
    }

    /**
     * Creates a new Role or update an existing one if an id is supplied as last parameter
     * @param cmd Role information:
     *                              label:          unique identifier for this role
     *                              description:    [optional] a brief description for this role
     *                              enabled:         [optional] whether the role is enabled or not
     * @param id [optional] Identifier of Role which is going to be edited
     * @return JSON informing whether the action was successful or not. If successful, it also contains the id of the
     * just created/edited role
     */
    def save(RoleCommand cmd, long id){
        def body = ['success' : false]

        final e = roleService.save(cmd, id)
        if(e){
            body.success = true
            body.id = e.id
        }

        render body as JSON
    }

    /**
     * Return a role's info
     * @param id Role's identifier
     * @return A json containing the role's info if the operation was successful with the following structure
     * <p><code>{success: true|false, item:{<param1>,...,<paramN>}}</code></p>
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
     * @param id Role's identifier
     * @return  A json containing the role's id if the operation was successful with the following structure
     * <p><code>{success: true|false, id: <identifier></code></p>
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
    //endregion

    /**
     * Returns a role's permissions by its id
     * @param id role's id
     * @return A <code>List</code> of permissions
     */
    def permissions(long id){
        def body = ['success': false]
        if(id){
            final r = roleService.permissions(id, params)
            if(r){
                body.success = true
                body.items = r['items']
                body.total = r['total']
            }
        }
        render body as JSON
    }
}
