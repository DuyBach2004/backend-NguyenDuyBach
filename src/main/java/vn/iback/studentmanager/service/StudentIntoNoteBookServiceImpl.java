package vn.iback.studentmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.iback.studentmanager.dao.StudentIntoNotebooklRespository;
import vn.iback.studentmanager.entity.StudentIntoNotebook;

import java.util.List;
@Service
public class StudentIntoNoteBookServiceImpl implements StudentIntoNoteBookService {
    private StudentIntoNotebooklRespository studentIntoNotebooklRespository;
    @Autowired
    public StudentIntoNoteBookServiceImpl(StudentIntoNotebooklRespository studentIntoNotebooklRespository) {
        this.studentIntoNotebooklRespository = studentIntoNotebooklRespository;
    }

    @Override
    public void save(StudentIntoNotebook studentIntoNotebook) {
        studentIntoNotebooklRespository.save(studentIntoNotebook);
    }

    @Override
    public void update(StudentIntoNotebook studentIntoNotebook) {
        studentIntoNotebooklRespository.saveAndFlush(studentIntoNotebook);
    }

    @Override
    public void saveAndUpdate(StudentIntoNotebook studentIntoNotebook) {

    }

    @Override
    public StudentIntoNotebook findStudentIntoNotebookByid(int id) {
        return studentIntoNotebooklRespository.getById(id);
    }

    @Override
    public StudentIntoNotebook findStudentIntoNotebookByName(String name) {
        return null;
    }

    @Override
    public List<StudentIntoNotebook> findAllStudentIntoNotebook() {
        return studentIntoNotebooklRespository.findAll();
    }

    @Override
    public void deleteById(int id) {
        studentIntoNotebooklRespository.deleteById(id);
    }
}
