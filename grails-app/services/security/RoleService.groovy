package security

import command.SearchCommand
import command.security.role.RoleCommand
import mapping.security.RoleBean

import grails.transaction.Transactional

@Transactional
class RoleService {

    def search(SearchCommand cmd, Map params) {
        Map response = [:]

        def list = ERole.createCriteria().list(params) {
            order("active", "desc")
            if(cmd.criteria) {
                or{
                    ilike("label", "%${cmd.criteria}%")
                    ilike("description", "%${cmd.criteria}%")
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

    def show (long id){
        Optional.ofNullable(ERole.get(id))
    }

    def delete(long id) {
        def e = ERole.get(id);
        if(e){
            e.delete();
            return true
        }
        return false
    }
}
