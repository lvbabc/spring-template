package com.rex.springtemplate.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("用户")
public class User implements Serializable {
    @ApiModelProperty("用户id")
    private Long id;

    @ApiModelProperty("用户账号")
    @NotNull(message = "用户账号不能为空")
    @Size(min = 6, max = 11, message = "账号长度必须是6-11个字符")
    private String username;

    @ApiModelProperty("用户密码")
    @NotNull(message = "用户密码不能为空")
    @Size(min = 6, max = 16, message = "密码长度必须是6-16个字符")
    private String password;

    @ApiModelProperty("用户邮箱")
    @NotNull(message = "用户邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;

    @ApiModelProperty("用户昵称")
    private String nickname;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
