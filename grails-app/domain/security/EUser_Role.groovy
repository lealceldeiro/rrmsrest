package security

class EUser_Role implements Serializable{

    EUser user

    ERole role

    static belongsTo = [user: EUser]

    static constraints = {
        id unique: true
    }

    static mapping = {
        id composite: ['user', 'role']
        version false
        table: 'euser_roles'

        role lazy: false
    }

    static EUser_Role addRole(EUser user, ERole role) {
        if(findByUserAndRole(user, role)){
            return null
            //todo: inform error
        }
        def e = new EUser_Role(user: user, role: role)
        e.save(flush: true, insert: true)
    }

    static def removeRole(EUser user, ERole role){
        executeUpdate("delete from EUser_Role ur where ur.user = :user and ur.role = :role", [user: user, role: role])
    }
    static def removeRoles(EUser user, List<ERole> roles){
        executeUpdate("delete from EUser_Role ur where ur.user = :user and ur.role in (:roles)", [user: user, roles: roles])
    }
    static def removeAllRolesFrom(EUser user){
        executeUpdate("delete from EUser_Role ur where ur.user = :user", [user: user])
    }
}
