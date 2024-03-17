package vn.iback.studentmanager.service;

import vn.iback.studentmanager.entity.classSchool;

import java.util.List;

public interface classService {
    public void save(classSchool classSchool);
    public void update(classSchool classSchool);
    public void saveAndUpdate(classSchool classSchool);
    public classSchool findClassByid(int id);
    public classSchool findClassByName(String classname);
    public List<classSchool> findAllClass();
    public void deleteById(int id);

    public void deletehomeroomTeacher(classSchool classSchool);

}
