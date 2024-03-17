package vn.iback.studentmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.iback.studentmanager.dao.NootebookResponsitory;
import vn.iback.studentmanager.entity.classSchool;
import vn.iback.studentmanager.entity.notebook;

import java.util.List;
@Service
public class nootebookServiceImpl implements nootebookService {
    private NootebookResponsitory nootebookResponsitory;
    @Autowired
    public nootebookServiceImpl(NootebookResponsitory nootebookResponsitory) {
        this.nootebookResponsitory = nootebookResponsitory;
    }

    @Override
    public void save(notebook notebook) {
        nootebookResponsitory.save(notebook);
    }

    @Override
    public void update(notebook notebook) {
        nootebookResponsitory.saveAndFlush(notebook);
    }

    @Override
    public void saveAndUpdate(notebook notebook) {

    }

    @Override
    public notebook findNotebookByid(int id) {
        return nootebookResponsitory.getById(id);
    }

    @Override
    public notebook findNotebookByName(String notebookName) {
        return null;
    }

    @Override
    public List<notebook> findAllNote() {
        return nootebookResponsitory.findAll();
    }

    @Override
    public void deleteById(int id) {
        nootebookResponsitory.deleteById(id);
    }
}
