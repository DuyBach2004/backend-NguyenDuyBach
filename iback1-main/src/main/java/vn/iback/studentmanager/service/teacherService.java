package vn.iback.studentmanager.service;

import vn.iback.studentmanager.entity.classSchool;
import vn.iback.studentmanager.entity.staff;

import java.util.List;

public interface teacherService {
    public void save(staff staff);
    public void update(staff staff);
    public void saveAndUpdate(staff staff);
    public staff findTeacherByid(String id);
    public staff findStaffByName(String staff);
    public List<staff> findAllTeacher();
    public void deleteById(String id);

}
