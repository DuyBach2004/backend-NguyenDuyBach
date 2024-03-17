package vn.iback.studentmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.iback.studentmanager.dao.ClassRespository;
import vn.iback.studentmanager.entity.classSchool;
import vn.iback.studentmanager.entity.staff;

import java.util.List;
@Service
public class classServiceImpl implements classService {
    ClassRespository classRespository;
    @Autowired
    public classServiceImpl(ClassRespository classRespository) {
        this.classRespository = classRespository;
    }



    @Override
    public void save(classSchool classSchool) {
        classRespository.save(classSchool);
    }

    @Override
    public void update(classSchool classSchool) {
        classRespository.saveAndFlush(classSchool);
    }

    @Override
    public void saveAndUpdate(classSchool classSchool) {
        classRespository.saveAndFlush(classSchool);
    }

    @Override
    public classSchool findClassByid(int id) {
        classSchool classSchool= classRespository.getById(id);
        return classSchool;
    }

    @Override
    public classSchool findClassByName(String classname) {

        return null;
    }

    @Override
    public List<classSchool> findAllClass() {
        List<classSchool> list=classRespository.findAll();
        return list;
    }

    @Override
    public void deleteById(int id) {
        classRespository.deleteById(id);
    }

    @Override
    public void deletehomeroomTeacher(classSchool classSchool) {
        staff homeroomTeacher=classSchool.getHomeroomTeacher();
            if(homeroomTeacher != null){
                classSchool.setHomeroomTeacher(null);
                classRespository.saveAndFlush(classSchool);
            }
    }
}
