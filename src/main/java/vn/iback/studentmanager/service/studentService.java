package vn.iback.studentmanager.service;

import vn.iback.studentmanager.entity.StudentIntoNotebook;
import vn.iback.studentmanager.entity.student;

import java.util.List;

public interface studentService {
    public void save(student student);
    public void update(student student);
    public student findStudentById(String id);

    public void deleteById(String id);
}
