package vn.iback.studentmanager.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.iback.studentmanager.entity.staff;
@Repository
public interface TeacherRespository extends JpaRepository<staff,String> {

}
