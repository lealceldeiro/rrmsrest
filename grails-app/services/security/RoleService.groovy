package security

import command.security.RoleCommand
import mapping.RoleBean

import grails.transaction.Transactional

@Transactional
class RoleService {

    def search(Map params) {
        def roles = ERole.list(params)
        def mapped = [];
        roles.each {
            mapped << new RoleBean(label: it.label, description: it.description, active: it.active)
        }

        return mapped
    }

    def create(RoleCommand cmd) {
        final ERole e = cmd()
        if(e.validate()){
            e.save flush: true;
            return e;
        }
        return false
    }
}
