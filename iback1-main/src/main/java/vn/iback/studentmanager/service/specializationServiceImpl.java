package vn.iback.studentmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.iback.studentmanager.dao.SpecializationRespository;
import vn.iback.studentmanager.entity.specialization;

import java.util.List;

@Service
public class specializationServiceImpl implements specializationService {
    private SpecializationRespository specializationRespository;
    @Autowired
    public specializationServiceImpl(SpecializationRespository specializationRespository) {
        this.specializationRespository = specializationRespository;
    }

    @Override
    public void save(specialization specialization) {
        specializationRespository.save(specialization);
    }

    @Override
    public void update(specialization specialization) {
        specializationRespository.saveAndFlush(specialization);
    }

    @Override
    public void saveAndUpdate(specialization specialization) {

    }

    @Override
    public specialization findSpecializationByid(String id) {
        return specializationRespository.getById(id);
    }

    @Override
    public List<specialization> findAllSpecialization() {
        return specializationRespository.findAll();
    }

    @Override
    public specialization deleteById(String id) {
         specializationRespository.deleteById(id);
        return null;
    }
}
