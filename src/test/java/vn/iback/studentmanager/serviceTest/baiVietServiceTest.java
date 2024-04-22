package vn.iback.studentmanager.serviceTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContextException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.Assert;
import vn.iback.studentmanager.configurationTest.Configuration;
import vn.iback.studentmanager.dao.BaivietResponsitory;
import vn.iback.studentmanager.dto.request.BaiVietRequest;
import vn.iback.studentmanager.dto.respon.BaiVietRespon;
import vn.iback.studentmanager.dto.respon.UserRespon;
import vn.iback.studentmanager.entity.baiViet;
import vn.iback.studentmanager.entity.user;
import vn.iback.studentmanager.exception.BaiVietAlreadyExistsException;
import vn.iback.studentmanager.service.Userservice;
import vn.iback.studentmanager.service.baiVietService.baiVietService;
import vn.iback.studentmanager.service.baiVietService.baiVietServiceImpl;


@SpringBootTest(classes = {baiVietServiceImpl.class})
@AutoConfigureMockMvc
@ContextConfiguration(classes = Configuration.class)
public class baiVietServiceTest {
    @MockBean
    private BaivietResponsitory baivietResponsitory;
    @Autowired
    private  baiVietService baiVietService;
    @MockBean
    private Userservice userService;
    private BaiVietRequest mockBaiVietRequest(){
        return new BaiVietRequest(
                10,
                "Chào ngày mới",
                "",
                "duongminhtuyen"
        );
    }
    private UserRespon mockUserResponse(){
        return new UserRespon("duongminhtuyen");
    }
    private BaiVietRespon mockBaiVietRespon(){
        return new BaiVietRespon(
                "Chào ngày mới",
                "",
                mockUserResponse()
        );
    }
    @Test
    public void testCreate_WhenBaiVietDoesNotExists(){
        BaiVietRequest mocRequest = mockBaiVietRequest();
        baiViet mockBaiViet=  new baiViet();
        mockBaiViet.setTitle("chào ngày cũ");
        user user = new user();
        user.setUsername(mocRequest.getCreateBy());
//        Mockito.when(userService.findByUsername(mocRequest.getCreateBy())).thenReturn(user);
        mockBaiViet.setUser(user);

        BaiVietRespon respon=mockBaiVietRespon();

        //giả định kết quả trả về
        Mockito.when(baivietResponsitory.existsById(mockBaiViet.getId())).thenReturn(true);
        var exception=Assertions.assertThrows(ApplicationContextException.class, () -> {
            baiVietService.save(mockBaiViet);
        });
        Assertions.assertEquals(mockBaiViet.getUser().getUsername(),respon.getCreateBy().getUsername());
        Assertions.assertEquals("BaiViet đã tồn tại",exception.getMessage());

    }
    @Test
    public void testCreate_WhenSuccess(){
        // Tạo đối tượng bài viết giả mạo và mock các phương thức cần thiết
        BaiVietRequest mockRequest = mockBaiVietRequest();
        baiViet mockBaiViet = new baiViet();
        mockBaiViet.setTitle("Chào ngày mới");
        mockBaiViet.setId(mockRequest.getId());
        user user = new user();
        user.setUsername(mockRequest.getCreateBy());
//        Mockito.when(userService.findByUsername(mockRequest.getCreateBy())).thenReturn(user);
        mockBaiViet.setUser(user);

        // Giả định rằng bài viết chưa tồn tại trong cơ sở dữ liệu
        Mockito.when(baivietResponsitory.existsById(mockBaiViet.getId())).thenReturn(false);
        Mockito.when(baivietResponsitory.save(mockBaiViet)).thenReturn(mockBaiViet);

        BaiVietRespon respon= mockBaiVietRespon();

        Assertions.assertEquals(mockBaiViet.getUser().getUsername(),mockRequest.getCreateBy());
        Assertions.assertEquals(mockBaiViet.getTitle(),mockRequest.getTitle());
        Assertions.assertEquals(mockBaiViet.getId(),mockRequest.getId());
    }

}
