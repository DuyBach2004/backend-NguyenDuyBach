package vn.iback.studentmanager.Controller.user;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.iback.studentmanager.entity.*;
import vn.iback.studentmanager.service.StudentIntoNoteBookService;
import vn.iback.studentmanager.service.Userservice;
import vn.iback.studentmanager.service.baiVietService.baiVietService;
import vn.iback.studentmanager.service.binhluanService.binhluanService;
import vn.iback.studentmanager.service.classService;
import vn.iback.studentmanager.service.lichHocService.lichHocService;
import vn.iback.studentmanager.service.nootebookService;

import java.util.List;
@Controller
@RequestMapping("/homeUser")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class homeUserController {

    private vn.iback.studentmanager.service.classService classService;
    private vn.iback.studentmanager.service.nootebookService nootebookService;
    private StudentIntoNoteBookService studentIntoNoteBookService;
    private baiVietService baiVietService;
    private binhluanService binhluanService;
    private Userservice userservice;
    @Autowired
    public homeUserController(vn.iback.studentmanager.service.classService classService, vn.iback.studentmanager.service.nootebookService nootebookService, StudentIntoNoteBookService studentIntoNoteBookService, vn.iback.studentmanager.service.baiVietService.baiVietService baiVietService, vn.iback.studentmanager.service.binhluanService.binhluanService binhluanService, Userservice userservice) {
        this.classService = classService;
        this.nootebookService = nootebookService;
        this.studentIntoNoteBookService = studentIntoNoteBookService;
        this.baiVietService = baiVietService;
        this.binhluanService = binhluanService;
        this.userservice = userservice;
    }

    @GetMapping("/showHome")
    public String showHomePage(@RequestParam("username") String username , Model model){
        List<notebook> notebookList=nootebookService.findAllNote();
        List<classSchool> classSchoolList=classService.findAllClass();
        user user=userservice.findByUsername(username);
        model.addAttribute("user",user);
        model.addAttribute("notebookList",notebookList);
        model.addAttribute("classSchoolList",classSchoolList);
        return "dangkyhocphan/home";
    }
    @GetMapping()
    public String showHomeUser(Model model, HttpServletRequest request){

        User user1=(User)request.getSession().getAttribute("user");
        user user=userservice.findByUsername(user1.getUsername());
        System.out.println("Tui da vao day");
        List<baiViet> baiVietList=baiVietService.findAllBaiViet();
        List<binhluan> binhluanList=binhluanService.findAllBinhluan();
        model.addAttribute("baiVietList",baiVietList);
        model.addAttribute("binhluanList",binhluanList);
        model.addAttribute("user",user);
        return "homeUser";
    }
    @GetMapping("/user")
    @ResponseBody
    public user showHome(Model model, HttpServletRequest request){

        User user1=(User)request.getSession().getAttribute("user");
        user user=userservice.findByUsername(user1.getUsername());

        return user;
    }

}
