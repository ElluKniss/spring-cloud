package com.dw.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@TableName("t_user")
public class User {

    private String id;

    @TableField("user_name")
    private String name;

    private String phone;

    public String getId() {
        return id;
    }

}
