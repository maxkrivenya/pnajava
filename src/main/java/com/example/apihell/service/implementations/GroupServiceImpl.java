package com.example.apihell.service.implementations;

import com.example.apihell.components.CacheComponent;
import com.example.apihell.model.Group;
import com.example.apihell.repository.GroupRepository;
import com.example.apihell.service.GroupService;
import org.springframework.stereotype.Service;

@Service
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;
    private final CacheComponent cache;

    public GroupServiceImpl(GroupRepository groupRepository, CacheComponent cache) {
        this.groupRepository = groupRepository;
        this.cache = cache;

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
            cache.remove(CacheComponent.GROUP_CACHE_KEY + id);
        }
        public Group save(Group group){
            cache.remove(CacheComponent.GROUP_CACHE_KEY + group.getId());
            cache.put(CacheComponent.GROUP_CACHE_KEY + group.getId(), group);
            return groupRepository.save(group);
        }
    public void logCache(){
        cache.log();
    }
    }