package cn.iu.admin.service.impl;

import cn.iu.admin.VO.UserVO;
import cn.iu.admin.common.Constant;
import cn.iu.admin.entity.Content;
import cn.iu.admin.entity.GroupName;
import cn.iu.admin.entity.User;
import cn.iu.admin.exception.BusinessException;
import cn.iu.admin.mapper.UserMapper;
import cn.iu.admin.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, Content> implements UserService {

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<GroupName> getGroupData(User user) {
        return userMapper.selectGroupListByUserId(ObjectUtils.isEmpty(user) ? "" : user.getId());
    }

    @Override
    public int register(User user) {
        if (ObjectUtils.isEmpty(user) || StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword())){
            throw new BusinessException("参数缺失，账户名或密码不能为空");
        }
        // 校验是否重名
        User num = userMapper.selectUserByUsername(user.getUsername());
        if (!ObjectUtils.isEmpty(num)){
            throw new BusinessException(String.format("用户名:%s重复,请重新输入",user.getUsername()));
        }
        String encode = passwordEncoder.encode(user.getPassword());
        user.setPassword(encode);
        user.setRole(Constant.DEFAULT);
        return userMapper.register(user);
    }

    @Override
    public User login(User user, HttpServletRequest request) {
        if (ObjectUtils.isEmpty(user) || StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword())){
            throw new BusinessException("参数缺失，账户名或密码不能为空");
        }
        User num = userMapper.selectUserByUsername(user.getUsername());
        if (ObjectUtils.isEmpty(num)){
            throw new BusinessException("用户名或密码错误，请重新输入");
        }
        if (!passwordEncoder.matches(user.getPassword(), num.getPassword())){
            throw new BusinessException("用户名或密码错误，请重新输入");
        }
        GroupName groupName = userMapper.selectGroupListById(num.getGroupId());
        num.setGroupName(ObjectUtils.isEmpty(groupName) ? "" : groupName.getName());
        request.getSession().setAttribute("user", num);

        return num;
    }

    @Override
    public void checkGroup(User user) {
        if (userMapper.countContentByGroupId(user) < 1){
            throw new BusinessException("该分组无数据，无法选择");
        }
        userMapper.checkGroup(user);
    }

    @Override
    public GroupName selectGroupListById(String id) {
        return userMapper.selectGroupListById(id);
    }

    @Override
    public UserVO selectUserVOByUsername(String username) {
        return userMapper.selectUserVOByUsername(username);
    }

}
