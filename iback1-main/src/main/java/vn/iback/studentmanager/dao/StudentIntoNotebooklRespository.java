package vn.iback.studentmanager.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.iback.studentmanager.entity.StudentIntoNotebook;
@Repository
public interface StudentIntoNotebooklRespository extends JpaRepository<StudentIntoNotebook,Integer> {

}
