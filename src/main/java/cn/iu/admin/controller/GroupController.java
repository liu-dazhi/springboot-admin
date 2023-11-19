package cn.iu.admin.controller;

import cn.iu.admin.entity.User;
import cn.iu.admin.enums.RstStatus;
import cn.iu.admin.result.Result;
import cn.iu.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    private UserService userService;


    @GetMapping("/getGroupData")
    private Result<?> getGroupData(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        return Result.ok(userService.getGroupData(user));
    }

    @PostMapping("/checkGroup")
    private Result<?> checkGroup(@RequestParam("groupId") String groupId, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (ObjectUtils.isEmpty(user)) {
            return Result.error(RstStatus.noLogin);
        }
        user.setGroupId(groupId);
        userService.checkGroup(user);
        return Result.ok();
    }
}
