package com.rex.springtemplate.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * role
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("角色")
public class Role {
    /**
     * 主键id
     */
    @ApiModelProperty("角色id")
    private Long id;

    /**
     * 角色名
     */
    @ApiModelProperty("角色名")
    @NotNull(message = "角色名不能为空")
    @Size(min = 1, max = 10, message = "角色名长度必须是1-10个字符")
    private String roleName;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}