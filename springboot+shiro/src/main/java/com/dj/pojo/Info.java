package com.dj.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author joker_dj
 * @create 2020-04-13日 11:52
 */
@Data
public class Info implements Serializable {
    private static final long serialVersionUID = 976666160198596263L;

    private String username;

    private String password;

    private String perm;

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

    @Override
    public String toString() {
        return "Info{" +
                "username=" + username +
                "password=" + password +
                '}';
    }
}
