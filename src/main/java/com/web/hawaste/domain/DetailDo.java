package com.web.hawaste.domain;

import com.web.hawaste.entity.Detail;
import lombok.Data;

@Data
public class DetailDo extends Detail {

    private String wasteTypeCode;
    private String wasteTypeName;
    private String wasteCode;
}
