package com.boot.api.model;


import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class User {
    private Integer id;
    @Size(min = 2,message = "이름은 두 글자 이상 작성부탁")
    private String name;
    @Past(message = "등록일은 과거 날짜를 등록")
    private Date joinDate;
}
