package com.yang.mapper;

import com.yang.pojo.Group;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author hang yang
 * @create 2022-01-04 20:55
 */
@Mapper
@Repository
public interface GroupMapper {
    /**
     * 增加一个系团队
     * @param group
     * @return
     */
     int addGroup(Group group);

    /**
     * 根据id删除一个系团队
     * @param gnum
     * @return
     */
     int deleteGroup(int gnum);

    /**
     * 更新系团队
     * @param group
     * @return
     */
     int updateGroup(Group group);

    /**
     * @description: 更据id查询一个系团队
     * @param  gnum
     * @return com.yang.sportmeet.pojo.Group
     *
     */
     Group queryGroupById(int gnum);

   /**
    * @description:
    * @return java.util.List<com.yang.sportmeet.pojo.Group>
    *
    */
     List<Group> queryGroupList();
}
