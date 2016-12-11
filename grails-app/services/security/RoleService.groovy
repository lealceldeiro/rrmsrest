package security

import command.security.RoleCommand
import mapping.security.RoleBean

import grails.transaction.Transactional

@Transactional
class RoleService {

    def search(Map params) {
        def roles = ERole.list(params)
        def mapped = [];
        roles.each {
            mapped << new RoleBean(id: it.id, label: it.label, description: it.description, active: it.active)
        }

        return mapped
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
