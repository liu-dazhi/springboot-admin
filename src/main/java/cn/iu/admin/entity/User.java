package cn.iu.admin.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.ToString;


@Data
@ToString
public class User {
    private String id;

    private String username;

    private String password;

    private String role;

    private String groupId;

    private String realname;

    @TableField(exist = false)
    private String iniCode;
}
