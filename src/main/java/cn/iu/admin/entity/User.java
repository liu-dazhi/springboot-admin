package cn.iu.admin.entity;

import lombok.Data;

@Data
public class User {
    private String id;

    private String username;

    private String password;

    private Integer role;

    private String groupId;

    private String realName;
}
