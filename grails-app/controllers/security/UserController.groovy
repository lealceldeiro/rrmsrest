package security

import command.SearchCommand
import command.security.user.UserCommand
import grails.converters.JSON
import org.springframework.http.HttpMethod

class UserController{

    def userService

    static allowedMethods = [
            search  : HttpMethod.GET.name(),
            save  : HttpMethod.POST.name(),
            show  : HttpMethod.GET.name(),
            delete  : HttpMethod.DELETE.name(),
            roles  : HttpMethod.GET.name()
    ]

    //region CRUD
    /**
     * Searches for users which match with the specified params
     * @param cmd Search criteria:
     *                              q: Criteria for searching the users
     * @return A json containing the user's info if the operation was successful with the following structure
     * <p><code>{success: true|false, items:[{<param1>,...,<paramN>}}]</code></p>
     */
    def search(SearchCommand cmd) {
        def body = ['success': false]
        if(cmd.validate()){
            def result = userService.search(cmd, params)

            body.success = true
            body.total = result['total']
            body.items = result['items']
        }

        render body as JSON
    }

    /**
     * Creates a new User or update an existing one if an id is supplied as last parameter
     * @param cmd User information:
     *                              username:           User's username
     *                              email:              [optional] user's email
     *                              name:               User's name
     *                              password:           User's password
     *                              roles               List with the User's roles's identifiers
     * @param id [optional] Identifier of User which is going to be edited
     * @return JSON informing whether the action was successful or not. If successful, it also contains the id of the
     * just created/edited user
     */
    def save(UserCommand cmd, long id){
        def body = ['success' : false]

        final e = userService.save(cmd, id);
        if(e){
            body.success = true
            body.id = e.id
        }

        render body as JSON
    }

    /**
     * Return a user's info
     * @param id User's identifier
     * @return A json containing the user's info if the operation was successful with the following structure
     * <p><code>{success: true|false, item:{<param1>,...,<paramN>}}</code></p>
     */
    def show(long id){
        def body = ['success' : false]
        def e = userService.show(id);
        if(e){
            body.success = true;
            body.item = e;
        }
        render body as JSON
    }

    /**
     * Deletes a User
     * @param id Users's identifier
     * @return A json containing the user's id if the operation was successful with the following structure
     * <p><code>{success: true|false, id: <identifier></code></p>
     */
    def delete(long id){
        def body = ['success': false]
        final e = userService.delete(id)
        if(e){
            body.success = true
            body.id = id
        }
        render body as JSON
    }
    //endregion

    /**
     * Returns a user's roles by its id
     * @param id user's id
     * @return A <code>List</code> of roles
     */
    def roles(long id){
        def body = ['success': false]
        if(id){
            final r = userService.roles(id, params)
            if(r){
                body.success = true
                body.items = r['items']
                body.total = r['total']
            }
        }
        render body as JSON
    }
}
