package vn.iback.studentmanager.service.diemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.iback.studentmanager.dao.DiemResprository;
import vn.iback.studentmanager.entity.diem;

import java.util.List;
@Service
public class diemServiceImpl implements diemService {
    private DiemResprository diemResprository;
    @Autowired
    public diemServiceImpl(DiemResprository diemResprository) {
        this.diemResprository = diemResprository;
    }

    @Override
    public void save(diem diem) {
        diemResprository.save(diem);
    }

    @Override
    public void update(diem diem) {
        diemResprository.saveAndFlush(diem);
    }

    @Override
    public diem findDiemById(int maDiem) {
        return diemResprository.getById(maDiem);
    }

    @Override
    public List<diem> findAllDiem() {
        return diemResprository.findAll();
    }

    @Override
    public void deleteById(int id) {
        diemResprository.deleteById(id);
    }
}
