package security

import command.security.UserCommand
import mapping.UserBean

import grails.transaction.Transactional

@Transactional
class UserService {

    def search(Map params) {
        def entities = EUser.list(params)
        def mapped = [];
        entities.each {
            mapped << new UserBean(username: it.username, name: it.name, password: it.password)
        }

        return mapped
    }

    def create(UserCommand cmd) {
        final EUser u = cmd()
        if(u.validate()){
            u.save flush: true;
            return u;
        }
        return false
    }
}
