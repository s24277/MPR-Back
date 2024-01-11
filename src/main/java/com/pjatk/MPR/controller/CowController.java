package com.pjatk.MPR.controller;

import com.pjatk.MPR.AlreadyExists;
import com.pjatk.MPR.model.Cow;
import com.pjatk.MPR.service.MyCowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CowController {
    private final MyCowService restService;

    @Autowired
    public CowController(MyCowService restService) {
        this.restService = restService;
    }

    @GetMapping("/cow/{id}")
    public Optional<Cow> getCowById(@PathVariable("id") Long id) {
        return this.restService.getCowById(id);
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

    @PostMapping("/cowNew")
    public void cowNew(@RequestBody Cow cow) throws AlreadyExists {
        this.restService.addCow(cow);
    }

    @PutMapping("/editCow/{id}")
    public void cowEdit(@PathVariable("id") Long id, @RequestBody Cow updatedCow){
        Optional<Cow> existingCow = this.restService.getCowById(id);
            Cow cowToUpdate = existingCow.get();
            cowToUpdate.setName(updatedCow.getName());
            cowToUpdate.setAge(updatedCow.getAge());

            this.restService.updateCow(cowToUpdate);
    }

    @DeleteMapping("/cowDel/{id}")
    public void cowDel(@PathVariable("id") String name) {
        this.restService.deleteCowByName(name);
    }

    @GetMapping("/welcome")
    public String getViewAll(Model model){
        model.addAttribute("cows",restService.getAllCows());
        return "welcome";
    }
}