package com.monitor;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobStatus {

    private String description;
    private String name;
    private String jsonFileName;
    private int id;
    private String status;
    private Links links;
}
