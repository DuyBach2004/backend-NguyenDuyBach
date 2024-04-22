//package vn.iback.studentmanager.serviceTest;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.data.repository.Repository;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import vn.iback.studentmanager.dao.UserRespository;
//import vn.iback.studentmanager.entity.user;
//import vn.iback.studentmanager.exception.UserAlreadyExistsException;
//import vn.iback.studentmanager.service.Userservice;
//import vn.iback.studentmanager.service.UserserviceImpl;
//
//@WebMvcTest(UserserviceImpl.class)
//public class userServiceTest {
//    @MockBean
//    private UserRespository userRespository;
//    @Autowired
//    private Userservice userservice;
//
//    public user mokUserTest(){
//        return new user("vinhrau","Duybach74@","Vinh","Rau");
//    }
//    @Test
//    public void  testCreate_WhenNameUserAlreadyExists_ThrowException(){
//        user mocKetUser= mokUserTest();
//        Mockito.when(userRespository.existsById(mocKetUser.getUsername())).thenReturn(true);
//        Assertions.assertThrows( UserAlreadyExistsException.class,()->userservice.save(mocKetUser));
//    }
//
//}
