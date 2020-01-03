package com.monitor.service;

import com.monitor.domain.Status;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class StatusService {

    public List<Status> generateStatus(){
        return Arrays.asList(new Status("Test1", "red"), new Status("Test2", "green"), new Status("Test1", "blue"));
    }

}

