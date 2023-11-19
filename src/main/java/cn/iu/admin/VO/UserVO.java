package cn.iu.admin.VO;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class UserVO {
    private String id;

    private String username;

    private String password;

    private String role;

    private String groupId;

    private String realname;

    @TableField(exist = false)
    private String iniCode;

    @TableField(exist = false)
    private String groupName;
}
