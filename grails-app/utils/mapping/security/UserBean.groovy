package mapping.security

/**
 * Created by Asiel on 11/18/2016.
 */
class UserBean {
    Integer id
    String username
    String email
    String name
    String password
    boolean enabled
    List<RoleBean> roles
}
