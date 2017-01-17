class BootStrap {

    def permissionService
    def roleService
    def configurationService

    def userService

    def init = { servletContext ->

        //todo: check out this initial config latter in time when authentication is working properly
        if(!configurationService.isThereAnyConfiguration()){
            configurationService.createDefaultConfig()
        }

        if(permissionService.noPermissionInDB()){ //this will only be true at the begging of the application start (db integrity is required)
            boolean pok = permissionService.createDefaultPermissions()
            if(pok){
                if(roleService.noRolesInDB()){
                    roleService.createDefaultAdminRole()
                }
            }
        }

        if(!configurationService.isDefaultAdminUnSetupChanged()){
            if(!configurationService.isDefaultUserCreated()){
                def u = userService.createDefaultUser()
                configurationService.setDefaultUserCreated()
                userService.addDefaultRoleToUser(u)
            }
        }

    }
    def destroy = {
    }
}
