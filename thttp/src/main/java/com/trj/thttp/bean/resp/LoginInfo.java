package com.trj.thttp.bean.resp;

public class LoginInfo {

    private String token;

    private String id;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "LoginInfo{" +
                "token='" + token + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
