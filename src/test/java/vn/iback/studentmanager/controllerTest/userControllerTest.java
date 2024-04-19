package vn.iback.studentmanager.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import vn.iback.studentmanager.entity.baiViet;
import vn.iback.studentmanager.entity.user;
import vn.iback.studentmanager.service.Userservice;
import vn.iback.studentmanager.service.baiVietService.baiVietService;

@SpringBootTest
@AutoConfigureMockMvc
public class userControllerTest{
    private static final Logger log = LoggerFactory.getLogger(userControllerTest.class);

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Userservice userService;

    @MockBean
    private baiVietService baiVietService;

    @Test
    @WithMockUser(username = "duongminhtuyen", roles = {"USER"})
    public void createPost() throws Exception {
        //GIVEN
        // Tạo một đối tượng User để kiểm tra
        baiViet baiViet=new baiViet();
        baiViet.setTitle("hello word");
        //...
        ObjectMapper objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(baiViet);

        String token = "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJkdXliYWNoIiwic3ViIjoiZHVvbmdtaW5odHV5ZW4iLCJleHAiOjE3MTM0NjE0NDMsImlhdCI6MTcxMzQ1Nzg0MywianRpIjoiY2ZkYmRiZGYtNmI2Mi00NDA0LWJkMzYtZjZhNmM0OTdhZDJmIiwic2NvcGUiOiJST0xFX1JPTEVfVVNFUiJ9.Uvg_GCUoZKwZUIcscBZ1lAkF9tqtAKVXDoB7tF2WNc92yBhz0y0jcXzOydUOtlV8IbdpNhaLJki-X31wNLp1Sw";

//        Mockito.when(baiVietService.save(ArgumentMatchers.any())).thenReturn(baiViet);
        //WHEN
        mockMvc.perform(MockMvcRequestBuilders.post("/user/post")
                        .param("title", "hello word")
                        .param("username", "duongminhtuyen")
                        .param("image", "image_url")
                        .content(content)
                        .header("Authorization",token)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(baiVietService, Mockito.times(1)).save(baiViet);
    }
}
