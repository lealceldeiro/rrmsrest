class UrlMappings {

	static mappings = {

        "/user/search"(controller: "UserController", action: "search")
        "/user/create"(controller: "UserController", action: "create")

        "/role/search"(controller: "RoleController", action: "search")
        "/role/save"(controller: "RoleController", action: "save")
        "/role/delete"(controller: "RoleController", action: "delete")


        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "500"(view:'/error')
	}
}
