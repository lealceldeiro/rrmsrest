class UrlMappings {

        static mappings = {

                "/$controller/$action?/$id?(.$format)?"{
                        constraints {
                                // apply constraints here
                        }
                }


                "/api/user/search"                            (controller: "user", action: "search")
                "/api/user/save/$id?"                         (controller: "user", action: "save")
                "/api/user/delete/$id?"                       (controller: "user", action: "delete")
                "/api/user/show/$id?"                         (controller: "user", action: "show")
                "/api/user/roles/$id?"                        (controller: "user", action: "roles")

                "/api/role/search"                            (controller: "role", action: "search")
                "/api/role/save/$id?"                         (controller: "role", action: "save")
                "/api/role/delete/$id?"                       (controller: "role", action: "delete")
                "/api/role/show/$id?"                         (controller: "role", action: "show")
                "/api/role/permissions/$id?"                  (controller: "role", action: "permissions")



                "/"(view:"/index")
                "500"(view:'/error')
        }
}
