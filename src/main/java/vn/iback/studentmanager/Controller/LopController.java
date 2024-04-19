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
import vn.iback.studentmanager.service.khoaService.khoaService;
import vn.iback.studentmanager.service.lopSevice.lopService;
import vn.iback.studentmanager.service.specializationService;
import vn.iback.studentmanager.service.teacherService;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/lop")
public class LopController {
    private lopService lopService;
    private khoaService khoaService;
    private specializationService specializationService;
    private teacherService teacherService;
    private Userservice userservice;
    @Autowired
    public LopController(vn.iback.studentmanager.service.lopSevice.lopService lopService, vn.iback.studentmanager.service.khoaService.khoaService khoaService, vn.iback.studentmanager.service.specializationService specializationService, vn.iback.studentmanager.service.teacherService teacherService, Userservice userservice) {
        this.lopService = lopService;
        this.khoaService = khoaService;
        this.specializationService = specializationService;
        this.teacherService = teacherService;
        this.userservice = userservice;
    }
    @GetMapping("")
    public List<lop> showPageClassApi(Model model){
        List<lop> lopList = lopService.findAllLop();
        return lopList;
    }

    @GetMapping("/ma-lop")
    public lop getById(@RequestParam String id){
        try {
            lop lop1 = lopService.findLopById(id);
            if (lop1 != null){
                return lop1;
            }else{
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tìm thấy lớp với ID: " + id);
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Có lỗi xảy ra: " + e.getMessage());
        }
    }

    @DeleteMapping("/ma-lop")
    public ResponseEntity deleteLopApi(@RequestParam String id){
        lop existing = lopService.findLopById(id);
        if (existing != null){
            lopService.deleteById(id);
            return ResponseEntity.ok("success");
        }else{
            return (ResponseEntity) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/ma-lop")
    public ResponseEntity updateLop(@RequestParam String id, @RequestBody lop lop, Model model){
        lop lop1 = lopService.findLopById(id);
        try {
            if (lop1 != null){
                lop1.setKhoa(lop.getKhoa());
                lop1.setStaff(lop.getStaff());
                lop1.setSpecialization(lop.getSpecialization());
                lop1.setTenLop(lop.getTenLop());
                lopService.update(lop1);
                return ResponseEntity.ok("update success");
            }else{
                return (ResponseEntity) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch (Exception e){
            return new ResponseEntity<>("failed: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity createLopApi(@RequestBody lop lop){
        try {
            if (lop != null){
                Random random = new Random();
                int randomNumber = random.nextInt(10000); // Số ngẫu nhiên giới hạn trong khoảng từ 0 đến 9999

                // Kết hợp chuỗi "LH" và số ngẫu nhiên để tạo lopId
                String lopId = "LH" + randomNumber;
                lop.setMaLop(lopId);
                lopService.save(lop);
                return ResponseEntity.ok("update success");
            }else{
                return (ResponseEntity) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch (Exception e){
            return new ResponseEntity<>("failed: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
//    @GetMapping("/showPageLop")
//    public String showPageLop(Model model){
//        List<lop> lopList=lopService.findAllLop();
//        model.addAttribute("lopList",lopList);
//        return "lop/home";
//    }
//@PutMapping("/update-rest-api/{id}")
//public lop updateLop(@PathVariable String id,@RequestBody lop lop,Model model){
//    lop lop11= lopService.findLopById(id);
//    lop11.setKhoa(lop.getKhoa());
//    lop11.setStaff(lop.getStaff());
//    lop11.setSpecialization(lop.getSpecialization());
//    lop11.setTenLop(lop.getTenLop());
//    lopService.update(lop11);
//    return lop11;
//}
//    @GetMapping("/showLopRestApi")
//    public List<lop> showLopRestApi(){
//        List<lop> lopList=lopService.findAllLop();
//        return lopList;
//    }
//    @PostMapping("/create-rest-api")
//    public lop createLopApi(@RequestBody lop lop){
//        Random random = new Random();
//        int randomNumber = random.nextInt(10000); // Số ngẫu nhiên giới hạn trong khoảng từ 0 đến 9999
//
//// Kết hợp chuỗi "ST" và số ngẫu nhiên để tạo studentId
//        String lopId= "LH" + randomNumber;
//        lop.setMaLop(lopId);
//        lopService.save(lop);
//        return lop;
//    }
//    @DeleteMapping("delete/{id}")
//    public ResponseEntity deleteLichApi(@PathVariable String id, Model model){
//        lop existing=lopService.findLopById(id);
//        if (existing!=null){
//            lopService.deleteById(id);
//            return ResponseEntity.ok("success");
//        }else{
//            return (ResponseEntity) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//    @PostMapping("/update-khoa2")
//    public String updateKhoa(@ModelAttribute("lop") lop lop,@RequestParam("id") String id,Model model){
//        khoa khoa=khoaService.findkyHocid(id);
//        lop.setKhoa(khoa);
//        lopService.update(lop);
//        return "redirect:/lop/showPageLop";
//    }

//    @PostMapping("/update-khoa")
//    public String updateKhoa2(@RequestParam("selectedOption") String selectedOption,@RequestParam("idLop") String id,Model model){
//        lop lop= lopService.findLopById(id);
//
//        khoa khoa=khoaService.findkyHocid(selectedOption);
//        lop.setKhoa(khoa);
//        lopService.update(lop);
////        return "redirect:/lop/showPageLop";
//        return "redirect:/home/showHome";
//    }
//    @PostMapping("/update-specialization")
//    public String updateSpecialization(@RequestParam("selectedOption") String idSpe,@RequestParam("idLop") String id,Model model){
//        lop lop= lopService.findLopById(id);
//        specialization specialization=specializationService.findSpecializationByid(idSpe);
//        lop.setSpecialization(specialization);
//        lopService.update(lop);
////        return "redirect:/lop/showPageLop";
//        return "redirect:/home/showHome";
//    }
//    @PostMapping("/update-specialization")
//    public String updateSpecialization(@ModelAttribute("lop") lop lop,@RequestParam("id") String id,Model model){
//        specialization specialization=specializationService.findSpecializationByid(id);
//        lop.setSpecialization(specialization);
//        lopService.update(lop);
//        return "redirect:/lop/showPageLop";
//    }
//@PostMapping("/update-teacher")
//public String updateTeacher(@RequestParam("selectedOption") String idStaff,@RequestParam("idLop") String id,Model model){
//    lop lop= lopService.findLopById(id);
//    staff teacher=teacherService.findTeacherByid(idStaff);
//    lop.setStaff(teacher);
//    lopService.update(lop);
//    return "redirect:/home/showHome";
//}
////    @PostMapping("/update-teacher")
////    public String updateTeacher(@ModelAttribute("lop") lop lop,@RequestParam("id") String id,Model model){
////        staff teacher=teacherService.findTeacherByid(id);
////        lop.setStaff(teacher);
////        lopService.update(lop);
////        return "redirect:/lop/showPageLop";
////    }
//    @GetMapping("/showListClass-user")
//    public String showListKhoaUser(@RequestParam("id") String id,Model model){
//        user user=userservice.findByUsername(id);
//        List<lop> lopList=lopService.findAllLop();
//        model.addAttribute("user",user);
//        model.addAttribute("lopList",lopList);
//        return "lop/home";
//    }
//
//    @GetMapping("/delete")
//    public String deleteLop(@RequestParam("id") String id,Model model){
//        lopService.deleteById(id);
//        return "redirect:/home/showHome";
//    }
//
//    @GetMapping("/showUpdate")
//    public String showUpdate(@RequestParam("id") String id,Model model){
//        lop lop=lopService.findLopById(id);
//        model.addAttribute("lop",lop);
//        return "lop/update";
//    }
//    @PostMapping("/update")
//    public String update(@ModelAttribute("lop") lop lop,Model model){
//        lopService.update(lop);
//        return "redirect:/home/showHome";
//    }

//    @GetMapping("/create")
//    public String createLop(Model model){
//        lop lop=new lop();
//        Random random = new Random();
//        int randomNumber = random.nextInt(10000); // Số ngẫu nhiên giới hạn trong khoảng từ 0 đến 9999
//
//// Kết hợp chuỗi "ST" và số ngẫu nhiên để tạo studentId
//        String lopId= "LH" + randomNumber;
//        lop.setMaLop(lopId);
//        lopService.save(lop);
//        return "redirect:/home/showHome";
//    }


}
