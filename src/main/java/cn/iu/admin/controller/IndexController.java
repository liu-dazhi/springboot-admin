package cn.iu.admin.controller;
import cn.iu.admin.entity.Content;
import cn.iu.admin.result.Result;
import cn.iu.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Wrapper;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/")
public class IndexController {

    @Autowired
    private UserService userService;

    @CrossOrigin
    @GetMapping(value = "/getData")
    public Result<List<Content>> getData() {
        List<Content> list = userService.list();

        //return list;
        return new Result<>(200, "Ok", list);
    }

    @CrossOrigin
    @PostMapping(value = "/insertData")
    public Result<List<Content>> insertData(@RequestBody Content content) {
        String uuid = String.valueOf(UUID.randomUUID());//最常用
        content.setId(uuid);
        userService.save(content);
        return new Result<>(200, "Ok", null);
    }

    @CrossOrigin
    @RequestMapping(value = "/deleteData")
    public Result<?> deleteData (@RequestBody Content content) {
        userService.removeById(content.getId());

        return new Result<>(200, "Ok", null);
    }

    @CrossOrigin
    @RequestMapping(value = "/updateData")
    public Result<?> updateData (@RequestBody Content content) {
        userService.updateById(content);
        return new Result<>(200, "Ok", null);
    }


}
