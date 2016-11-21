package security

class ERole {

    String label
    String description
    boolean active

    static constraints = {
        label nullable: false, unique: true
    }
}
