package vn.iback.studentmanager.Controller.user;

import jakarta.mail.MessagingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.iback.studentmanager.dao.RoleRespository;
import vn.iback.studentmanager.dto.*;
import vn.iback.studentmanager.entity.*;
import vn.iback.studentmanager.service.*;
import vn.iback.studentmanager.service.AuthenticationService.AuthenticationService;
import vn.iback.studentmanager.service.baiVietService.baiVietService;
import vn.iback.studentmanager.service.binhluanService.binhluanService;
import vn.iback.studentmanager.service.diemService.diemService;
import vn.iback.studentmanager.service.imageService.imageService;
import vn.iback.studentmanager.service.khoaService.khoaService;
import vn.iback.studentmanager.service.lopSevice.lopService;
import vn.iback.studentmanager.service.mailService.MailService;
import vn.iback.studentmanager.service.phanHoiService.phanHoiService;
import vn.iback.studentmanager.service.userBinhLuanService.userBinhLuanService;
import vn.iback.studentmanager.service.userPhanHoiService.userPhanHoiService;
import vn.iback.studentmanager.service.userPostService.userPostService;
import vn.iback.studentmanager.utils.Const;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
@RestController
@RequestMapping(value = "/user" )
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController2 {
    private static final Logger log = LoggerFactory.getLogger(user.class);
    private Userservice userservice;
    private RoleRespository roleRespository;
    private MailService mailService;
    private vn.iback.studentmanager.service.baiVietService.baiVietService baiVietService;
    private vn.iback.studentmanager.service.imageService.imageService imageService;
    private vn.iback.studentmanager.service.binhluanService.binhluanService binhluanService;
    private vn.iback.studentmanager.service.phanHoiService.phanHoiService phanHoiService;
    private nootebookService nootebookService;
    private diemService diemService;
    private StudentIntoNoteBookService studentIntoNoteBookService;
    private AuthenticationService authenticationService;

    public UserController2(Userservice userservice, RoleRespository roleRespository, MailService mailService, vn.iback.studentmanager.service.baiVietService.baiVietService baiVietService, vn.iback.studentmanager.service.imageService.imageService imageService, vn.iback.studentmanager.service.binhluanService.binhluanService binhluanService, vn.iback.studentmanager.service.phanHoiService.phanHoiService phanHoiService, vn.iback.studentmanager.service.nootebookService nootebookService, vn.iback.studentmanager.service.diemService.diemService diemService, StudentIntoNoteBookService studentIntoNoteBookService, AuthenticationService authenticationService) {
        this.userservice = userservice;
        this.roleRespository = roleRespository;
        this.mailService = mailService;
        this.baiVietService = baiVietService;
        this.imageService = imageService;
        this.binhluanService = binhluanService;
        this.phanHoiService = phanHoiService;
        this.nootebookService = nootebookService;
        this.diemService = diemService;
        this.studentIntoNoteBookService = studentIntoNoteBookService;
        this.authenticationService = authenticationService;
    }

    @Autowired

    //    @GetMapping("/update")
//    public String updateStudent(@RequestParam("username") String username, Model model){
//        user user=userservice.findByUsername(username);
//        student student=studentService.findStudentById(user.getStudentId().getStudentId());
//        model.addAttribute("student",student);
//        return "user/update";
//    }

//    @PostMapping("/update-if")
//    public String updateInfor(@ModelAttribute("user") user user, Model model){
//        userservice.update(user);
//        System.out.println(user.getStudentId().getDantoc());
//        student student=studentService.findStudentById(user.getStudentId().getStudentId());
//        student.setBirthday(user.getStudentId().getBirthday());
//        student.setTongiao(user.getStudentId().getTongiao());
//        student.setGioitinh(user.getStudentId().getGioitinh());
//        student.setDantoc(user.getStudentId().getDantoc());
//        student.setQuoctich(user.getStudentId().getQuoctich());
//        student.setCccd(user.getStudentId().getCccd());
//        student.setNgaycap(user.getStudentId().getNgaycap());
//        student.setNoicap(user.getStudentId().getNoicap());
//        student.setSodienthoai(user.getStudentId().getSodienthoai());
//        student.setNgayvaodoan(user.getStudentId().getNgayvaodoan());
//        student.setNgayvaodang(user.getStudentId().getNgayvaodang());
//        student.setTinhthanh(user.getStudentId().getTinhthanh());
//        student.setHuyenquan(user.getStudentId().getHuyenquan());
//        student.setXa_phuong(user.getStudentId().getXa_phuong());
//
//        studentService.update(student);
//        return "user/showInformationUpdate";
//    }
//    @GetMapping("/delete")
//    public String deleteStudent(@RequestParam("id") String username, Model model){
//        userservice.deleteByUsername(username);
//        return "redirect:homeUser";
//    }
//    @GetMapping("/showListUser")
//    public String showListUser(Model model){
//        List<user> userList=userservice.findAll();
//        model.addAttribute("userList",userList);
//        return "user/home";
//    }
@GetMapping("")
public ResponseEntity<Object> showListUserRestApi() {
    try {
//        var authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        log.info("Username: {}", authentication.getName());
//        authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));

        List<user> userList = userservice.findAll();
        List<userDTO> userDtoList = new ArrayList<>();
        for (user user : userList) {
            userDTO userDto = new userDTO();
            userDto.setUsername(user.getUsername());
            userDto.setFullName(user.getFirstname() + ' ' + user.getLastname());
            userDto.setEmail(user.getEmail());
            if (user.getKhoa() != null) {
                userDto.setKhoa(user.getKhoa().getTenKhoa());
            }
            if (user.getSpecialization() != null) {
                userDto.setSpecialization(user.getSpecialization().getSpecializedName());
            }
            if(user.getStudentId() != null ){
                userDto.setStudentId(user.getStudentId().getStudentId());
            }
            userDto.setRole(user.getRoles());
            userDtoList.add(userDto);
        }
        return new ResponseEntity<>(userDtoList, HttpStatus.OK);
    } catch (Exception e) {
        return new ResponseEntity<>("Error fetching user data: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
    @PostMapping("/token")
    public String generaToken(@RequestParam("username") String username,@RequestParam("password")String password ){
        user user=userservice.findByUsername(username);
        return authenticationService.generateToken(user);
    }
    //    @RequestMapping(value = "/show", method = RequestMethod.GET)
//    @ResponseBody
    @GetMapping("/post/username")
    public ResponseEntity<Object> showPostOfUse(@RequestParam("username") String username){
        try {
            log.info(username+"----------");
            List<postDTO> postDTOS=baiVietService.findBaiVietByUser(username);

                return new ResponseEntity<>(postDTOS.get(0).getTitle(), HttpStatus.OK);

        }catch (UsernameNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>("Error fetching user data: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/lophocphandadangki/ma-lop-hoc")
    public ResponseEntity<String> studentstudentCancel(@RequestParam("Mã lớp học") int id,Model model, RedirectAttributes redirectAttributes) {
        try {
            StudentIntoNotebook studentIntoNotebook=studentIntoNoteBookService.findStudentIntoNotebookByid(id);
            if(studentIntoNotebook!=null){
                studentIntoNoteBookService.deleteById(id);
                return new ResponseEntity<>("delete successfully", HttpStatus.OK);
            }else {
                return new ResponseEntity<>("Lớp học phần không tồn tại", HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            return new ResponseEntity<>("delete failed: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
//    @DeleteMapping("/reply/ma-phan-hoi")
//    public ResponseEntity<String> deleteReply(@RequestParam("Mã phản hồi") int id,Model model) {
//        try {
//            phanHoi phanHoi=phanHoiService.findPhanHoiByid(id);
//            if(phanHoi!=null){
//                phanHoiService.deleteById(id);
//
//                return new ResponseEntity<>("delete successfully", HttpStatus.OK);
//            }else {
//                return new ResponseEntity<>("Reply does not exist", HttpStatus.BAD_REQUEST);
//            }
//        }catch (Exception e){
//            return new ResponseEntity<>("delete failed: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//    @DeleteMapping("/comment/ma-binh-luan")
//    public ResponseEntity<String> deleteComment(@RequestParam("Mã bình luận") int id,Model model) {
//        try {
//            binhluan binhluan=binhluanService.findClassByid(id);
//            if(binhluan!=null){
//                binhluanService.deleteById(id);
//                return new ResponseEntity<>("delete successfully", HttpStatus.OK);
//            }else {
//                return new ResponseEntity<>("Comment does not exist", HttpStatus.BAD_REQUEST);
//            }
//        }catch (Exception e){
//            return new ResponseEntity<>("delete failed: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//    @DeleteMapping("/post/ma-bai-viet")
//    public ResponseEntity<String> deletePost(@RequestParam("Mã bài viết") int id,Model model) {
//        try {
//            baiViet baiViet=baiVietService.findClassByid(id);
//            if(baiViet!=null){
//                baiVietService.deleteById(id);
//                return new ResponseEntity<>("delete successfully", HttpStatus.OK);
//            }else {
//                return new ResponseEntity<>("Post does not exist", HttpStatus.BAD_REQUEST);
//            }
//        }catch (Exception e){
//            return new ResponseEntity<>("delete failed: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @GetMapping("/comment/username")
    public ResponseEntity<Object> showCmtOfUse(@RequestParam("username") String username){
        try {
            user user=userservice.findByUsername(username);
            if (user != null) {
                List<binhluan> binhluanList = binhluanService.findAllBinhluan();
                List<binhluanDTO> binhluanDTOS = new ArrayList<>();
                for (binhluan binhluan : binhluanList) {
                   if (binhluan.getUser().getUsername().equals(username)){
                       binhluanDTO binhluanDTO = new binhluanDTO();
                       binhluanDTO.setContent(binhluan.getContent());
                       if (binhluan.getBaiViet() != null) {
                           binhluanDTO.setMaBaiViet(binhluan.getBaiViet().getId());
                       }
                       binhluanDTO.setId(binhluan.getId());
                       binhluanDTO.setLike(binhluan.getLike());
                       if (binhluan.getUser() != null) {
                           binhluanDTO.setUsername(binhluan.getUser().getUsername());
                       }
                       String time = String.valueOf(binhluan.getThoigiandangbai());
                       binhluanDTO.setThoigiandangbai(time);
                       binhluanDTOS.add(binhluanDTO);
                   }
                }
                return new ResponseEntity<>(binhluanDTOS, HttpStatus.OK);
            }else {
                return new ResponseEntity<>("User Không tồn tại: ", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch (Exception e){
            return new ResponseEntity<>("Error fetching user data: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/reply/ma-binh-luan")
    public ResponseEntity<Object> showPhanHoiOfBinhLuan(@RequestParam("Mã bình luận") int id ){
        try {
            binhluan binhluan=binhluanService.findClassByid(id);
            if (binhluan != null){
                List<phanHoi> phanHoiList= phanHoiService.findAllPhanHoi();
                List<phanhoiDTO> phanhoiDTOS=new ArrayList<>();
                for(phanHoi phanHoi:phanHoiList){
                    if (phanHoi.getBinhluan().getId()==id){
                        phanhoiDTO phanhoiDTO=new phanhoiDTO();
                        phanhoiDTO.setContent(phanHoi.getContent());
                        phanhoiDTO.setIdBinhluan(phanHoi.getBinhluan().getId());
                        phanhoiDTO.setLike(phanHoi.getLike());
                        String time= String.valueOf(phanHoi.getThoigianphanhoi());
                        phanhoiDTO.setThoigianphanhoi(time);
                        phanhoiDTO.setUser(phanHoi.getUser().getUsername());
                        phanhoiDTOS.add(phanhoiDTO);
                    }
                }
                return new ResponseEntity<>(phanhoiDTOS, HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Bình luận Không tồn tại: ", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch (Exception e){
            return new ResponseEntity<>("Error fetching user data: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
//    @GetMapping("/comment")
//    public  List<binhluan> showBinhLuan(Model model){
//        System.out.println("HELLO");
//        List<baiViet> baiVietList =baiVietService.findAllBaiViet();
//        List<binhluan> list=new ArrayList<>();
//        List<binhluan> binhluanList=new ArrayList<>();
//        for (baiViet baiViet:baiVietList){
//            binhluanList=binhluanService.findAllBinhluanOfBaiViet(baiViet);
//            list.addAll(binhluanList);
//
//        }
//        return  list;
//    }
    @GetMapping("/comment")
    public ResponseEntity<Object> showUserCmt(){
        try {
            List<binhluan> binhluanList = binhluanService.findAllBinhluan();
            List<binhluanDTO> binhluanDTOS = new ArrayList<>();
            for(binhluan binhluan:binhluanList){
                binhluanDTO binhluanDTO=new binhluanDTO();
                binhluanDTO.setContent(binhluan.getContent());
                if (binhluan.getBaiViet()!=null){
                    binhluanDTO.setMaBaiViet(binhluan.getBaiViet().getId());
                }
                binhluanDTO.setLike(binhluan.getLike());
                binhluanDTO.setId(binhluan.getId());
                if (binhluan.getUser()!=null){
                    binhluanDTO.setUsername(binhluan.getUser().getUsername());
                }
                String time= String.valueOf(binhluan.getThoigiandangbai());
                binhluanDTO.setThoigiandangbai(time);
                binhluanDTOS.add(binhluanDTO);
            }
            return new ResponseEntity<>(binhluanDTOS, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Error fetching user data: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/reply")
    public ResponseEntity<Object> showPhanHoi(Model model){
        try {
            List<phanHoi> phanHoiList= phanHoiService.findAllPhanHoi();
            List<phanhoiDTO> phanhoiDTOS=new ArrayList<>();
            for(phanHoi phanHoi:phanHoiList){
                phanhoiDTO phanhoiDTO=new phanhoiDTO();
                phanhoiDTO.setContent(phanHoi.getContent());
                phanhoiDTO.setIdBinhluan(phanHoi.getBinhluan().getId());
                phanhoiDTO.setLike(phanHoi.getLike());
                phanhoiDTO.setId(phanHoi.getIdPhanhoi());
                String time= String.valueOf(phanHoi.getThoigianphanhoi());
                phanhoiDTO.setThoigianphanhoi(time);
                phanhoiDTO.setUser(phanHoi.getUser().getUsername());
                phanhoiDTOS.add(phanhoiDTO);
            }
            return new ResponseEntity<>(phanhoiDTOS, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Error fetching user data: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/post")
    public ResponseEntity<Object>  showBaiViet(){
        try {
            List<baiViet> baiVietList =baiVietService.findAllBaiViet();
            List<postDTO> postDTOS=new ArrayList<>();
            for (baiViet baiViet:baiVietList) {
                postDTO postDTO=new postDTO();
                if (baiViet.getImage() != null){
                    postDTO.setImage(baiViet.getImage());
                }
                postDTO.setLike(baiViet.getLike());
                postDTO.setId(baiViet.getId());
                String time= String.valueOf(baiViet.getThoigianbinhluan());
                postDTO.setThoigianbinhluan(time);
                if (baiViet.getTitle()!=null){
                    postDTO.setTitle(baiViet.getTitle());
                }
                if (baiViet.getUser()!= null){
                    postDTO.setUsername(baiViet.getUser().getUsername());
                }
                postDTOS.add(postDTO);
            }
            return new ResponseEntity<>(postDTOS, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Error fetching user data: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/reply/username")
    public ResponseEntity<Object> showPhanHoiOfUse(@RequestParam("username") String username){
        try {
            user user=userservice.findByUsername(username);
            if (user != null){
                List<phanHoi> phanHoiList= phanHoiService.findAllPhanHoi();
                List<phanhoiDTO> phanhoiDTOS=new ArrayList<>();
                for(phanHoi phanHoi:phanHoiList){
                    if (phanHoi.getUser().getUsername().equals(username)){
                        phanhoiDTO phanhoiDTO=new phanhoiDTO();
                        phanhoiDTO.setContent(phanHoi.getContent());
                        phanhoiDTO.setIdBinhluan(phanHoi.getBinhluan().getId());
                        phanhoiDTO.setLike(phanHoi.getLike());
                        phanhoiDTO.setId(phanHoi.getIdPhanhoi());
                        String time= String.valueOf(phanHoi.getThoigianphanhoi());
                        phanhoiDTO.setThoigianphanhoi(time);
                        phanhoiDTO.setUser(phanHoi.getUser().getUsername());
                        phanhoiDTOS.add(phanhoiDTO);
                    }
            }
                return new ResponseEntity<>(phanhoiDTOS, HttpStatus.OK);
            }else{
                return new ResponseEntity<>("User Không tồn tại: ", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch (Exception e){
            return new ResponseEntity<>("Error fetching user data: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
//    @GetMapping("/reply")
//    public List<userPhanHoi> showUserPhanHoi(Model model){
//        List<userPhanHoi> userPhanHois =userPhanHoiService.findAll();
//        return userPhanHois;
//    }
    @RequestMapping(value = "/post", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> createPost(@RequestParam("title") String title,@RequestParam("username") String username,@RequestParam("image") String images) {
        try {
            // Your existing logic
            user user = userservice.findByUsername(username);
            if (user != null){
                LocalDateTime timeString = LocalDateTime.now();
                Timestamp timestamp = Timestamp.valueOf(timeString);
                List<String> imageBlobs = new ArrayList<>();
                String imageBlob = images;
                imageBlobs.add(images);

                baiViet baiViet = new baiViet();
                baiViet.setContent(title);
                baiViet.setUser(user);
                baiViet.setThoigianbinhluan(timestamp);
                baiViet.setDislike(0);
                baiViet.setImage(imageBlob);
                baiViet.setHaha(0);
                baiViet.setLike(0);
                baiViet.setTim(0);
                baiVietService.save(baiViet);

                for (String image : imageBlobs) {
                    System.out.println("Processing image...");
                    vn.iback.studentmanager.entity.image image1 = new image();
                    image1.setPath(image);
                    image1.setBaiViet(baiViet);
                    imageService.update(image1);
                }

                // After processing the data, you can return a response:
                return new ResponseEntity<>("Post created successfully", HttpStatus.CREATED);
            }else {
                return new ResponseEntity<>("username không chính xác",HttpStatus.NOT_FOUND);
            }
        } catch (UsernameNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            // Handle the exception here (e.g., log it or return an error response)
            return new ResponseEntity<>("Error creating post: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/comment")
    public ResponseEntity<String> binhluan(@RequestParam("username") String username,@RequestParam("content") String content,@RequestParam("Post ID") int idPost){
        try {
            binhluan binhluan=new binhluan();
            baiViet baiViet=baiVietService.findClassByid(idPost);
            if (baiViet != null) { // Kiểm tra xem bài viết có tồn tại không
                user user = userservice.findByUsername(username);
                if (user != null) {
                    LocalDateTime timeString = LocalDateTime.now();
                    Timestamp time = Timestamp.valueOf(timeString);
                    binhluan.setUser(user);
                    binhluan.setContent(content);
                    binhluan.setThoigiandangbai(time);
                    binhluan.setBaiViet(baiViet);
                    binhluanService.save(binhluan);
                    return new ResponseEntity<>("Comment created successfully", HttpStatus.CREATED);
                }else {
                    return new ResponseEntity<>("Error: user does not exist", HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity<>("Error: Post does not exist", HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            return new ResponseEntity<>("Error creating comment: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/reply")
    public ResponseEntity<String> phanhoi(@RequestParam("username") String username,@RequestParam("content") String content,@RequestParam("Comment ID") int idCmt){
        try {
            phanHoi phanHoi=new phanHoi();
            binhluan binhluan=binhluanService.findClassByid(idCmt);
            if (binhluan != null) { // Kiểm tra xem bài viết có tồn tại không
                user user = userservice.findByUsername(username);
                if (user != null){
                    LocalDateTime timeString = LocalDateTime.now();
                    Timestamp time = Timestamp.valueOf(timeString);
                    phanHoi.setUser(user);
                    phanHoi.setThoigianphanhoi(time);
                    phanHoi.setContent(content);
                    phanHoi.setBinhluan(binhluan);
                    phanHoiService.save(phanHoi);
                    return new ResponseEntity<>("Reply created successfully", HttpStatus.CREATED);
                }else {
                    return new ResponseEntity<>("Error: user does not exist", HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity<>("Error: Comment does not exist", HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            return new ResponseEntity<>("Error creating reply: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/lophocphan/create")
    public ResponseEntity<String> studentErroll(@RequestParam("Mã lớp học phần") int id, @RequestParam("username") String username) {
        user user=userservice.findByUsername(username);
        notebook notebook=nootebookService.findNotebookByid(id);
        if (notebook != null){
            Collection<user> users=new ArrayList<>();
            StudentIntoNotebook studentIntoNotebook=new StudentIntoNotebook();
            users.add(user);
            studentIntoNotebook.setUser(user);
            studentIntoNotebook.setNotebook(notebook);
            studentIntoNoteBookService.save(studentIntoNotebook);
            diem diem=new diem();
            diem.setNotebook(notebook);
            diemService.save(diem);
            return new ResponseEntity<>("successfully", HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>("Error: Class does not exist", HttpStatus.BAD_REQUEST);
        }
    }
        @GetMapping("/lophocphandadangki/username")
    public List<StudentIntoNotebook> danhsachlophoc(@RequestParam("username") String username){
        List<StudentIntoNotebook> studentIntoNotebooks=studentIntoNoteBookService.findAllStudentIntoNotebook();
        List<StudentIntoNotebook> studentIntoNotebooks1=new ArrayList<>();
        for (StudentIntoNotebook studentIntoNotebook:studentIntoNotebooks) {
            if(studentIntoNotebook.getUser().getUsername().equals(username)){
                studentIntoNotebooks1.add(studentIntoNotebook);
            }
        }
        return studentIntoNotebooks1;
    }
    @GetMapping("/lophocphandadangki")
    public List<StudentIntoNotebook> danhsachlophocdadangki(){
        List<StudentIntoNotebook> studentIntoNotebooks=studentIntoNoteBookService.findAllStudentIntoNotebook();
        return studentIntoNotebooks;
    }
//    @GetMapping("/show-user-post")
//    public List<userPost> showUserPost(Model model){
//        List<userPost> userPosts =userPostService.findAll();
//        return userPosts;
//    }
    //    @PostMapping("/create-post") http://localhost:8080/user/binhluan-createPhanhoi
//    @RequestMapping(value = "/create-post", method = RequestMethod.POST)
//    @ResponseBody
//    public ResponseEntity<String> createPost(@RequestBody Map<String, Object> payload) {
//        Map<String, Object> additionalProp1 = (Map<String, Object>) payload.get("additionalProp1");
//        String title = (String) additionalProp1.get("title");
//        String username = (String) additionalProp1.get("username");
//        user user=userservice.findByUsername(username);
//
//        String timeString = (String) additionalProp1.get("time");
//
//        System.out.println("bay gio la" + timeString);
//
//        System.out.println("bay gio laaaaaaaaa------------" + payload);
//
//        Timestamp timestamp = Timestamp.valueOf(timeString);
//
//        List<String> imageStrings = (List<String>) additionalProp1.get("images");
//        System.out.println(imageStrings);
//        List<String> imageBlobs = new ArrayList<>();
//        String imageBlob = null;
//        for (String imageString : imageStrings) {
//            byte[] imageBytes = imageString.getBytes(StandardCharsets.UTF_8);
//            imageBlob = imageString;
////            try {
//////                imageBlob = new SerialBlob(imageBytes);
////                imageBlob = imageString;
////            } catch (SQLException exception) {
////                throw new RuntimeException(exception);
////            }
//            imageBlobs.add(imageString);
//        }
////        System.out.println(imageBlobs);
//        System.out.println("hello");
//        baiViet baiViet=new baiViet();
//        baiViet.setContent(title);
//        baiViet.setUser(user);
//        baiViet.setThoigianbinhluan(timestamp);
//        baiViet.setDislike(0);
//        baiViet.setImage(imageBlob);
//        baiViet.setHaha(0);
//        baiViet.setLike(0);
//        baiViet.setTim(0);
//        baiVietService.save(baiViet);
//
//        for (String image : imageBlobs) {
//            System.out.println("Processing image...");
//            vn.iback.studentmanager.entity.image image1=new image();
//            image1.setPath(image);
//            image1.setBaiViet(baiViet);
//            imageService.update(image1);
//        }
//
//
//        // After processing the data, you can return a response:
//        return new ResponseEntity<>("Post created successfully", HttpStatus.CREATED);
//    }
//    @GetMapping("/showListUser2")
//    public String showListUser2(Model model){
//        List<user> userList=userservice.findAll();
//        model.addAttribute("userList",userList);
//        return "user/showInformationUpdate";
//    }
//    @GetMapping("/showPageIfUser")
//    public String showListUser(@RequestParam("username") String username,Model model){
//        user user=userservice.findByUsername(username);
//
//        model.addAttribute("user",user);
//        return "user/showInformation";
//    }

//    @GetMapping("/showPageIfUserUpdate")
//    public String showListUserUpdate(@RequestParam("username") String username,Model model){
//        user user=userservice.findByUsername(username);
//        student student=studentService.findStudentById(user.getStudentId().getStudentId());
//        model.addAttribute("user",user);
//        model.addAttribute("student",student);
//        return "user/showInformationUpdate";
//    }

//    @PostMapping("/updateAvatar")
//    public String updateAvatar(@ModelAttribute("user") user user, @RequestParam("duongDanAnh") MultipartFile duongDanAnh, Model model){
//        userservice.update(user);
//        System.out.println(user.getAvatar());
//        System.out.println(user.getUsername());
//        System.out.println(user.getStudentId());
//        System.out.println(duongDanAnh.toString());
//        String folder= "D:\\Java\\studentmanager\\src\\main\\resources\\static\\avatar";
//        File file;
//
//        // gioi han nguoi dung upload file
//        int maxFileSize=1024*5024;
//        int maxMemSize= 1024 * 5024;
//
//        DiskFileItemFactory factory=new DiskFileItemFactory();
//        factory.setSizeThreshold(maxMemSize);
//
//
//        //tao file
//        String fileName="Customer"+duongDanAnh;
//        String path=folder+fileName;
//
//
//        if(duongDanAnh.isEmpty()){
//            return "user/showInformation";
//        }
//        Path path1= Paths.get("./avatar/");
//        try {
//            InputStream inputStream = duongDanAnh.getInputStream();
//            Files.copy(inputStream,path1.resolve(duongDanAnh.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
////
////            byte[] bytes=duongDanAnh.getOriginalFilename().toLowerCase().getBytes();
////            Blob avatar=new javax.sql.rowset.serial.SerialBlob(bytes);
//
//            System.out.println(duongDanAnh.getOriginalFilename().toLowerCase());
//            user.setAvatar(duongDanAnh.getOriginalFilename().toLowerCase());
//            userservice.update(user);
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//
//        model.addAttribute("user",user);
//        return "user/showInformation";
//    }
//    @PostMapping("/update-khoa")
//    public String updateKhoa(@RequestParam("selectedOption") String maKhoa , @RequestParam("username") String username, Model model){
//        khoa khoa=khoaService.findkyHocid(maKhoa);
//        user user=userservice.findByUsername(username);
//        user.setKhoa(khoa);
//        userservice.update(user);
////        return "redirect:/user/showListUser";
//        return "redirect:/home/showHome";
//    }

//    @PostMapping("/update-khoa")
//    public String updateKhoa(@ModelAttribute("user") user user , @RequestParam("id") String maKhoa, Model model){
//        khoa khoa=khoaService.findkyHocid(maKhoa);
//        user.setKhoa(khoa);
//        userservice.update(user);
//        return "redirect:/user/showListUser";
//    }
//@PostMapping("/update-specialization")
//public String updateSpecializatio(@RequestParam("selectedOption") String maChuyenNghanh, @RequestParam("username") String username,Model model){
//    specialization specialization=specializationService.findSpecializationByid(maChuyenNghanh);
//    user user=userservice.findByUsername(username);
//    user.setSpecialization(specialization);
//    userservice.update(user);
////    return "redirect:/user/showListUser";
//    return "redirect:/home/showHome";
//}

//    @PostMapping("/update-specialization")
//    public String updateSpecializatio(@ModelAttribute("user") user user,@RequestParam("id") String maChuyenNghanh,Model model){
//        specialization specialization=specializationService.findSpecializationByid(maChuyenNghanh);
//        user.setSpecialization(specialization);
//        userservice.update(user);
//        return "redirect:/user/showListUser";
//    }
//@PostMapping("/update-lop")
//public String updateLop(@RequestParam("selectedOption") String maLop, @RequestParam("username") String username, Model model){
//    lop lop=lopService.findLopById(maLop);
//    user user=userservice.findByUsername(username);
//    user.setLop(lop);
//    userservice.update(user);
////    return "redirect:/user/showListUser";
//    return "redirect:/home/showHome";
//}

//    @GetMapping("/UpdateEmail")
//    public String UpdateEmail(@RequestParam("username") String username ,Model model){
//        user user=userservice.findByUsername(username);
//        if(user.isTrangThaiXacThuc()){
//            model.addAttribute("user",user);
//            return "user/daxacthuc";
//        }else{
//            model.addAttribute("user",user);
//            return "user/xacthucemail";
//        }
//    }

    @PostMapping("/email")
    public ResponseEntity<String> xacthuc(@RequestParam("username") String username ,Model model)throws MessagingException {
        user user=userservice.findByUsername(username);
        Random random=new Random();
        Random rd= new Random();
        String s1=rd.nextInt(10)+"";
        String s2=rd.nextInt(10)+"";
        String s3=rd.nextInt(10)+"";
        String s4=rd.nextInt(10)+"";
        String s5=rd.nextInt(10)+"";
        String s6=rd.nextInt(10)+"";

        String s= s1+s2+s2+s4+s5+s6;
        String songaunhien=s;
        Date toDaysDay= new Date(new java.util.Date().getMinutes());
        Calendar c = Calendar.getInstance();
        c.setTime(toDaysDay);
        c.add(Calendar.MILLISECOND, 1);
        Date thoiGianHieuLucXacThuc = new Date(c.getTimeInMillis());

        boolean trangThaiXacThuc=false;
        user.setMaXacThuc(songaunhien);
        user.setThoiGianHieuLucCuaMaXacThuc(thoiGianHieuLucXacThuc);
        userservice.update(user);
        DataMailDTO dataMailDTO=new DataMailDTO();
        dataMailDTO.setTo(user.getEmail());
        dataMailDTO.setSubject("Mã xác thực tài khoản hệ thống iBack");
        Map<String, Object> props = new HashMap<>();
        props.put("name", user.getFirstname()+' '+user.getLastname());
        props.put("username", user.getUsername());
        props.put("songaunhien", songaunhien);
        props.put("avatar", user.getAvatar());
        dataMailDTO.setProps(props);
        mailService.sendHtmlMail(dataMailDTO, Const.SEND_MAIL_SUBJECT.CLIENT_REGISTER);
//            sendEmail("ibackcenter@gmail.com",user.getEmail(),Const.SEND_MAIL_SUBJECT.CLIENT_REGISTER,"hello");
        return new ResponseEntity<>("email sent successfully", HttpStatus.CREATED);
    }
//    @PostMapping("/xac-thuc-email")
//    public void sendEmail(String from,String to,String subject,String content){
//        SimpleMailMessage mailMessage=new SimpleMailMessage();
//        mailMessage.setFrom(from);
//        mailMessage.setTo(to);
//        mailMessage.setSubject(subject);
//        mailMessage.setText(content);
//        mailSender.send(mailMessage);
//
//    }

//    @PostMapping("/checkxacthuc")
//    public String checkxacthuc(@RequestParam("username") String username, HttpServletRequest request, Model model){
//        String maxacthuc=request.getParameter("maxacthuc");
//        user user=userservice.findByUsername(username);
//        if (user.getMaXacThuc().equals(maxacthuc)){
//            user.setTrangThaiXacThuc(true);
//            userservice.update(user);
//            model.addAttribute("user",user);
//            return "user/daxacthuc";
//        }else {
//            String baoLoi="Mã xác thực không chính xác";
//            model.addAttribute("loi",baoLoi);
//            model.addAttribute("user",user);
//            return "user/nhapmaxacthuc";
//        }
//
//
//    }

    @PutMapping("/role")
    public String updateRole(@RequestParam("Mã role")int id,@RequestParam("username") String username, Model model){
        user user=userservice.findByUsername(username);
        if(id==1){
            roles defautRole=roleRespository.findByName("ROLE_ADMIN");
            Collection<roles> roles=new ArrayList<>();
            roles.add(defautRole);
            user.setRoles(roles);
            userservice.update(user);
        } else if (id==2) {
            roles defautRole=roleRespository.findByName("ROLE_MANAGER");
            Collection<roles> roles=new ArrayList<>();
            roles.add(defautRole);
            user.setRoles(roles);
            userservice.update(user);
        }else if (id==3) {
            roles defautRole=roleRespository.findByName("ROLE_TEACHER");
            Collection<roles> roles=new ArrayList<>();
            roles.add(defautRole);
            user.setRoles(roles);
            userservice.update(user);
        }else if (id==4) {
            roles defautRole=roleRespository.findByName("ROLE_USER");
            Collection<roles> roles=new ArrayList<>();
            roles.add(defautRole);
            user.setRoles(roles);
            userservice.update(user);
        }

//        return "redirect:/user/showListUser";
        return "update success";
    }
//    @PostMapping("/update-lop")
//    public String updateLop(@ModelAttribute("user") user user , @RequestParam("id") String maLop, Model model){
//        lop lop=lopService.findLopById(maLop);
//        user.setLop(lop);
//        userservice.update(user);
//        return "redirect:/user/showListUser";
//    }

}
