package com.example.apihell.repository;

import com.example.apihell.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GroupRepository extends JpaRepository<Group, String> {
    @Query(value="SELECT * FROM group WHERE id=?1", nativeQuery=true)
    public String getDegreeById(String id);
}

