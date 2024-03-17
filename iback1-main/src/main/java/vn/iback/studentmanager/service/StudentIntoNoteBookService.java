package vn.iback.studentmanager.service;

import vn.iback.studentmanager.entity.StudentIntoNotebook;
import vn.iback.studentmanager.entity.notebook;

import java.util.List;

public interface StudentIntoNoteBookService {
    public void save(StudentIntoNotebook studentIntoNotebook);
    public void update(StudentIntoNotebook studentIntoNotebook);
    public void saveAndUpdate(StudentIntoNotebook studentIntoNotebook);
    public StudentIntoNotebook findStudentIntoNotebookByid(int id);
    public StudentIntoNotebook findStudentIntoNotebookByName(String name);
    public List<StudentIntoNotebook> findAllStudentIntoNotebook();
    public void deleteById(int id);
}
