class UrlMappings {

        static mappings = {

                //generic
                "/$controller/$action?/$id?(.$format)?"{
                        constraints {
                                // apply constraints here
                        }
                }



                //USER...
                "/api/user"                     (controller: "user")                            {action= [GET: "search", PUT: "create"]}
                "/api/user/$id"                 (controller: "user")                            {action= [GET: "show", POST: "update", DELETE: "delete"]}
                "/api/user/$id/roles"           (controller: "user", action: "roles")

                //ROLE...
                "/api/role"                     (controller: "role")                            {action= [GET: "search", PUT: "create"]}
                "/api/role/$id"                 (controller: "role")                            {action= [GET: "show", POST: "update", DELETE: "delete"]}
                "/api/role/$id/permissions"     (controller: "role", action: "permissions")

                //PERMISSION
                "/api/permission"               (controller: "permission")                      {action= [GET: "search"]}


                //default
                "/"(view:"/index")
                "500"(view:'/error')
        }
}
