package com.monitor.domain;

import lombok.*;

@Data
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Status {
    private String name;
    private String colour;
}
