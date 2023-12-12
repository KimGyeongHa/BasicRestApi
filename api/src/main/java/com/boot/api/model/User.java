package com.boot.api.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@JsonIgnoreProperties(value = {"password","id"})
@Schema(description = "사용자 상세 정보를 위한 도메인 객체")
public class User {
    private Integer id;

    @Size(min = 2,message = "이름은 두 글자 이상 작성부탁")
    @Schema(title = "이름",description = "사용자 이름을 입력합니다.")
    private String name;
    @Past(message = "등록일은 과거 날짜를 등록")
    @Schema(title = "가입날짜",description = "가입날짜를 입력합니다.")
    private Date joinDate;
    private String password;
}
