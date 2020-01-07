package com.monitor.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.monitor.domain.JobStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class JobStatusService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Gson gson;

    @Autowired
    private String jsonURL;

    public List<JobStatus> generateJobStatus(){
        String jobStatusFile = restTemplate.getForObject(jsonURL, String.class);
        List<JobStatus> allJobStatus = gson.fromJson(jobStatusFile, new TypeToken<List<JobStatus>>() {}.getType());

        Collections.sort(allJobStatus, new JobStatusComparitor());
        allJobStatus = replaceNewLine(allJobStatus);
        System.out.println(allJobStatus);
        return allJobStatus;
    }

    private List<JobStatus> replaceNewLine(List<JobStatus> allJobStatus){
        return allJobStatus.stream()
                .map(job -> {
                    job.setDescription(job.getDescription().replaceAll("(\\r\\n|\\n)","<br />"));
                    return job;
                }).collect(Collectors.toList());
    }

}

