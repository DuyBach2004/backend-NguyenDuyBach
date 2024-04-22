package vn.iback.studentmanager.service.baiVietService;

import ch.qos.logback.core.spi.ErrorCodes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.iback.studentmanager.dao.BaivietResponsitory;
import vn.iback.studentmanager.dao.UserRespository;
import vn.iback.studentmanager.dto.postDTO;
import vn.iback.studentmanager.entity.baiViet;
import vn.iback.studentmanager.entity.user;
import vn.iback.studentmanager.service.Userservice;

import java.util.ArrayList;
import java.util.List;
@Service
public class baiVietServiceImpl implements baiVietService {
    private BaivietResponsitory baivietResponsitory;
    private UserRespository userRespository;
    private static final Logger log = LoggerFactory.getLogger(user.class);
    @Autowired
    public baiVietServiceImpl(BaivietResponsitory baivietResponsitory) {
        this.baivietResponsitory = baivietResponsitory;
    }

    @Override
    public baiViet save(baiViet baiViet) {
        if (baivietResponsitory.existsById(baiViet.getId())){
             throw new ApplicationContextException("BaiViet đã tồn tại");
        } else {
            // Trả về đối tượng baiViet sau khi lưu vào cơ sở dữ liệu
            return baivietResponsitory.save(baiViet);
        }
    }

    @Override
    public void update(baiViet baiViet) {
            baivietResponsitory.saveAndFlush(baiViet);
    }

    @Override
    public void saveAndUpdate(baiViet baiViet) {
            baivietResponsitory.saveAndFlush(baiViet);
    }

    @Override
    public baiViet findClassByid(int id) {
        return baivietResponsitory.getById(id);
    }

    @Override
    public List<baiViet> findAllBaiViet() {
        return baivietResponsitory.findAll();
    }

    @Override
    public List<postDTO> findBaiVietByUser(String username) {
        user user = userRespository.findByUsername(username);
        if (user != null) {
            log.info(user + "---------------");
            List<baiViet> baiVietList = baivietResponsitory.findAll();
            List<postDTO> postDTOS = new ArrayList<>();
            for (baiViet baiViet : baiVietList) {
                if (baiViet.getUser() != null) {
                    if (baiViet.getUser().getUsername().equals(username)) {
                        postDTO postDTO = new postDTO();
                        if (baiViet.getImage() != null) {
                            postDTO.setImage(baiViet.getImage());
                        }
                        postDTO.setLike(baiViet.getLike());
                        String time = String.valueOf(baiViet.getThoigianbinhluan());
                        postDTO.setThoigianbinhluan(time);
                        postDTO.setId(baiViet.getId());
                        if (baiViet.getContent() != null) {
                            postDTO.setTitle(baiViet.getContent());
                        }
                        if (baiViet.getUser() != null) {
                            postDTO.setUsername(baiViet.getUser().getUsername());
                        }
                        postDTOS.add(postDTO);
                    }
                }
            }
            return postDTOS;
        }else {
            throw new ApplicationContextException("user không tồn tại");
        }
    }
    @Override
    @Transactional
    public void deleteById(int id) {
        baivietResponsitory.deleteById(id);
    }

    @Override
    public boolean exitsById(int id) {
        return baivietResponsitory.existsById(id);
    }
}
