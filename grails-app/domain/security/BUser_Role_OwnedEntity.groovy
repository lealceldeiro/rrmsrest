package security

class BUser_Role_OwnedEntity implements Serializable{

    EUser user

    BRole role

    EOwnedEntity ownedEntity

    static belongsTo = [user: EUser]

    static constraints = {
        id unique: true
    }

    static mapping = {
        id composite: ['user', 'role', 'ownedEntity']
        version false
        table: 'buser_role_owned_entity'

        role lazy: false
    }

    /**
     * Adds a roles to a user over a specific entity
     * @param user User to add role to
     * @param role Role to be added
     * @param ownedEntity Entity which user will have the assigned role
     * @return
     */
    static BUser_Role_OwnedEntity addRole(EUser user, BRole role, EOwnedEntity ownedEntity) {
        if(findByUserAndRoleAndOwnedEntity(user, role, ownedEntity)){
            return null
            //todo: inform error
        }
        def e = new BUser_Role_OwnedEntity(user: user, role: role, ownedEntity: ownedEntity)
        e.save(flush: true, insert: true)
    }

    /**
     * Remove a specific roles from a user over an entity
     * @param user User to remove roles from
     * @param role Role to be removed
     * @param ownedEntity Entity to remove roles from
     * @return
     */
    static def removeRole(EUser user, BRole role, EOwnedEntity ownedEntity){
        executeUpdate(
                "delete from BUser_Role_OwnedEntity ur where ur.user = :user and ur.role = :role and ur.ownedEntity = :entity",
                [user: user, role: role, entity: ownedEntity]
        )
    }
    /**
     * Removes a list of roles assigned to a user from some entity
     * @param user User to remove roles from
     * @param roles Roles to be removed
     * @param ownedEntity Entity to remove roles from
     * @return
     */
    static def removeRoles(EUser user, List<BRole> roles, EOwnedEntity ownedEntity){
        executeUpdate(
                "delete from BUser_Role_OwnedEntity ur where ur.user = :user and ur.role in (:roles) and ur.ownedEntity = :entity",
                [user: user, roles: roles, entity: ownedEntity]
        )
    }
    /**
     * Removes all Roles from an users over an specific entity
     * @param User user to remove roles from
     * @param ownedEntity Entity to remove roles from
     * @return
     */
    static def removeAllRolesFrom(EUser user, EOwnedEntity ownedEntity){
        executeUpdate(
                "delete from BUser_Role_OwnedEntity ur where ur.user = :user and ur.ownedEntity = : entity",
                [user: user, entity: ownedEntity]
        )
    }
    /**
     * Removes all Roles from an users over all entities
     * @param User user to remove roles from
     * @return
     */
    static def removeAllRolesFromAll(EUser user){
        executeUpdate("delete from BUser_Role_OwnedEntity ur where ur.user = :user",[user: user])
    }

    /**
     * Returns all user's roles in a specific entity
     * @param id user's id
     * @param ownedEntityId Owned entity id
     * @param params filter params
     * @return
     */
    static def getRolesByUser(Long id, Long ownedEntityId, Map params){
        createCriteria().list(params) {

            projections {
                property("role")
            }

            user {
                eq "id", id
            }

            if(ownedEntityId != null){
                ownedEntity {
                    eq "id", ownedEntityId
                }
            }

        }
    }
}
