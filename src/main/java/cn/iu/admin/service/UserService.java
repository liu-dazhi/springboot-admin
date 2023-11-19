package cn.iu.admin.service;

import cn.iu.admin.VO.UserVO;
import cn.iu.admin.entity.Content;
import cn.iu.admin.entity.GroupName;
import cn.iu.admin.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService extends IService<Content> {

    List<GroupName> getGroupData(User user);

    int register(User user);

    User login(User user, HttpServletRequest request);

    void checkGroup(User user);

    GroupName selectGroupListById(String id);

    UserVO selectUserVOByUsername(String username);
}
