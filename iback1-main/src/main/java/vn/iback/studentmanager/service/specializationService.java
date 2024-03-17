package vn.iback.studentmanager.service;

import vn.iback.studentmanager.entity.specialization;

import java.util.List;

public interface specializationService {
    public void save(specialization specialization);
    public void update(specialization specialization);
    public void saveAndUpdate(specialization specialization);
    public specialization findSpecializationByid(String id);
    public List<specialization> findAllSpecialization();

    public specialization deleteById(String id);

}
