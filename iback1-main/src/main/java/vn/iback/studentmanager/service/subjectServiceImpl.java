package vn.iback.studentmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.iback.studentmanager.dao.SubjectRespository;
import vn.iback.studentmanager.entity.subjects;

import java.util.List;
@Service
public class subjectServiceImpl implements subjectService {
    private SubjectRespository subjectRespository;
    @Autowired
    public subjectServiceImpl(SubjectRespository subjectRespository) {
        this.subjectRespository = subjectRespository;
    }

    @Override
    public void save(subjects subjects) {
        subjectRespository.save(subjects);
    }

    @Override
    public void update(subjects subjects) {
        subjectRespository.saveAndFlush(subjects);
    }

    @Override
    public subjects findNSubjectByid(String id) {
        return subjectRespository.getById(id);
    }

    @Override
    public subjects findSubjectByName(String subjectName) {
        return null;
    }


    @Override
    public List<subjects> findAll() {
        return subjectRespository.findAll();
    }

    @Override
    public void deleteById(String id) {
    subjectRespository.deleteById(id);
    }
}
