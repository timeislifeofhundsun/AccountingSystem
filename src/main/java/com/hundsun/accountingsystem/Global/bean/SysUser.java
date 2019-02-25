package com.hundsun.accountingsystem.Global.bean;

import java.util.List;


public class SysUser {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String compangy;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompangy() {
		return compangy;
	}

	public void setCompangy(String compangy) {
		this.compangy = compangy;
	}



	private List<SysRole> roles;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }

}
