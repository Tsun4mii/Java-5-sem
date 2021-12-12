package bstu.shust.jprojectv2.dto;

import bstu.shust.jprojectv2.models.Role;

public class UserResponse {
    private Long id;
    private String login;
    private Role role;

    public UserResponse(Long id, String login, Role role) {
        this.id = id;
        this.login = login;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
