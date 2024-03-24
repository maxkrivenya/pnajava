package com.example.apihell.repository;

import com.example.apihell.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
public interface GroupRepository extends JpaRepository<Group, String> {
}

