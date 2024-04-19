package vn.iback.studentmanager.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.iback.studentmanager.entity.ketQuaHocTap;
import vn.iback.studentmanager.entity.khoa;
import vn.iback.studentmanager.entity.lichHoc;
import vn.iback.studentmanager.entity.user;
import vn.iback.studentmanager.service.Userservice;
import vn.iback.studentmanager.service.lichHocService.lichHocService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("lich-hoc")
public class LichHocController {
    private lichHocService lichHocService;
    private Userservice userservice;
    @Autowired
    public LichHocController(vn.iback.studentmanager.service.lichHocService.lichHocService lichHocService, Userservice userservice) {
        this.lichHocService = lichHocService;
        this.userservice = userservice;
    }
    @GetMapping("")
    public List<lichHoc> showPageClassApi(Model model){
        List<lichHoc> lichHocList = lichHocService.findAllLichHoc();
        return lichHocList;
    }

    @DeleteMapping("/ma-lich-hoc")
    public ResponseEntity deleteLichHocApi(@RequestParam int id){
        lichHoc existing = lichHocService.findkyHocid(id);
        if (existing != null){
            lichHocService.deleteById(id);
            return ResponseEntity.ok("success");
        }else{
            return (ResponseEntity) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/ma-lich-hoc")
    public ResponseEntity<List<lichHoc>> getLichHocByUsername(@RequestParam int id){
        lichHoc existing = lichHocService.findkyHocid(id);
        List<lichHoc> lichHocList=new ArrayList<>();
        if (existing != null){
            lichHocList.add(existing);
            return new ResponseEntity<>(lichHocList, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @DeleteMapping("")
//    public ResponseEntity<Void> deleteLichApi(@RequestParam int id, Model model){
//        lichHoc existing=lichHocService.findkyHocid(id);
//        if (existing!=null){
//            lichHocService.deleteById(id);
//            return ResponseEntity.ok().build();
//        }else{
//            return ResponseEntity.notFound().build();
//        }
//    }
//    @GetMapping("")
//    public List<lichHoc> showLichHocRestApi(){
//        List<lichHoc> lichHocList=lichHocService.findAllLichHoc();
//        return lichHocList;
//    }
//    @GetMapping("/delete")
//    public String deleteLichHoc(@RequestParam("id") int id, Model model){
//        lichHocService.deleteById(id);
////        return "redirect:/lich-hoc/showPageLichHoc";
//        return "redirect:/home/showHome";
//    }
//    @GetMapping("/showPageLichHoc")
//    public String showPageLichHoc(Model model){
//        List<lichHoc> lichHocList=lichHocService.findAllLichHoc();
//        model.addAttribute("lichHocList",lichHocList);
//        return "lichhoc/home";
//    }
//    @PutMapping("/update-rest-api/{id}")
//    public lichHoc updateKetQuaApi(@PathVariable int id, @RequestBody lichHoc lichHoc, Model model){
//        lichHoc lichHoc1=lichHocService.findkyHocid(id);
//        lichHoc1.setThoiGianHoc(lichHoc.getThoiGianHoc());
//        lichHoc1.setClassSchool(lichHoc.getClassSchool());
//        lichHocService.update(lichHoc);
//        return lichHoc1;
//    }

//    @GetMapping("/showPageLichHocUser")
//    public String showPageLichHocUser(@RequestParam("username") String username ,Model model){
//        List<lichHoc> lichHocList=lichHocService.findAllLichHoc();
//        user user=userservice.findByUsername(username);
//
//        model.addAttribute("lichHocList",lichHocList);
//        model.addAttribute("user",user);
//        return "lichhoc/showLichHocUser";
//    }


}
