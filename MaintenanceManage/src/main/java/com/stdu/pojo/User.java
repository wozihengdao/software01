package com.stdu.pojo;

public class User {
    private String username; // 用户名（对应varchar(20)）
    private String password; // 密码（对应varchar(20)）
    private String type;     // 类型（对应varchar(10)）

    public User(String username, String password, String type) {
        this.username = username;
        this.password = password;
        this.type = type;
    }

    // Getter & Setter 方法
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    // toString 方法（可选，建议添加）
    @Override
    public String toString() {
        return "TbUser{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

}
