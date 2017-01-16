package security

class BRole implements Serializable {

    String label
    String description
    Boolean enabled

    static hasMany = [permissions: BRole_Permission]

    static constraints = {
        label nullable: false, blank: false, unique: true
        enabled nullable: true
        description nullable: true
    }
}
