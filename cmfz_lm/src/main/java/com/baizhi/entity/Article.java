package com.baizhi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article implements Serializable {
    private String id;
    private String title;
    private String guruId;
    private String content;
    @JsonFormat(pattern = "YYYY-MM-dd",timezone = "GTM+8")
    @DateTimeFormat(pattern = "YYYY-MM-dd")
    private Date publishTime;
    private Guru guru;
}
