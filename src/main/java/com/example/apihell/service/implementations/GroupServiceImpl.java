package com.example.apihell.service.implementations;

import com.example.apihell.components.CacheComponent;
import com.example.apihell.model.Group;
import com.example.apihell.model.Professor;
import com.example.apihell.repository.GroupRepository;
import com.example.apihell.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private final GroupRepository groupRepository;
    private final CacheComponent cache;

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository, CacheComponent cache) {
        this.groupRepository = groupRepository;
        this.cache = cache;

    }

    public List<Group> findAll(){
        String cacheKey = CacheComponent.GROUP_CACHE_KEY + CacheComponent.GET_ALL;
        List<Group> groups = (List<Group>) cache.get(cacheKey);
        if(groups == null){
            groups = groupRepository.findAll();
            cache.put(cacheKey, groups);
        }
        return groups;
    }

    public Group getGroupById(String id){
        String cacheId = CacheComponent.GROUP_CACHE_KEY + id;
        Group group = (Group) cache.get(cacheId);
        if(group == null){
            group = groupRepository.getGroupById(id);
            cache.put(cacheId,group);
        }
        return group;
    }

    public void deleteGroupById(String id){
            groupRepository.deleteById(id);
        cache.remove(CacheComponent.GROUP_CACHE_KEY + CacheComponent.GET_ALL);
        cache.remove(CacheComponent.GROUP_CACHE_KEY + id);
    }

    public Group save(Group group){
        cache.remove(CacheComponent.GROUP_CACHE_KEY + group.getId());
        cache.remove(CacheComponent.GROUP_CACHE_KEY + CacheComponent.GET_ALL);
        cache.put(CacheComponent.GROUP_CACHE_KEY + group.getId(), group);
        return groupRepository.save(group);
    }

    }
