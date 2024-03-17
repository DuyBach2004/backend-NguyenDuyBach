package vn.iback.studentmanager.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.iback.studentmanager.entity.specialization;
@Repository
public interface SpecializationRespository extends JpaRepository<specialization,String> {
}
