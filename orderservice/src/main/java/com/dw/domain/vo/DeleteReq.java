package com.dw.domain.vo;

import com.dw.domain.Product;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class DeleteReq {

    @NotNull
    private long id;

    private List<Product> list;
}
