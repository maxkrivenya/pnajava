package com.example.apihell.repository;
import java.util.List;

import com.example.apihell.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SemesterRepository extends JpaRepository<Subject, String> {

}
