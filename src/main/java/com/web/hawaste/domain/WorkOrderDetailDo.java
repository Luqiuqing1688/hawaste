package com.web.hawaste.domain;

import com.web.hawaste.entity.WorkOrder;
import lombok.Data;

import java.util.List;

@Data
public class WorkOrderDetailDo extends WorkOrder {

    private List<DetailDo> details;  //工单的详单信息列表
    private List<TransferDo> transfers; //工单的转运记录列表
    private String createUserName;     //产废用户名
    private String createOfficeName;    //产废用户所在公司
    private String createUserPhone;     //产废用户电话
    private String transportUserName;   //运输用户信息
    private String transportOfficeName;
    private String transportUserPhone;
    private String recipientUserName;   //处置用户信息
    private String recipientOfficeName;
    private String recipientUserPhone;
}
