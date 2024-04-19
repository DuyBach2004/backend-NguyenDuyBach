package vn.iback.studentmanager.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import vn.iback.studentmanager.entity.*;
import vn.iback.studentmanager.service.Userservice;
import vn.iback.studentmanager.service.ketQuaHocTapService.ketQuaHocTapService;
import vn.iback.studentmanager.service.khoaService.khoaService;
import vn.iback.studentmanager.service.lopSevice.lopService;
import vn.iback.studentmanager.service.specializationService;
import vn.iback.studentmanager.service.teacherService;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/khoa")
public class KhoaController {
    private khoaService khoaService;
    private specializationService specializationService;
    private teacherService teacherService;
    private lopService lopService;
    private Userservice userservice;
    @Autowired
    public KhoaController(vn.iback.studentmanager.service.khoaService.khoaService khoaService, vn.iback.studentmanager.service.specializationService specializationService, vn.iback.studentmanager.service.teacherService teacherService, vn.iback.studentmanager.service.lopSevice.lopService lopService, Userservice userservice) {
        this.khoaService = khoaService;
        this.specializationService = specializationService;
        this.teacherService = teacherService;
        this.lopService = lopService;
        this.userservice = userservice;
    }
    @GetMapping("")
    public List<khoa> showPageClassApi(Model model){
        List<khoa> khoaList = khoaService.findAllKhoa();
        return khoaList;
    }

    @GetMapping("/ma-khoa")
    public khoa getById(@RequestParam String id){
        try {
            khoa khoa1 = khoaService.findkyHocid(id);
            if (khoa1 != null){
                return khoa1;
            }else{
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tìm thấy khoa với ID: " + id);
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Có lỗi xảy ra: " + e.getMessage());
        }
    }

    @DeleteMapping("/ma-khoa")
    public ResponseEntity deleteKhoaApi(@RequestParam String id){
        khoa existing = khoaService.findkyHocid(id);
        if (existing != null){
            khoaService.deleteById(id);
            return ResponseEntity.ok("success");
        }else{
            return (ResponseEntity) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/ma-khoa")
    public ResponseEntity updateKhoa(@RequestParam String id, @RequestBody khoa khoa, Model model){
        khoa khoa1 = khoaService.findkyHocid(id);
        try {
            if (khoa1 != null){
                khoa1.setTenKhoa(khoa.getTenKhoa());
                khoaService.save(khoa1);
                return ResponseEntity.ok("update success");
            }else{
                return (ResponseEntity) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch (Exception e){
            return new ResponseEntity<>("failed: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public khoa createKhoaApi(@RequestBody khoa khoa){
        Random random = new Random();
        int randomNumber = random.nextInt(10000); // Số ngẫu nhiên giới hạn trong khoảng từ 0 đến 9999

        // Kết hợp chuỗi "DHCN" và số ngẫu nhiên để tạo Khoaid
        String Khoaid = "DHCN" + randomNumber;
        khoa.setMaKhoa(Khoaid);
        khoaService.save(khoa);
        return khoa;
    }
//    @GetMapping("/showPageKhoa")
//    public String showPageKhoa(Model model){
//        List<khoa> khoaList=khoaService.findAllKhoa();
//        model.addAttribute("khoaList",khoaList);
//        return "khoa/home";
//    }
//    @GetMapping("/showKhoaRestApi")
//    public List<khoa> showKhoaRestApi(){
//        List<khoa> khoaList=khoaService.findAllKhoa();
//        return khoaList;
//    }
//    @PutMapping("/update-rest-api/{id}")
//    public khoa updateKhoa(@PathVariable String id,@RequestBody khoa khoa,Model model){
//        khoa khoa1= khoaService.findkyHocid(id);
//        khoa1.setTenKhoa(khoa.getTenKhoa());
//        khoaService.save(khoa1);
//        return khoa1;
//    }
//    @PostMapping("/create-rest-api")
//    private khoa createKhoaApi(@RequestBody khoa khoa){
//        Random random = new Random();
//        int randomNumber = random.nextInt(10000); // Số ngẫu nhiên giới hạn trong khoảng từ 0 đến 9999
//
//// Kết hợp chuỗi "ST" và số ngẫu nhiên để tạo studentId
//        String Khoaid = "DHCN" + randomNumber;
//        khoa.setMaKhoa(Khoaid);
//        khoaService.save(khoa);
//        return khoa;
//    }
//    @DeleteMapping("delete/{id}")
//    public ResponseEntity deleteKhoaApi(@PathVariable String id, Model model){
//        khoa existing= khoaService.findkyHocid(id);
//        if (existing!=null){
//            khoaService.deleteById(id);
//            return ResponseEntity.ok("success");
//        }else{
//            return ResponseEntity.notFound().build();
//        }
//    }
//    @GetMapping("/showListKhoa-specialization")
//    public String showPageKhoa(@RequestParam("id") String id,Model model){
//        specialization specialization=specializationService.findSpecializationByid(id);
//        List<khoa> khoaList=khoaService.findAllKhoa();
//        model.addAttribute("specialization",specialization);
//        model.addAttribute("khoaList",khoaList);
//        return "khoa/home";
//    }
//    @GetMapping("/showListKhoa-lop")
//    public String showListKhoaLop(@RequestParam("id") String id,Model model){
//        lop lop=lopService.findLopById(id);
//        List<khoa> khoaList=khoaService.findAllKhoa();
//        model.addAttribute("lop",lop);
//        model.addAttribute("khoaList",khoaList);
//        return "khoa/home";
//    }
//    @GetMapping("/showListKhoa-teacher")
//    public String showListKhoaTeacher(@RequestParam("id") String id,Model model){
//            staff staff=teacherService.findTeacherByid(id);
//            List<khoa> khoaList=khoaService.findAllKhoa();
//            model.addAttribute("teacher",staff);
//            model.addAttribute("khoaList",khoaList);
//            return "khoa/home";
//    }
//    @GetMapping("/showListKhoa-user")
//    public String showListKhoaUser(@RequestParam("id") String id,Model model){
//        user user=userservice.findByUsername(id);
//        List<khoa> khoaList=khoaService.findAllKhoa();
//        model.addAttribute("user",user);
//        model.addAttribute("khoaList",khoaList);
//        return "khoa/home";
//    }
//
//    @GetMapping("/delete")
//    public String deleteKhoa(@RequestParam("id") String maKhoa,Model model){
//        khoaService.deleteById(maKhoa);
//        return "redirect:/home/showHome";
//    }
//    @GetMapping("/showPageUpdate")
//    public String showPageUpdate(@RequestParam("id") String id,Model model){
//        khoa khoa=khoaService.findkyHocid(id);
//        model.addAttribute("khoa",khoa);
//        return "khoa/update";
//    }
//    @PostMapping("update")
//    public String updateKhoa(@ModelAttribute("khoa") khoa khoa,Model model){
//        khoaService.update(khoa);
//        return "redirect:/home/showHome";
//    }

//    @GetMapping("/create")
//    private String createKhoa(Model model){
//        khoa khoa = new khoa();
//        Random random = new Random();
//        int randomNumber = random.nextInt(10000); // Số ngẫu nhiên giới hạn trong khoảng từ 0 đến 9999
//
//// Kết hợp chuỗi "ST" và số ngẫu nhiên để tạo studentId
//        String Khoaid = "DHCN" + randomNumber;
//        khoa.setMaKhoa(Khoaid);
//        khoaService.save(khoa);
//        return "redirect:/home/showHome";
//    }

}
