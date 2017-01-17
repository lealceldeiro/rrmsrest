package configuration

class BConfiguration implements Serializable{

    /**
     * When default admin account is reconfigured this is true
     */
    boolean defaultAdminUnSetup
    boolean defaultAdminUnSetupConfigured
    boolean defaultUserCreated

    static constraints = {
        defaultAdminUnSetup(nullable: false, blank: false)
        defaultAdminUnSetupConfigured(nullable: false, blank: false)
        defaultUserCreated(nullable: false, blank: false)
    }
}
