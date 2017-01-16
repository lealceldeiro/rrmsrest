package security

import grails.transaction.Transactional
import nomenclator.EnumPermission

@Transactional
class PermissionService {

    boolean check() {
        BPermission.count() < 1
    }

    def insert(){
        List<EnumPermission> ps = EnumPermission.values()

        ps.each {it->
            new BPermission(name: it.toString(), label: it.toString().replace("_", " ")).save(flush: true)
        }
        return true
    }
}
