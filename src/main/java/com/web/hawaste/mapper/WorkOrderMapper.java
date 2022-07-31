package com.web.hawaste.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.web.hawaste.domain.WorkOrderDetailDo;
import com.web.hawaste.entity.WorkOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.web.hawaste.domain.WorkOrderDo;
import org.apache.ibatis.annotations.Select;

import java.io.Serializable;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author gec
 * @since 2022-05-26
 */
public interface WorkOrderMapper extends BaseMapper<WorkOrder> {

    @Select("SELECT " +
            "wo.*, " +
            "    cu.name create_user_name, " +
            "    co.name create_office_name, " +
            "    tu.name transport_user_name, " +
            "    ru.name recipient_user_name " +
            "    FROM " +
            "    work_order wo " +
            "    LEFT JOIN sys_user cu " +
            "    ON wo.create_user_id = cu.id " +
            "    LEFT JOIN sys_office co " +
            "    ON cu.office_id = co.id " +
            "    LEFT JOIN sys_user tu " +
            "    ON wo.transport_user_id = tu.id " +
            "    LEFT JOIN sys_office `to` " +
            "    ON tu.office_id = `to`.id " +
            "    LEFT JOIN sys_user ru " +
            "    ON wo.recipient_user_id = ru.id " +
            "    LEFT JOIN sys_office ro " +
            "    ON ru.office_id = ro.id " +
            "    ${ew.customSqlSegment}")
    IPage<WorkOrderDo> selectByCondition(IPage<WorkOrderDo> page, Wrapper ew);

    @Select("SELECT  " +
            " wo.*,  " +
            " cu.NAME create_user_name,  " +
            " co.NAME create_office_name,  " +
            " tu.NAME transport_user_name,  " +
            " ru.NAME recipient_user_name  " +
            "FROM  " +
            " work_order wo  " +
            " LEFT JOIN sys_user cu  " +
            " ON wo.create_user_id = cu.id  " +
            " LEFT JOIN sys_office co  " +
            " ON cu.office_id = co.id  " +
            " LEFT JOIN sys_user tu  " +
            " ON wo.transport_user_id = tu.id  " +
            " LEFT JOIN sys_office `to`  " +
            " ON tu.office_id = `to`.id  " +
            " LEFT JOIN sys_user ru  " +
            " ON wo.recipient_user_id = ru.id  " +
            " LEFT JOIN sys_office ro  " +
            " ON ru.office_id = ro.id   " +
            "WHERE  " +
            " wo.del_flag = 0   " +
            " AND wo.id = #{oid} ")
    WorkOrderDetailDo selectDetailById(Serializable oid);
}
