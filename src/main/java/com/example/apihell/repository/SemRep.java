package com.example.apihell.repository;
import java.util.List;
import com.example.apihell.model.Sem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SemRep extends JpaRepository<Sem, String> {

    @Query(value = "select subj from sem where spec = ?1 and semnum = ?2", nativeQuery = true)
    List<String> getSubjectsBySpecAndSemNum(String spec, int semNum);
}
