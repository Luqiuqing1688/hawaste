package com.web.hawaste.domain;

import com.web.hawaste.entity.Examine;
import lombok.Data;

/**
 * Examine的扩展类
 */
@Data
public class ExamineDo extends Examine {

    private String userName;
    private String officeName;
}
