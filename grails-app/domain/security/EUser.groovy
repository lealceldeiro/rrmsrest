package security

class EUser {

    String username
    String name
    String password

    static constraints = {
        username nullable: false, unique: true
        name nullable: false, blank: false
        password nullable: false, blank: false
    }

}
