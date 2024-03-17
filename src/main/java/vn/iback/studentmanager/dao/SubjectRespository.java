package vn.iback.studentmanager.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.iback.studentmanager.entity.subjects;

@Repository
public interface SubjectRespository extends JpaRepository<subjects,String> {
}
