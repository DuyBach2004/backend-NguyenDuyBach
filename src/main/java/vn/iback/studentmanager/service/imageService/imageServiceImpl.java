package vn.iback.studentmanager.service.imageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.iback.studentmanager.dao.ImageRespository;
import vn.iback.studentmanager.entity.image;

import java.util.List;
@Service
public class imageServiceImpl implements imageService {
    private ImageRespository imageRespository;
    @Autowired
    public imageServiceImpl(ImageRespository imageRespository) {
        this.imageRespository = imageRespository;
    }



    @Override
    public void save(image image) {
        imageRespository.save(image);
    }

    @Override
    public void update(image image) {
        imageRespository.saveAndFlush(image);
    }

    @Override
    public void saveAndUpdate(image image) {
        imageRespository.saveAndFlush(image);
    }

    @Override
    public image findImageByid(int id) {
        return imageRespository.getById(id);
    }

    @Override
    public List<image> findAllImage() {
        return imageRespository.findAll();
    }

    @Override
    public void deleteById(int id) {
        imageRespository.deleteById(id);
    }
}
