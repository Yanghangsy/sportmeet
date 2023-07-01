package com.yang.service.Impl;


import com.yang.mapper.GroupMapper;
import com.yang.pojo.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class GroupServiceImpl implements GroupMapper {

    private GroupMapper groupMapper;
    @Autowired
    public GroupServiceImpl(GroupMapper groupMapper) {
        this.groupMapper = groupMapper;
    }


    @Override
    public int addGroup(Group group) {
        return groupMapper.addGroup(group);
    }

    @Override
    public int deleteGroup(int gnum) {
        return groupMapper.deleteGroup(gnum);
    }

    @Override
    public int updateGroup(Group group) {
        return groupMapper.updateGroup(group);
    }

    @Override
    public Group queryGroupById(int gnum) {
        return groupMapper.queryGroupById(gnum);
    }

    @Override
    public List<Group> queryGroupList() {
        return groupMapper.queryGroupList();
    }
}
