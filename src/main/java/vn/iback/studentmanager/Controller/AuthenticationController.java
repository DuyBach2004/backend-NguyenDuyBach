package vn.iback.studentmanager.Controller;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.SignedJWT;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.iback.studentmanager.dao.RoleRespository;
import vn.iback.studentmanager.entity.roles;
import vn.iback.studentmanager.entity.student;
import vn.iback.studentmanager.entity.user;
import vn.iback.studentmanager.service.AuthenticationService.AuthenticationService;
import vn.iback.studentmanager.service.Userservice;
import vn.iback.studentmanager.service.specializationService;
import vn.iback.studentmanager.service.studentService;
import vn.iback.studentmanager.web.RegisterUser;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private vn.iback.studentmanager.service.studentService studentService;
    private Userservice userservice;
    private RoleRespository roleRepository;
    private specializationService specializationService;
    private AuthenticationService authenticationService;
    @Autowired
    public AuthenticationController(vn.iback.studentmanager.service.studentService studentService, Userservice userservice, RoleRespository roleRepository, vn.iback.studentmanager.service.specializationService specializationService, AuthenticationService authenticationService) {
        this.studentService = studentService;
        this.userservice = userservice;
        this.roleRepository = roleRepository;
        this.specializationService = specializationService;
        this.authenticationService = authenticationService;
    }

    //    @GetMapping("/showRegisterForm")
//    public String showRegisterForm(Model model){
//        RegisterUser registerUser=new RegisterUser();
//        model.addAttribute("registerUser",registerUser);
//        return "register/form";
//    }
//    @PostMapping("/process")
//    @PostMapping("/process")
//    public String process(@Valid @ModelAttribute("registerUser") RegisterUser registerUser, BindingResult result, Model model, HttpSession session){
//        String userName= registerUser.getUsername();
//        //form validation
//        if(result.hasErrors()){
//            return "register/form";
//        }
//        //Kieu tra user ton tai
//        user userExisting= userservice.findByUsername(userName);
//        if (userExisting!=null){
//            model.addAttribute("registerUser",new RegisterUser());
//            model.addAttribute("my_error","Tài khoản đã tồn tại");
//            return "register/form";
//        }
//        //Neu chua ton tai
//        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
//        user user=new user();
//        user.setUsername(registerUser.getUsername());
//        user.setPassword(bCryptPasswordEncoder.encode(registerUser.getPassword()));
//        user.setFirstname(registerUser.getFirstName());
//        user.setLastname(registerUser.getLastName());
//        user.setEmail(registerUser.getEmail());
//
//
//
//        // Lấy thời gian hiện tại (ví dụ, làm chuỗi nó làm được)
//        String timeCurrently = String.valueOf(System.currentTimeMillis());
//
//// Tạo một số ngẫu nhiên
//        Random random = new Random();
//        int randomNumber = random.nextInt(10000); // Số ngẫu nhiên giới hạn trong khoảng từ 0 đến 9999
//
//// Kết hợp chuỗi "ST" và số ngẫu nhiên để tạo studentId
//        String studentId = "ST" + randomNumber;
//
//// Tạo một đối tượng student mới và thiết lập ID cho nó
//        student newStudent = new student();
//        newStudent.setStudentId(studentId);
//
//// Lưu student mới vào cơ sở dữ liệu
//        studentService.save(newStudent);
//
//// Thiết lập student cho đối tượng user
//        user.setStudentId(newStudent);
//
//        //role mac dinh
//        roles defautRole=roleRepository.findByName("ROLE_USER");
//        Collection<roles> roles=new ArrayList<>();
//        roles.add(defautRole);
//        user.setRoles(roles);
//        user.setTrangThaiXacThuc(false);
//        userservice.save(user);
//
//        //thong bao thanh cong
//        session.setAttribute("myuser", user);
//        return "register/confirmation";
//
//    }
    @PostMapping("/sign-in")
public ResponseEntity processLogin(@RequestParam("username") String username,@RequestParam("password") String password, Model model, HttpSession session){
    user user=userservice.findByUsername(username);
        String encryptedPasswordFromDb=user.getPassword();
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        boolean isPasswordMatches = bcrypt.matches(password, encryptedPasswordFromDb);

        if (isPasswordMatches) {
            // Mật khẩu đúng ...
            return ResponseEntity.ok("success");
        } else {
            // Mật khẩu sai ...
            return ResponseEntity.ok("error");
        }
}
    @GetMapping("/logout")
    public ResponseEntity logout(HttpSession session) {
        try {
            // Xử lý việc xóa thông tin phiên đăng nhập ở đây
            // Ví dụ: xóa token, xóa session, v.v.
            session.invalidate();
            // Trả về thông báo thành công
            return ResponseEntity.ok("Đã đăng xuất thành công!");
        } catch (Exception e) {
            // Xử lý lỗi (nếu có)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Lỗi khi đăng xuất: " + e.getMessage());
        }
    }

    @PostMapping("/sign-up")
    public ResponseEntity<user> processRegisterUser(@RequestBody RegisterUser registerUser, Model model, HttpSession session){
        String userName= registerUser.getUsername();
        //form validation
        //Neu chua ton tai
        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
        user user=new user();
        user.setUsername(registerUser.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(registerUser.getPassword()));
        user.setFirstname(registerUser.getFirstName());
        user.setLastname(registerUser.getLastName());
        user.setEmail(registerUser.getEmail());



        // Lấy thời gian hiện tại (ví dụ, làm chuỗi nó làm được)
        String timeCurrently = String.valueOf(System.currentTimeMillis());

// Tạo một số ngẫu nhiên
        Random random = new Random();
        int randomNumber = random.nextInt(10000); // Số ngẫu nhiên giới hạn trong khoảng từ 0 đến 9999

// Kết hợp chuỗi "ST" và số ngẫu nhiên để tạo studentId
        String studentId = "ST" + randomNumber;

// Tạo một đối tượng student mới và thiết lập ID cho nó
        student newStudent = new student();
        newStudent.setStudentId(studentId);

// Lưu student mới vào cơ sở dữ liệu
        studentService.save(newStudent);

// Thiết lập student cho đối tượng user
        user.setStudentId(newStudent);

        //role mac dinh
        roles defautRole=roleRepository.findByName("ROLE_USER");
        Collection<roles> roles=new ArrayList<>();
        roles.add(defautRole);
        user.setRoles(roles);
        user.setTrangThaiXacThuc(false);
        userservice.save(user);

        //thong bao thanh cong
        session.setAttribute("myuser", user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);

    }
//    @PostMapping("/token")
//    public String generaToken(@RequestParam("username") String username,@RequestParam("password")String password ){
//        user user=userservice.findByUsername(username);
//        return authenticationService.generateToken(user);
//    }
//    @PostMapping("/introspect")
//    public boolean authenticate(@RequestParam("token") String token)
//            throws ParseException, JOSEException {
//        System.out.println(token);
//        return authenticationService.introspect(token);
//    }

}
