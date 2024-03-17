package vn.iback.studentmanager.service.hokyservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.iback.studentmanager.dao.KyhocRespository;
import vn.iback.studentmanager.entity.kyHoc;

import java.util.List;
@Service
public class hokyServiceImpl implements hokyService {
    private KyhocRespository kyhocRespository;
    @Autowired
    public hokyServiceImpl(KyhocRespository kyhocRespository) {
        this.kyhocRespository = kyhocRespository;
    }

    @Override
    public void save(kyHoc kyHoc) {
        kyhocRespository.save(kyHoc);
    }

    @Override
    public void update(kyHoc kyHoc) {
        kyhocRespository.saveAndFlush(kyHoc);
    }
    @Override
    public kyHoc findkyHocid(int id) {
        return kyhocRespository.getById(id);
    }

    @Override
    public List<kyHoc> findAllKyHoc() {
        return kyhocRespository.findAll();
    }

    @Override
    public void deleteById(int id) {
            kyhocRespository.deleteById(id);
    }
}
