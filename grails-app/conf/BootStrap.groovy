class BootStrap {

    def permissionService

    def init = { servletContext ->

        if(permissionService.check()){ //this will only be true at the begging of the application start (db integrity is required)
            permissionService.insert()
        }

    }
    def destroy = {
    }
}
