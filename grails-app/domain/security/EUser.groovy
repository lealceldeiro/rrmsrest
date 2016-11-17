package security

class EUser {

    String usernme
    String name
    String password

    static constraints = {
        usernme nullable: false, unique: true
        name nullable: false, blank: false
        password nullable: false, blank: false
    }

}
