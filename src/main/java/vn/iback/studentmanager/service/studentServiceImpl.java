package vn.iback.studentmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vn.iback.studentmanager.dao.StudentRespository;
import vn.iback.studentmanager.entity.StudentIntoNotebook;
import vn.iback.studentmanager.entity.student;
import vn.iback.studentmanager.entity.user;
@Service
public class studentServiceImpl implements studentService {
    StudentRespository studentRespository;
    @Autowired
    public studentServiceImpl(StudentRespository studentRespository) {
        this.studentRespository = studentRespository;
    }


    @Override
    public void save(student student) {
            studentRespository.save(student);
    }

    @Override
    public void update(student student) {
            studentRespository.saveAndFlush(student);
    }

    @Override
    public student findStudentById(String id) {
        return studentRespository.getById(id);
    }

    @Override
    public void deleteById(String id) {

    }
}
