package com.web.hawaste.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.web.hawaste.domain.TransferDo;
import com.web.hawaste.entity.Transfer;
import org.apache.ibatis.annotations.Select;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author gec
 * @since 2022-05-26
 */
public interface TransferMapper extends BaseMapper<Transfer> {

    @Select("SELECT " +
            "   tr.*, " +
            "   su.NAME user_name, " +
            "   su.phone user_phone  " +
            "FROM " +
            "   transfer tr, " +
            "   sys_user su  " +
            "WHERE " +
            "   tr.work_order_id = #{oid}  " +
            "   AND tr.oprate_user_id = su.id  " +
            "ORDER BY " +
            "   tr.create_date DESC")
    List<TransferDo> selectByWorkOrderId(Serializable oid);
}
