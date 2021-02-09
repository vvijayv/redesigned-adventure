package com.fourkites.r2dbc;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestQuery {

    private String value;
    private LocalDateTime from;
    private LocalDateTime to;
    private long id;

}
