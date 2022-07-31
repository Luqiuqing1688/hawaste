package com.web.hawaste.domain;

import com.web.hawaste.entity.Transfer;
import lombok.Data;

@Data
public class TransferDo extends Transfer {

    private String userName;
    private String userPhone;

}
