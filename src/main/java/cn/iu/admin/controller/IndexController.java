package cn.iu.admin.controller;

import cn.iu.admin.common.Constant;
import cn.iu.admin.entity.Content;
import cn.iu.admin.entity.GroupName;
import cn.iu.admin.mapper.UserMapper;
import cn.iu.admin.result.Result;
import cn.iu.admin.service.UserService;
import cn.iu.admin.service.impl.UserServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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

    @Autowired
    private UserMapper userMapper;

    /**
     * 根据分组id查询选项
     * @param groupId
     * @return
     */
    @CrossOrigin
    @GetMapping(value = "/getData")
    public Result<List<Content>> getData(@RequestParam(value = "groupId",defaultValue = "") String groupId) {
        QueryWrapper<Content> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("group_id", "".equals(groupId) ? Constant.DEFAULTGROUP : groupId);
        List<Content> list = userService.list(queryWrapper);
        return new Result<>(200, "Ok", list);
    }

    /**
     * 查询分组
     */



    /**
     * 插入选项
     * @param content
     * @return
     */
    @CrossOrigin
    @PostMapping(value = "/insertData")
    public Result<List<Content>> insertData(@RequestBody Content content) {
        String uuid = String.valueOf(UUID.randomUUID());
        content.setId(uuid);
        userService.save(content);
        return new Result<>(200, "Ok", null);
    }

    /**
     * 插入分组
     * @param group
     * @return
     */
    @PostMapping(value = "/insertGroupData")
    public Result<List<Content>> insertGroupData(@RequestBody GroupName group) {
        userMapper.insertGroupData(group);
        return new Result<>(200, "Ok", null);
    }

    @CrossOrigin
    @RequestMapping(value = "/deleteData")
    public Result<?> deleteData(@RequestBody Content content) {
        userService.removeById(content.getId());

        return new Result<>(200, "Ok", null);
    }

    @CrossOrigin
    @RequestMapping(value = "/updateData")
    public Result<?> updateData(@RequestBody Content content) {
        userService.updateById(content);
        return new Result<>(200, "Ok", null);
    }



}
