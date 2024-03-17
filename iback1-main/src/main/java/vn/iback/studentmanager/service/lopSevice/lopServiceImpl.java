package vn.iback.studentmanager.service.lopSevice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.iback.studentmanager.dao.lopResponsitory;
import vn.iback.studentmanager.entity.lop;

import java.util.List;
@Service
public class lopServiceImpl implements lopService {
    public lopResponsitory lopResponsitory;
    @Autowired
    public lopServiceImpl(vn.iback.studentmanager.dao.lopResponsitory lopResponsitory) {
        this.lopResponsitory = lopResponsitory;
    }

    @Override
    public void save(lop lop) {
        lopResponsitory.save(lop);
    }

    @Override
    public void update(lop lop) {
        lopResponsitory.saveAndFlush(lop);
    }

    @Override
    public lop findLopById(String maLop) {
        return lopResponsitory.getById(maLop);
    }

    @Override
    public List<lop> findAllLop() {
        return lopResponsitory.findAll();
    }

    @Override
    public void deleteById(String id) {
        lopResponsitory.deleteById(id);
    }
}
