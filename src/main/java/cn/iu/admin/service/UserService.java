package cn.iu.admin.service;

import cn.iu.admin.entity.Content;
import cn.iu.admin.entity.GroupName;
import cn.iu.admin.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface UserService extends IService<Content> {

    List<GroupName> getGroupData(User user);

    int register(User user);

    User login(User user);
}
