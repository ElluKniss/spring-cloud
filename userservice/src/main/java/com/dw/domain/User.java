package com.dw.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_user")
public class User {

    private String id;
    private String name;

    private int age;

    public String getId() {
        return id;
    }

}
