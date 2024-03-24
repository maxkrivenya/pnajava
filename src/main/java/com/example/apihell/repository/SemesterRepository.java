package com.example.apihell.repository;

import com.example.apihell.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
public interface SemesterRepository extends JpaRepository<Subject, String> {

}
