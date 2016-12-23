class UrlMappings {

        static mappings = {

                "/user/search"                          (controller: "UserController", action: "search")
                "/user/save/$id"                        (controller: "UserController", action: "save")
                "/user/delete/$id"                      (controller: "UserController", action: "delete")
                "/user/show/$id"                        (controller: "UserController", action: "show")
                "/user/roles/$id"                       (controller: "UserController", action: "roles")

                "/role/search"                          (controller: "RoleController", action: "search")
                "/role/save/$id"                        (controller: "RoleController", action: "save")
                "/role/delete/$id"                      (controller: "RoleController", action: "delete")
                "/role/show/$id"                        (controller: "RoleController", action: "show")


                "/$controller/$action?/$id?(.$format)?"{
                        constraints {
                                // apply constraints here
                        }
                }

                "/"(view:"/index")
                "500"(view:'/error')
        }
}
