package com.web.hawaste.domain;

import com.web.hawaste.entity.WorkOrder;
import lombok.Data;

@Data
public class WorkOrderDo extends WorkOrder {

    private String createUserName;
    private String createOfficeName;
    private String transportUserName;
    private String recipientUserName;
}
