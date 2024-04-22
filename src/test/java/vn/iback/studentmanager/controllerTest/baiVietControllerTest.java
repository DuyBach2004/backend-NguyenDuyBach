package vn.iback.studentmanager.controllerTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import vn.iback.studentmanager.Controller.user.UserController2;
import vn.iback.studentmanager.configurationTest.Configuration;
import vn.iback.studentmanager.dao.RoleRespository;
import vn.iback.studentmanager.dto.postDTO;
import vn.iback.studentmanager.dto.request.BaiVietRequest;
import vn.iback.studentmanager.dto.respon.BaiVietRespon;
import vn.iback.studentmanager.dto.respon.UserRespon;
import vn.iback.studentmanager.entity.baiViet;
import vn.iback.studentmanager.entity.user;
import vn.iback.studentmanager.service.AuthenticationService.AuthenticationService;
import vn.iback.studentmanager.service.StudentIntoNoteBookService;
import vn.iback.studentmanager.service.Userservice;
import vn.iback.studentmanager.service.baiVietService.baiVietService;
import vn.iback.studentmanager.service.mailService.MailService;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest(classes = {UserController2.class})
@AutoConfigureMockMvc
@ContextConfiguration(classes = Configuration.class)
public class baiVietControllerTest {
    private static final Logger log = LoggerFactory.getLogger(baiVietControllerTest.class);

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Userservice userService;
    @MockBean
    private RoleRespository roleRespository;
    @MockBean
    private MailService mailService;
    @MockBean
    private vn.iback.studentmanager.service.imageService.imageService imageService;
    @MockBean
    private vn.iback.studentmanager.service.binhluanService.binhluanService binhluanService;
    @MockBean
    private vn.iback.studentmanager.service.phanHoiService.phanHoiService phanHoiService;
    @MockBean
    private vn.iback.studentmanager.service.nootebookService nootebookService;
    @MockBean
    private vn.iback.studentmanager.service.diemService.diemService diemService;
    @MockBean
    private StudentIntoNoteBookService studentIntoNoteBookService;
    @MockBean
    private AuthenticationService authenticationService;
    @MockBean
    private baiVietService baiVietService;
    @Autowired
    private UserController2 userController2;
    @Autowired
    ObjectMapper objectMapper;
    private static final String END_POINT_PATH = "/user/post";

    private BaiVietRequest mockBaiVietRequest() {
        return new BaiVietRequest(
                10,
                "Chào ngày mới",
                "",
                "duongminhtuyen"
        );
    }

    private UserRespon mockUserResponse() {
        return new UserRespon("duongminhtuyen");
    }

    private BaiVietRespon mockBaiVietRespon() {
        return new BaiVietRespon(
                "Chào ngày mới",
                "",
                mockUserResponse()
        );
    }

    @Test
    void testCreateBaiViet_WhenTitleInvalid_Return400AndException() throws Exception {
        //Given
        objectMapper.registerModule(new JavaTimeModule());
        BaiVietRequest mocRequest = mockBaiVietRequest();
        mocRequest.setTitle(null);

        // Mock the behavior of userService
        user user = new user();
        user.setUsername(mocRequest.getCreateBy());
        Mockito.when(userService.findByUsername(mocRequest.getCreateBy())).thenReturn(user);

        //When
        mockMvc.perform(
                        MockMvcRequestBuilders.post(END_POINT_PATH)
                                .param("title", mocRequest.getTitle())
                                .param("username", mocRequest.getCreateBy())
                                .param("image", mocRequest.getImage())
                                .contentType(MediaType.APPLICATION_JSON_VALUE))
                //Then
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(result -> {
                    Exception exception = result.getResolvedException();
                    if (exception != null) {
                        System.out.println("Exception occurred: " + exception.getMessage());
                        exception.printStackTrace();
                    }
                })
                .andDo(print());
    }

    @Test
    public void testCreateBaiViet_WhenUserNameDoesNotExists_Return404() throws Exception {
        objectMapper.registerModule(new JavaTimeModule());
        BaiVietRequest mocRequest = mockBaiVietRequest();
        mocRequest.setCreateBy("duy");

        // Mock the behavior of userService
        user user = new user();
        user.setUsername(mocRequest.getCreateBy());
        Mockito.when(userService.findByUsername(mocRequest.getCreateBy())).thenReturn(null);

        mockMvc.perform(
                        MockMvcRequestBuilders.post(END_POINT_PATH)
                                .param("title", mocRequest.getTitle())
                                .param("username", mocRequest.getCreateBy())
                                .param("image", mocRequest.getImage())
                                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().string("username không chính xác"))
                .andDo(result -> {
                    Exception exception = result.getResolvedException();
                    if (exception != null) {
                        System.out.println("Exception occurred: " + exception.getMessage());
                        exception.printStackTrace();
                    }
                })
                .andDo(print());

    }

    @Test
    public void testCreateBaiViet_WhenSuccess_Return201() throws Exception {
        objectMapper.registerModule(new JavaTimeModule());
        BaiVietRequest mocRequest = mockBaiVietRequest();

        // Mock the behavior of userService
        user user = new user();
        user.setUsername(mocRequest.getCreateBy());
        Mockito.when(userService.findByUsername(mocRequest.getCreateBy())).thenReturn(user);


        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(END_POINT_PATH)
                        .param("title", mocRequest.getTitle())
                        .param("username", mocRequest.getCreateBy())
                        .param("image", mocRequest.getImage())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string("Post created successfully"))
                .andDo(print())
                .andReturn();

    }

    @Test
    public void testGetAllPostsByUser_WhenUserNameInvalid_Return500() throws Exception {
        objectMapper.registerModule(new JavaTimeModule());
        BaiVietRequest mocRequest = mockBaiVietRequest();
        mocRequest.setCreateBy("duy");

        // Mock the behavior of userService
        user user = new user();
        user.setUsername(mocRequest.getCreateBy());
        Mockito.when(userService.findByUsername(mocRequest.getCreateBy())).thenReturn(null);
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/user/post/username")
                                .param("username", mocRequest.getCreateBy())
                                .contentType(MediaType.APPLICATION_JSON_VALUE))
                //Then
                .andExpect(MockMvcResultMatchers.status().isInternalServerError())
                .andDo(result -> {
                    Exception exception = result.getResolvedException();
                    if (exception != null) {
                        System.out.println("Exception occurred: " + exception.getMessage());
                        exception.printStackTrace();
                    }
                })
                .andDo(print());

    }
//    @Test
//    public void testGetAllPostsByUser_WhenUserExistsAndRequestIsSuccessful_ReturnPosts() throws Exception {
//        objectMapper.registerModule(new JavaTimeModule());
//        BaiVietRequest mocRequest = mockBaiVietRequest();
//
//        // Create some mock posts
//        List<postDTO> mockPosts = new ArrayList<>();
//        postDTO post1 = new postDTO();
//        post1.setId(1);
//        post1.setTitle("Content of Post 1");
//        post1.setLike(10);
//        post1.setThoigianbinhluan(String.valueOf(LocalDateTime.now()));
//        post1.setUsername(mocRequest.getCreateBy());
//        mockPosts.add(post1);
//
//        // Mock the behavior of baiVietService
//        Mockito.when(baiVietService.findBaiVietByUser(mocRequest.getCreateBy())).thenReturn(mockPosts);
//
//        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/user/post/username")
//                        .param("username", mocRequest.getCreateBy())
//                        .contentType(MediaType.APPLICATION_JSON))
////                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(mockPosts)))
//                .andExpect(MockMvcResultMatchers.jsonPath("title")
//                        .value("chao ca nha"))
//                .andDo(print())
//                .andReturn();
////        List<postDTO> returnedPosts = objectMapper.readValue(mvcResult.getResponse().getContentAsString(),
////                new TypeReference<List<postDTO>>() {});
////
////        // Assert that the returned posts match the mock posts
////        Assertions.assertEquals(mockPosts.size(), returnedPosts.size());
////        Assertions.assertEquals(mockPosts.get(0).getId(), returnedPosts.get(0).getId());
////        Assertions.assertEquals(mockPosts.get(0).getTitle(), returnedPosts.get(0).getTitle());
//
//    }
}
