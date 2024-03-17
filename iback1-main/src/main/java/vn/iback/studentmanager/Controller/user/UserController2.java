package vn.iback.studentmanager.Controller.user;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.iback.studentmanager.dao.RoleRespository;
import vn.iback.studentmanager.dto.DataMailDTO;
import vn.iback.studentmanager.entity.*;
import vn.iback.studentmanager.service.Userservice;
import vn.iback.studentmanager.service.baiVietService.baiVietService;
import vn.iback.studentmanager.service.binhluanService.binhluanService;
import vn.iback.studentmanager.service.imageService.imageService;
import vn.iback.studentmanager.service.khoaService.khoaService;
import vn.iback.studentmanager.service.lopSevice.lopService;
import vn.iback.studentmanager.service.mailService.MailService;
import vn.iback.studentmanager.service.phanHoiService.phanHoiService;
import vn.iback.studentmanager.service.specializationService;
import vn.iback.studentmanager.service.userPostService.userPostService;
import vn.iback.studentmanager.utils.Const;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.*;
@Controller
@RequestMapping(value = "/user" )
public class UserController2 {
    private Userservice userservice;
    private vn.iback.studentmanager.service.studentService studentService;
    private vn.iback.studentmanager.service.khoaService.khoaService khoaService;
    private vn.iback.studentmanager.service.lopSevice.lopService lopService;
    private RoleRespository roleRespository;
    private MailService mailService;
    private MailSender mailSender;
    private vn.iback.studentmanager.service.specializationService specializationService;
    private vn.iback.studentmanager.service.baiVietService.baiVietService baiVietService;
    private vn.iback.studentmanager.service.imageService.imageService imageService;
    private vn.iback.studentmanager.service.binhluanService.binhluanService binhluanService;
    private vn.iback.studentmanager.service.phanHoiService.phanHoiService phanHoiService;

    @Autowired
    public UserController2(Userservice userservice, vn.iback.studentmanager.service.studentService studentService, vn.iback.studentmanager.service.khoaService.khoaService khoaService, vn.iback.studentmanager.service.lopSevice.lopService lopService, RoleRespository roleRespository, MailService mailService, MailSender mailSender, vn.iback.studentmanager.service.specializationService specializationService, vn.iback.studentmanager.service.baiVietService.baiVietService baiVietService, vn.iback.studentmanager.service.imageService.imageService imageService, vn.iback.studentmanager.service.binhluanService.binhluanService binhluanService, vn.iback.studentmanager.service.phanHoiService.phanHoiService phanHoiService) {
        this.userservice = userservice;
        this.studentService = studentService;
        this.khoaService = khoaService;
        this.lopService = lopService;
        this.roleRespository = roleRespository;
        this.mailService = mailService;
        this.mailSender = mailSender;
        this.specializationService = specializationService;
        this.baiVietService = baiVietService;
        this.imageService = imageService;
        this.binhluanService = binhluanService;
        this.phanHoiService = phanHoiService;
    }

    @GetMapping("/update")
    public String updateStudent(@RequestParam("username") String username, Model model){
        user user=userservice.findByUsername(username);
        student student=studentService.findStudentById(user.getStudentId().getStudentId());
        model.addAttribute("student",student);
        return "user/update";
    }

    @PostMapping("/update-if")
    public String updateInfor(@ModelAttribute("user") user user, Model model){
        userservice.update(user);
        System.out.println(user.getStudentId().getDantoc());
        student student=studentService.findStudentById(user.getStudentId().getStudentId());
        student.setBirthday(user.getStudentId().getBirthday());
        student.setTongiao(user.getStudentId().getTongiao());
        student.setGioitinh(user.getStudentId().getGioitinh());
        student.setDantoc(user.getStudentId().getDantoc());
        student.setQuoctich(user.getStudentId().getQuoctich());
        student.setCccd(user.getStudentId().getCccd());
        student.setNgaycap(user.getStudentId().getNgaycap());
        student.setNoicap(user.getStudentId().getNoicap());
        student.setSodienthoai(user.getStudentId().getSodienthoai());
        student.setNgayvaodoan(user.getStudentId().getNgayvaodoan());
        student.setNgayvaodang(user.getStudentId().getNgayvaodang());
        student.setTinhthanh(user.getStudentId().getTinhthanh());
        student.setHuyenquan(user.getStudentId().getHuyenquan());
        student.setXa_phuong(user.getStudentId().getXa_phuong());

        studentService.update(student);
        return "user/showInformationUpdate";
    }
    @GetMapping("/delete")
    public String deleteStudent(@RequestParam("id") String username, Model model){
        userservice.deleteByUsername(username);
        return "redirect:homeUser";
    }
    @GetMapping("/showListUser")
    public String showListUser(Model model){
        List<user> userList=userservice.findAll();
        model.addAttribute("userList",userList);
        return "user/home";
    }
    //    @RequestMapping(value = "/show", method = RequestMethod.GET)
//    @ResponseBody
    @GetMapping("/show")
    public List<baiViet> showBaiViet(Model model){
        List<baiViet> baiVietList =baiVietService.findAllBaiViet();
        System.out.println(baiVietList);
        return baiVietList;
    }


    @GetMapping("/showBinhLuan")
    public  List<binhluan> showBinhLuan(Model model){
        System.out.println("HELLO");
        List<baiViet> baiVietList =baiVietService.findAllBaiViet();
        List<binhluan> list=new ArrayList<>();
        List<binhluan> binhluanList=new ArrayList<>();
        for (baiViet baiViet:baiVietList){
            binhluanList=binhluanService.findAllBinhluanOfBaiViet(baiViet);
            list.addAll(binhluanList);

        }
        return  list;
    }
    @GetMapping("/showPhanHoi")
    public List<phanHoi> showPhanHoi(Model model){
        List<binhluan> binhluanList=binhluanService.findAllBinhluan();
        List<phanHoi> list=new ArrayList<>();
        List<phanHoi> phanHoiList= new ArrayList<>();
        for (binhluan binhluan:binhluanList){
            phanHoiList=phanHoiService.findAllPhanHoiOfBinhLuan(binhluan);
            list.addAll(phanHoiList);
        }

        return list;
    }

    @GetMapping("/showListUser2")
    public String showListUser2(Model model){
        List<user> userList=userservice.findAll();
        model.addAttribute("userList",userList);
        return "user/showInformationUpdate";
    }
    @GetMapping("/showPageIfUser")
    public String showListUser(@RequestParam("username") String username,Model model){
        user user=userservice.findByUsername(username);

        model.addAttribute("user",user);
        return "user/showInformation";
    }

    @GetMapping("/showPageIfUserUpdate")
    public String showListUserUpdate(@RequestParam("username") String username,Model model){
        user user=userservice.findByUsername(username);
        student student=studentService.findStudentById(user.getStudentId().getStudentId());
        model.addAttribute("user",user);
        model.addAttribute("student",student);
        return "user/showInformationUpdate";
    }

    @PostMapping("/updateAvatar")
    public String updateAvatar(@ModelAttribute("user") user user, @RequestParam("duongDanAnh") MultipartFile duongDanAnh, Model model){
        userservice.update(user);
        System.out.println(user.getAvatar());
        System.out.println(user.getUsername());
        System.out.println(user.getStudentId());
        System.out.println(duongDanAnh);
        String folder= "D:\\Java\\studentmanager\\src\\main\\resources\\static\\avatar";
        File file;

        // gioi han nguoi dung upload file
        int maxFileSize=1000*5024;
        int maxMemSize=1000*5024;

        DiskFileItemFactory factory=new DiskFileItemFactory();
        factory.setSizeThreshold(maxMemSize);


        //tao file
        String fileName="Customer"+duongDanAnh;
        String path=folder+fileName;

        if(duongDanAnh.isEmpty()){
            return "user/showInformation";
        }
        Path path1= Paths.get("./avatar/");
        try {
            InputStream inputStream = duongDanAnh.getInputStream();
            Files.copy(inputStream,path1.resolve(duongDanAnh.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
//
//            byte[] bytes=duongDanAnh.getOriginalFilename().toLowerCase().getBytes();
//            Blob avatar=new javax.sql.rowset.serial.SerialBlob(bytes);
            user.setAvatar(duongDanAnh.getOriginalFilename().toLowerCase());
            userservice.update(user);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        model.addAttribute("user",user);
        return "user/showInformation";
    }

    @PostMapping("/update-khoa")
    public String updateKhoa(@ModelAttribute("user") user user , @RequestParam("id") String maKhoa, Model model){
        khoa khoa=khoaService.findkyHocid(maKhoa);
        user.setKhoa(khoa);
        userservice.update(user);
        return "redirect:/user/showListUser";
    }
    @PostMapping("/update-specialization")
    public String updateSpecializatio(@ModelAttribute("user") user user,@RequestParam("id") String maChuyenNghanh,Model model){
        specialization specialization=specializationService.findSpecializationByid(maChuyenNghanh);
        user.setSpecialization(specialization);
        userservice.update(user);
        return "redirect:/user/showListUser";
    }

    @GetMapping("/UpdateEmail")
    public String UpdateEmail(@RequestParam("username") String username ,Model model){
        user user=userservice.findByUsername(username);
        if(user.isTrangThaiXacThuc()){
            model.addAttribute("user",user);
            return "user/daxacthuc";
        }else{
            model.addAttribute("user",user);
            return "user/xacthucemail";
        }
    }

    @PostMapping("/xacthuc")
    public String xacthuc(@RequestParam("username") String username ,Model model)throws MessagingException {
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
        model.addAttribute("user",user);
        return "user/nhapmaxacthuc";
    }
    public void sendEmail(String from,String to,String subject,String content){
        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setFrom(from);
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(content);
        mailSender.send(mailMessage);

    }

    @PostMapping("/checkxacthuc")
    public String checkxacthuc(@RequestParam("username") String username, HttpServletRequest request, Model model){
        String maxacthuc=request.getParameter("maxacthuc");
        user user=userservice.findByUsername(username);
        if (user.getMaXacThuc().equals(maxacthuc)){
            user.setTrangThaiXacThuc(true);
            userservice.update(user);
            model.addAttribute("user",user);
            return "user/daxacthuc";
        }else {
            String baoLoi="Mã xác thực không chính xác";
            model.addAttribute("loi",baoLoi);
            model.addAttribute("user",user);
            return "user/nhapmaxacthuc";
        }


    }

    @GetMapping("/updateRole")
    public String updateRole(@RequestParam("username") String username ,@RequestParam("id") int id, Model model){
        //        roles defautRole=roleRepository.findByName("ROLE_USER");
//        Collection<roles> roles=new ArrayList<>();
//        roles.add(defautRole);
//        user.setRoles(roles);
//        userservice.save(user);
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

        return "redirect:/user/showListUser";
    }
    @PostMapping("/update-lop")
    public String updateLop(@ModelAttribute("user") user user , @RequestParam("id") String maLop, Model model){
        lop lop=lopService.findLopById(maLop);
        user.setLop(lop);
        userservice.update(user);
        return "redirect:/user/showListUser";
    }

}
