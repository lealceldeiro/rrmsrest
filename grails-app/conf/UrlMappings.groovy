class UrlMappings {

	static mappings = {

        "/user/search"(controller: "UserController", action: "search")
        "/user/create"(controller: "UserController", action: "create")

        "/role/search"(controller: "RoleController", action: "search")


        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "500"(view:'/error')
	}
}
