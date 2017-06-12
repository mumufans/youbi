package com.youbi.app.model;

public class Role {
    private Integer roleId;

    private String roleName;

    private String roleZhname;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getRoleZhname() {
        return roleZhname;
    }

    public void setRoleZhname(String roleZhname) {
        this.roleZhname = roleZhname == null ? null : roleZhname.trim();
    }
}