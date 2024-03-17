package vn.iback.studentmanager.service.hokyservice;

import vn.iback.studentmanager.entity.classSchool;
import vn.iback.studentmanager.entity.kyHoc;

import java.util.List;

public interface hokyService {
    public void save(kyHoc kyHoc);
    public void update(kyHoc kyHoc);
    public kyHoc findkyHocid(int id);
    public List<kyHoc> findAllKyHoc();
    public void deleteById(int id);


}
