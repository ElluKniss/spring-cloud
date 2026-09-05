package com.dw.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@TableName("t_user")
public class User {

    @TableId(value = "user_id", type = IdType.AUTO)
    private int userId;

    @TableField("user_name")
    private String name;

    @TableField("nick_name")
    private String nickName;

    /**
     * 用户密码
     */
    @NotBlank(message = "{user.password.not.blank}")
    @Length(min = 6, max = 15, message = "{user.password.length.valid}")
    private String password;

    @TableField("phonenumber")
    private String phone;

}
