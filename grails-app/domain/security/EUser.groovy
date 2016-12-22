package security

class EUser implements Serializable {

    String username
    String email
    String name
    String password

    static hasMany = [roles: EUser_Role]

    static constraints = {
        username nullable: false, unique: true, blank: false
        email nullable: true, unique: true, blank: false
        name nullable: false, blank: false
        password nullable: false, blank: false
    }

}
