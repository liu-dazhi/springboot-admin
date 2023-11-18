package cn.iu.admin.controller;

import cn.iu.admin.entity.User;
import cn.iu.admin.result.Result;
import cn.iu.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    private UserService userService;


    @GetMapping("/getGroupData")
    private Result<?> getGroupData(User user){
        return Result.ok(userService.getGroupData(user));
    }

}
