package com.monitor.service;

import com.monitor.domain.JobStatus;

import java.util.Comparator;

public class JobStatusComparitor implements Comparator<JobStatus> {


    @Override
    public int compare(JobStatus jobStatus, JobStatus t1) {
        return (jobStatus.getStatus().ordinal() - t1.getStatus().ordinal());
    }
}
