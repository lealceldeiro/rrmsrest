package security

class ERole {

    String label
    String description
    boolean active

    static belongsTo = [EUser]
    static hasMany = [users: EUser]

    static constraints = {
        label nullable: false, blank: false, unique: true
        active nullable: true
        description nullable: true
    }
}
