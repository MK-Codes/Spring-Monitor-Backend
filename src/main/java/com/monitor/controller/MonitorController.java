package com.monitor.controller;

import com.monitor.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/monitor")
public class MonitorController {

    @Autowired
    private StatusService statusService;

    @GetMapping("/test")
    public String monitor(Model model){
        model.addAttribute("allStatus", statusService.generateStatus());
        return "monitor";
    }
}
