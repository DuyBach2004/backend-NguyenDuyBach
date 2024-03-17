package vn.iback.studentmanager.service;

import vn.iback.studentmanager.entity.classSchool;
import vn.iback.studentmanager.entity.notebook;

import java.util.List;

public interface nootebookService {
    public void save(notebook notebook);
    public void update(notebook notebook);
    public void saveAndUpdate(notebook notebook);
    public notebook findNotebookByid(int id);
    public notebook findNotebookByName(String notebookName);
    public List<notebook> findAllNote();
    public void deleteById(int id);

}
