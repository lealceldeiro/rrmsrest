package security

import command.SearchCommand
import command.security.user.UserCommand
import mapping.security.RoleBean
import mapping.security.UserBean

import grails.transaction.Transactional

@Transactional
class UserService {

    /**
     * Search a user(s) according to optional parameters
     * @param cmd       [optional] Grails command containing the parameters for searching the user(s)
     *                  q: Criteria for searching
     * @param params    [optional] Parameters for paging the result
     * @return          A json containing a list of users with the following structure if the operation was successful
     * <p><code>{success: true|false, items:[<it1>,...,<itn>], total: <totalCount>}</code></p>
     */
    def search(SearchCommand cmd, Map params) {
        Map response = [:]

        def list = EUser.createCriteria().list(params) {
            order("username", "desc")
            if(cmd.q) {
                or{
                    ilike("username", "%${cmd.q}%")
                    ilike("email", "%${cmd.q}%")
                    ilike("name", "%${cmd.q}%")
                }
            }

        }

        def mapped = [];
        list.each {
            def r = it.roles
            List<RoleBean> roles = []
            if(r) {
                r.each {
                    roles << new RoleBean(id: it.id, label: it.label, description: it.description)
                }
            }
            mapped << new UserBean(id: it.id, username: it.username, email: it.email, name: it.name, roles: roles)
        }

        response.items = mapped
        response.total = list.totalCount
        return response
    }

    /**
     * Creates or updates a role
     * @param cmd Role data such: label(string), description(string) and active(boolean)
     * @param id [optional] if an update is going to be performed, the id of the role which is going to be updated
     * must be supplied
     * @return A json containing the id of the role if the operation was successful
     * <p><code>{success: true|false, id: <roleId>}</code></p>
     */
    def save(UserCommand cmd, long id) {
        EUser e = cmd()
        boolean creating = false
        if(e.validate()){
            EUser aux = null;
            //editing
            if(id != null){
                aux = EUser.get(id)
            }
            //creating
            if(!aux){
                aux = e;
            }
            else{
                aux.id = e.id;
                aux.username = e.username;
                aux.email = e.email;
                aux.name = e.name;
                aux.password = e.password;
                aux.roles = e.roles;
            }
            //set the corresponding roles to the user
            if (cmd.roles.size() > 0) { //not checking for nullability in cmd.roles because it's marked as not nullable on cmd
                int s = cmd.roles.size()
                /*if(aux.roles) {
                    int sr = aux.roles.size()
                    def ro;

                    for (int i = 0; i < sr; i++) {
                        ro = aux.roles[i];
                        if (!cmd.roles.contains(ro)) {
                            aux.removeFromRoles(ro);
                        }
                    }
                }*/

                for (int i = 0; i < s; i++) {
                    def r = ERole.get(cmd.roles.get(i))
                    if(r){
                        aux.addToRoles(r)
                    }
                    else{
                        return false
                        //todo: inform this role isn't present
                    }
                }
            }


            aux.save flush: true
            return aux
        }
        //todo: inform about the error
        return false
    }

    /**
     * Shows some User info
     * @param id Identifier of the user that is going to be shown
     * @return A UserBean entity with the role's info or false if none user is found
     */
    def show (long id){
        def e = Optional.ofNullable(EUser.get(id))
        if(e.isPresent()){
            def i = e.value
            if(i){
                return new UserBean(username: i.username, email: i.email, name: i.name)
            }
        }
        //todo: inform about the error
        return false
    }

    /**
     * Deletes some user's info
     * @param id Identifier of the user that is going to be deleted
     * @return <code>true</code> or <code>false</code> depending on the result of the operation
     */
    def delete(long id) {
        def e = EUser.get(id);
        if(e){
            e.delete();
            return true
        }
        //todo: inform about the error
        return false
    }
}
