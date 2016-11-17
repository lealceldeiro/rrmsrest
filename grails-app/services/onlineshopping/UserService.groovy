package onlineshopping

import command.security.UserCommand
import grails.transaction.Transactional
import security.EUser

@Transactional
class UserService {

    def create(UserCommand cmd) {
        final EUser u = cmd()
        if(u.validate()){
            u.save flush: true;
            return u;
        }
        return false
    }
}
