class UrlMappings {

	static mappings = {

        "/user/search"(controller: "UserController", action: "search")
        "/user/save"(controller: "UserController", action: "save")
        "/user/delete"(controller: "UserController", action: "delete")
        "/user/show"(controller: "UserController", action: "show")

        "/role/search"(controller: "RoleController", action: "search")
        "/role/save"(controller: "RoleController", action: "save")
        "/role/delete"(controller: "RoleController", action: "delete")
        "/role/show"(controller: "RoleController", action: "show")


        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "500"(view:'/error')
	}
}
