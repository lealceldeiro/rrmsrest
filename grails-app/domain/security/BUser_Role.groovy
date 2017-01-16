package security

class BUser_Role implements Serializable{

    EUser user

    BRole role

    static belongsTo = [user: EUser]

    static constraints = {
        id unique: true
    }

    static mapping = {
        id composite: ['user', 'role']
        version false
        table: 'buser_role'

        role lazy: false
    }

    static BUser_Role addRole(EUser user, BRole role) {
        if(findByUserAndRole(user, role)){
            return null
            //todo: inform error
        }
        def e = new BUser_Role(user: user, role: role)
        e.save(flush: true, insert: true)
    }

    static def removeRole(EUser user, BRole role){
        executeUpdate("delete from BUser_Role ur where ur.user = :user and ur.role = :role", [user: user, role: role])
    }
    static def removeRoles(EUser user, List<BRole> roles){
        executeUpdate("delete from BUser_Role ur where ur.user = :user and ur.role in (:roles)", [user: user, roles: roles])
    }
    static def removeAllRolesFrom(EUser user){
        executeUpdate("delete from BUser_Role ur where ur.user = :user", [user: user])
    }

    static def getRolesByUser(Long id, Map params){
        createCriteria().list(params) {

            projections {
                property("role")
            }

            user {
                eq "id", id
            }

        }
    }
}
