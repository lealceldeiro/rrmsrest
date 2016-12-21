package security

import command.SearchCommand
import command.security.role.RoleCommand
import mapping.security.RoleBean

import grails.transaction.Transactional

@Transactional
class RoleService {

    /**
     * Search a role(s) according to optional parameters
     * @param cmd       [optional] Grails command containing the parameters for searching the role(s)
     *                  q: Criteria for searching
     * @param params    [optional] Parameters for paging the result
     * @return          A json containing a list of roles with the following structure if the operation was successful
     * <p><code>{success: true|false, items:[<it1>,...,<itn>], total: <totalCount>}</code></p>
     */
    def search(SearchCommand cmd, Map params) {
        Map response = [:]

        def list = ERole.createCriteria().list(params) {
            order("active", "desc")
            if(cmd.q) {
                or{
                    ilike("label", "%${cmd.q}%")
                    ilike("description", "%${cmd.q}%")
                }
            }

        }

        def mapped = [];
        list.each {
            mapped << new RoleBean(id: it.id, label: it.label, description: it.description, active: it.active)
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
    def save(RoleCommand cmd, long id) {
        ERole e = cmd()
        if(e.validate()){
            ERole aux = null;
            if(id != null){
                aux = ERole.get(id)
            }
            if(!aux){
                aux = e;
            }
            else{
                aux.id = e.id;
                aux.active = e.active;
                aux.description = e.description;
                aux.label = e.label;
            }

            aux.save flush: true
            return aux
        }
        return false
    }

    /**
     * Shows some role info
     * @param id Identifier of the role that is going to be shown
     * @return A json containing the role's info if the operation was successful with the following structure
     * <p><code>{success: true|false, items:[<it1>,...,<itn>], total: <totalCount>}</code></p>
     */
    def show (long id){
        def e = Optional.ofNullable(ERole.get(id))
        if(e.isPresent()){
            def i = e.value
            if(i){
                return new RoleBean(id: i.id, label: i.label, description: i.description, active: i.active)
            }
        }
        return false
    }

    /**
     * Deletes some role's info
     * @param id Identifier of the role that is going to be deleted
     * @return A json indicting whether the operation was successful with the following structure
     * <p><code>{success: true|false}</code></p>
     */
    def delete(long id) {
        def e = ERole.get(id);
        if(e){
            e.delete();
            return true
        }
        return false
    }
}
