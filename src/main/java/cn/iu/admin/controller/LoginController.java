package cn.iu.admin.controller;

import cn.iu.admin.VO.UserVO;
import cn.iu.admin.common.Constant;
import cn.iu.admin.entity.Content;
import cn.iu.admin.entity.User;
import cn.iu.admin.enums.RstStatus;
import cn.iu.admin.exception.BusinessException;
import cn.iu.admin.result.Result;
import cn.iu.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static cn.iu.admin.exception.BusinessException.throwExceptionWithNull;

@RestController
@RequestMapping("/account")
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result<?> register(@RequestBody User user) {
        if (!Constant.INICODE.equals(user.getIniCode())){
            return Result.error(RstStatus.iniCodeError);
        }
        // 丑陋的代码
        int register = userService.register(user);
        if (register == 1){
            return Result.ok();
        }else{
            return Result.error(RstStatus.serviceError);
        }

    }
    @PostMapping("/login")
    public Result<?> login(@RequestBody User user, HttpServletRequest request) {
        User login = userService.login(user, request);
        return Result.ok("登陆成功",login);
    }

    @GetMapping("/checkLogin")
    public Result<?> checkLogin(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        UserVO userVO = userService.selectUserVOByUsername(ObjectUtils.isEmpty(user) ? "" : user.getUsername());
        return Result.ok(userVO);
    }

}
