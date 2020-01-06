package com.monitor.domain;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class JobStatus {
    private String description;
    private String name;
    private String jsonFileName;
    private int id;
    private Status status;
    private List<Links> links;


}
