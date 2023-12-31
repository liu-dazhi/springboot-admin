package cn.iu.admin.mapper;

import cn.iu.admin.VO.UserVO;
import cn.iu.admin.entity.Content;
import cn.iu.admin.entity.GroupName;
import cn.iu.admin.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<Content> {

    void insertGroupData(GroupName group);
    List<GroupName> selectGroupListByUserId(String userId);
    GroupName selectGroupListById(String id);
    int register(User user);
    User selectUserByUsername(String username);
    UserVO selectUserVOByUsername(String username);

    void checkGroup(User user);
    int countContentByGroupId(User user);
}
