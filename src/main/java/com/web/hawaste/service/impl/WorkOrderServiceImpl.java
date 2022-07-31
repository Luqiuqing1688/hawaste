package com.web.hawaste.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.web.hawaste.domain.DetailDo;
import com.web.hawaste.domain.TransferDo;
import com.web.hawaste.domain.WorkOrderDetailDo;
import com.web.hawaste.entity.WorkOrder;
import com.web.hawaste.domain.WorkOrderDo;
import com.web.hawaste.mapper.DetailMapper;
import com.web.hawaste.mapper.TransferMapper;
import com.web.hawaste.mapper.WorkOrderMapper;
import com.web.hawaste.service.IWorkOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author gec
 * @since 2022-05-26
 */
@Service
public class WorkOrderServiceImpl extends ServiceImpl<WorkOrderMapper, WorkOrder> implements IWorkOrderService {

    @Autowired
    private DetailMapper detailMapper;
    @Autowired
    private TransferMapper transferMapper;

    @Override
    public IPage<WorkOrderDo> selectByCondition(IPage<WorkOrderDo> page, Map<String, Object> params) {

        QueryWrapper<WorkOrderDo> wrapper = new QueryWrapper<>();
        wrapper.eq("wo.del_flag", "0")
                .eq(params.containsKey("status") && !ObjectUtils.isEmpty(params.get("status")),
                        "wo.`status`", params.get("status"))
                .ge(params.containsKey("startDate") && !ObjectUtils.isEmpty(params.get("startDate")),
                        "wo.create_date", params.get("startDate"))
                .le(params.containsKey("endDate") && !ObjectUtils.isEmpty(params.get("endDate")),
                        "wo.create_date", params.get("endDate"))
                .and(params.containsKey("officeId") && !ObjectUtils.isEmpty(params.get("officeId")),
                        qw -> qw.eq("co.id", params.get("officeId"))
                                .or()
                                .eq("`to`.id", params.get("officeId"))
                                .or()
                                .eq("ro.id", params.get("officeId")));
        return baseMapper.selectByCondition(page, wrapper);
    }

    @Override
    public WorkOrderDetailDo selectDetailById(Serializable id) {

        //查出工单基本信息
        WorkOrderDetailDo workOrderDetailDo = baseMapper.selectDetailById(id);

        //查出详单信息
        List<DetailDo> detailDos = detailMapper.selectByOrderId(id);
        workOrderDetailDo.setDetails(detailDos);

        //查出转运记录
        List<TransferDo> transferDos = transferMapper.selectByWorkOrderId(id);
        workOrderDetailDo.setTransfers(transferDos);

        return workOrderDetailDo;
    }
}
