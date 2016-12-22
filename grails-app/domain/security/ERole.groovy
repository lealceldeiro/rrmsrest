package security

class ERole implements Serializable {

    String label
    String description
    boolean active

    static constraints = {
        label nullable: false, blank: false, unique: true
        active nullable: true
        description nullable: true
    }
}
