package vn.iback.studentmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.iback.studentmanager.dao.TeacherRespository;
import vn.iback.studentmanager.entity.classSchool;
import vn.iback.studentmanager.entity.staff;

import java.util.List;
@Service
public class teacherServiceImpl implements teacherService {
    private TeacherRespository teacherRespository;
    @Autowired
    public teacherServiceImpl(TeacherRespository teacherRespository) {
        this.teacherRespository = teacherRespository;
    }

    @Override
    public void save(staff staff) {
        teacherRespository.save(staff);
    }

    @Override
    public void update(staff staff) {
        teacherRespository.saveAndFlush(staff);
    }

    @Override
    public void saveAndUpdate(staff staff) {

    }

    @Override
    public staff findTeacherByid(String id) {
        return teacherRespository.getById(id);
    }

    @Override
    public staff findStaffByName(String staff) {
        return null;
    }

    @Override
    public List<staff> findAllTeacher() {
        return teacherRespository.findAll();
    }

    @Override
    public void deleteById(String id) {
        teacherRespository.deleteById(id);
    }


}
