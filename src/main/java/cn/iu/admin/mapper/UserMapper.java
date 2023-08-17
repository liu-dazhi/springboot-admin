package cn.iu.admin.mapper;

import cn.iu.admin.entity.Content;
import cn.iu.admin.entity.GroupName;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<Content> {

    void insertGroupData(GroupName group);

    List<GroupName> selectListByUserId(String userId);

}
