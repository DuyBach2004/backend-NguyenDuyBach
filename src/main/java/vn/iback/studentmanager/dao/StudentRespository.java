package vn.iback.studentmanager.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.iback.studentmanager.entity.roles;
import vn.iback.studentmanager.entity.student;
@Repository
public interface StudentRespository extends JpaRepository<student, String> {
//        public student findStudentById(String id);
}
