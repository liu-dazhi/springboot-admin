package cn.iu.admin.service.impl;

import cn.iu.admin.entity.Content;
import cn.iu.admin.mapper.UserMapper;
import cn.iu.admin.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, Content> implements UserService {

    @Autowired
    private UserMapper userMapper;
}
