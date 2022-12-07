package com.dw.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_unibox_device_info")
public class UniboxDevice {

    private int id;

    private String cuei;

    private String secret;

    private String createTime;
}
