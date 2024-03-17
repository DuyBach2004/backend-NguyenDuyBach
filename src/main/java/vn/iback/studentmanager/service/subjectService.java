package vn.iback.studentmanager.service;

import vn.iback.studentmanager.entity.notebook;
import vn.iback.studentmanager.entity.subjects;

import java.util.List;

public interface subjectService {
    public void save(subjects subjects);
    public void update(subjects subjects);
    public subjects findNSubjectByid(String id);
    public subjects findSubjectByName(String subjectName);
    public List<subjects> findAll();
    public void deleteById(String id);
}
