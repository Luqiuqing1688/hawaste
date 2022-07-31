package com.web.hawaste.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.web.hawaste.domain.WorkOrderDetailDo;
import com.web.hawaste.entity.WorkOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import com.web.hawaste.domain.WorkOrderDo;

import java.io.Serializable;
import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author gec
 * @since 2022-05-26
 */
public interface IWorkOrderService extends IService<WorkOrder> {

    IPage<WorkOrderDo> selectByCondition(IPage<WorkOrderDo> page, Map<String, Object> params);

    WorkOrderDetailDo selectDetailById(Serializable id);
}
