package com.monitor.controller;

import com.monitor.domain.JobStatus;
import com.monitor.service.JobStatusService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/monitor")
public class MonitorController {

    @Autowired
    private JobStatusService statusService;

    @Autowired
    private String jsonURL;

    @GetMapping
    public String monitor(Model model){
        List<JobStatus> allJobs = statusService.generateJobStatus();
        model.addAttribute("allJobs", allJobs);
        return "monitor";
    }

    @GetMapping("/static")
    public String monitorStatic(Model model) {
        //List<JobStatus> allJobs = statusService.generateJobStatus();
        //model.addAttribute("allJobs", allJobs);
        model.addAttribute("url", jsonURL);
        return "monitor_static";
    }

    @GetMapping("/test")
    public String monitorFilter(){
        return "filter";
    }
}
