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
import vn.iback.studentmanager.service.hokyservice.hokyService;
import vn.iback.studentmanager.service.ketQuaHocTapService.ketQuaHocTapService;

import java.util.List;

@RestController
@RequestMapping("/ketquahoctap")
public class KetQuaHocTapController {
    private ketQuaHocTapService ketQuaHocTapService;
    private Userservice userservice;
    private hokyService hokyService;
    @Autowired
    public KetQuaHocTapController(vn.iback.studentmanager.service.ketQuaHocTapService.ketQuaHocTapService ketQuaHocTapService, Userservice userservice, vn.iback.studentmanager.service.hokyservice.hokyService hokyService) {
        this.ketQuaHocTapService = ketQuaHocTapService;
        this.userservice = userservice;
        this.hokyService = hokyService;
    }
    @GetMapping("")
    public List<ketQuaHocTap> showPageClassApi(Model model){
        List<ketQuaHocTap> ketQuaHocTapList = ketQuaHocTapService.findAllKetQuaHocTap();
        return ketQuaHocTapList;
    }

    @GetMapping("/ma-ket-qua-hoc-tap")
    public ketQuaHocTap getById(@RequestParam int id){
        try {
            ketQuaHocTap ketQuaHocTap1 = ketQuaHocTapService.findKetQuaHocTapById(id);
            if (ketQuaHocTap1 != null){
                return ketQuaHocTap1;
            }else{
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tìm thấy kết quả học tập với ID: " + id);
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Có lỗi xảy ra: " + e.getMessage());
        }
    }

    @DeleteMapping("/ma-ket-qua-hoc-tap")
    public ResponseEntity deleteKetQuaHocTapApi(@RequestParam int id){
        ketQuaHocTap existing = ketQuaHocTapService.findKetQuaHocTapById(id);
        if (existing != null){
            ketQuaHocTapService.deleteById(id);
            return ResponseEntity.ok("success");
        }else{
            return (ResponseEntity) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/ma-ket-qua-hoc-tap")
    public ResponseEntity updateKetQuaHocTap(@RequestParam int id, @RequestBody ketQuaHocTap ketQuaHocTap, Model model){
        ketQuaHocTap ketQuaHocTap1 = ketQuaHocTapService.findKetQuaHocTapById(id);
        try {
            if (ketQuaHocTap1 != null){
                ketQuaHocTap1.setKetQuaHocTap(ketQuaHocTap.getKetQuaHocTap());
                ketQuaHocTap1.setUser(ketQuaHocTap.getUser());
                ketQuaHocTap1.setKyHoc(ketQuaHocTap.getKyHoc());
                ketQuaHocTap1.setDiemTrungBinh(ketQuaHocTap.getDiemTrungBinh());
                ketQuaHocTap1.setDiemRenLuyen(ketQuaHocTap.getDiemRenLuyen());
                ketQuaHocTapService.update(ketQuaHocTap1);
                return ResponseEntity.ok("update success");
            }else{
                return (ResponseEntity) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch (Exception e){
            return new ResponseEntity<>("failed: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @PostMapping("")
//    public ketQuaHocTap createKetQuaHocTap(@RequestBody ketQuaHocTap ketQuaHocTap){
//        ketQuaHocTap.setDiemTrungBinh(-1);
//        ketQuaHocTap.setDiemRenLuyen(-1);
//        ketQuaHocTapService.save(ketQuaHocTap);
//        return ketQuaHocTap;
//    }
    @PostMapping("")
    public ResponseEntity createNotebookApi(@RequestBody ketQuaHocTap ketQuaHocTap){
        try {
            if (ketQuaHocTap != null){
                ketQuaHocTapService.save(ketQuaHocTap);
                return ResponseEntity.ok("update success");
            }else{
                return (ResponseEntity) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch (Exception e){
            return new ResponseEntity<>("failed: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
//    @GetMapping("/showPageKetQuaHocTap")
//    public String showPageKetQuaHocTap(Model model){
//        List<ketQuaHocTap> ketQuaHocTapList=ketQuaHocTapService.findAllKetQuaHocTap();
//        model.addAttribute("ketQuaHocTapList",ketQuaHocTapList);
//        return "ketqua/home";
//    }
//    @GetMapping("/showKetQuaHocTapRestApi")
//    public List<ketQuaHocTap> showKetQuaHocTapRestApi(){
//        List<ketQuaHocTap> ketQuaHocTapList=ketQuaHocTapService.findAllKetQuaHocTap();
//        return ketQuaHocTapList;
//    }
//    @PostMapping("/create-rest-api")
//    public ketQuaHocTap createDiemApi(){
//        ketQuaHocTap ketQuaHocTap=new ketQuaHocTap();
//        ketQuaHocTap.setDiemTrungBinh(-1);
//        ketQuaHocTap.setDiemRenLuyen(-1);
//
//        ketQuaHocTapService.save(ketQuaHocTap);
////        return "redirect:/ketquahoctap/showPageKetQuaHocTap";
//        return ketQuaHocTap;
//    }
//    @PutMapping("/update-rest-api/{id}")
//    public ketQuaHocTap updateKetQuaApi(@PathVariable int id,@RequestBody ketQuaHocTap ketQuaHocTap,Model model){
//        vn.iback.studentmanager.entity.ketQuaHocTap ketQuaHocTap1=ketQuaHocTapService.findKetQuaHocTapById(id);
//        ketQuaHocTap1.setKetQuaHocTap(ketQuaHocTap.getKetQuaHocTap());
//        ketQuaHocTap1.setUser(ketQuaHocTap.getUser());
//        ketQuaHocTap1.setKyHoc(ketQuaHocTap.getKyHoc());
//        ketQuaHocTap1.setDiemTrungBinh(ketQuaHocTap.getDiemTrungBinh());
//        ketQuaHocTap1.setDiemRenLuyen(ketQuaHocTap.getDiemRenLuyen());
//        ketQuaHocTapService.update(ketQuaHocTap1);
//        return ketQuaHocTap1;
//    }
//    @DeleteMapping("delete/{id}")
//    public ResponseEntity<Void> deleteDiemApi(@PathVariable int id, Model model){
//        ketQuaHocTap existing= ketQuaHocTapService.findKetQuaHocTapById(id);
//        if (existing!=null){
//            ketQuaHocTapService.deleteById(id);
//            return ResponseEntity.ok().build();
//        }else{
//            return ResponseEntity.notFound().build();
//        }
//    }
//    @GetMapping("/create")
//    public String createDiem(Model model){
//        ketQuaHocTap ketQuaHocTap=new ketQuaHocTap();
//        ketQuaHocTap.setDiemTrungBinh(-1);
//        ketQuaHocTap.setDiemRenLuyen(-1);
//
//        ketQuaHocTapService.save(ketQuaHocTap);
////        return "redirect:/ketquahoctap/showPageKetQuaHocTap";
//        return "redirect:/home/showHome";
//    }

//    @GetMapping("/showPageUpdate")
//    public String showPageUpdate(@RequestParam("id") int id,Model model){
//        ketQuaHocTap ketQuaHocTap=ketQuaHocTapService.findKetQuaHocTapById(id);
//        model.addAttribute("ketQuaHocTap",ketQuaHocTap);
//        return "ketqua/update";
//    }


//    @PostMapping("/update")
//    public String updateKetQua(@ModelAttribute("ketQuaHocTap") ketQuaHocTap ketQuaHocTap,Model model){
//
//        System.out.println(ketQuaHocTap);
//        System.out.println(ketQuaHocTap.getDiemRenLuyen());
//        ketQuaHocTap.setDiemRenLuyen(ketQuaHocTap.getDiemRenLuyen());
//        ketQuaHocTapService.update(ketQuaHocTap);
////        return "redirect:/ketquahoctap/showPageKetQuaHocTap";
//        return "redirect:/home/showHome";
//    }
//    @PostMapping("/update-user")
//    public String updateUser(@RequestParam("selectedOption") String username,@RequestParam("idKetQua") int idKetQua,Model model){
//        user user=userservice.findByUsername(username);
//        ketQuaHocTap ketQuaHocTap=ketQuaHocTapService.findKetQuaHocTapById(idKetQua);
//        ketQuaHocTap.setUser(user);
//        ketQuaHocTapService.update(ketQuaHocTap);
//        return "redirect:/home/showHome";
//    }

//    @PostMapping("/update-hocKy")
//    public String updateHocKy(@RequestParam("selectedOption") int idHocky,@RequestParam("idKetQua") int idKetQua,Model model){
//        kyHoc hcKyHoc=hokyService.findkyHocid(idHocky);
//        ketQuaHocTap ketQuaHocTap=ketQuaHocTapService.findKetQuaHocTapById(idKetQua);
//        ketQuaHocTap.setKyHoc(hcKyHoc);
//        ketQuaHocTapService.update(ketQuaHocTap);
//        return "redirect:/home/showHome";
//    }


//    @GetMapping("delete")
//    public String deleteDiem(@RequestParam("id") int id,Model model){
//        ketQuaHocTapService.deleteById(id);
////        return "redirect:/ketquahoctap/showPageKetQuaHocTap";
//        return "redirect:/home/showHome";
//    }

}
