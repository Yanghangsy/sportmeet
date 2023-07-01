package com.yang.service;

import com.yang.pojo.Group;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GroupService {
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
