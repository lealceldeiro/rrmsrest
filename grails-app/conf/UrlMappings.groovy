class UrlMappings {

	static mappings = {

        "/user/list"(controller: "UserController", action: "list")
        "/user/create"(controller: "UserController", action: "create")


        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "500"(view:'/error')
	}
}
