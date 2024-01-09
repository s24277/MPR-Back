package com.pjatk.MPR.controller;

import com.pjatk.MPR.AlreadyExists;
import com.pjatk.MPR.model.Cow;
import com.pjatk.MPR.service.MyCowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CowController {
    private final MyCowService restService;

    @Autowired
    public CowController(MyCowService restService) {
        this.restService = restService;
    }

    @GetMapping("/cow/{name}")
    public Cow getCowByName(@PathVariable("name") String name) {
        return this.restService.getCowByName(name);
    }

    @GetMapping("/greeting")
    public String greeting() {
        return "works";
    }

    @GetMapping("/showCow")
    public List<Cow> showCow() {
        return this.restService.getAllCows();
    }
    @GetMapping(value = "/cowNew")
    public String getAddView(Model model){
        model.addAttribute("cow",new Cow(1L,"", 0));
        return "cowNew";
    }

   // @PostMapping(value = "/cowNew")
    //public String cowNew(){
    //}

    @PostMapping("/cowNew")
    public void cowNew(@RequestBody Cow cow) throws AlreadyExists {
        this.restService.addCow(cow);
    }

    @PutMapping("/editCow/{id}")
    public void cowEdit(){

    }

    @DeleteMapping("/cowDel/{name}")
    public void cowDel(@PathVariable("name") String name) {
        this.restService.deleteCowByName(name);
    }

    @GetMapping("/welcome")
    public String getViewAll(Model model){
        model.addAttribute("cows",restService.getAllCows());
        return "welcome";
    }
}