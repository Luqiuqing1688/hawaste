package com.web.hawaste.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.web.hawaste.domain.DetailDo;
import com.web.hawaste.entity.Detail;
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
public interface DetailMapper extends BaseMapper<Detail> {

    @Select("SELECT " +
            "  de.*, " +
            "  wt.CODE waste_type_code, " +
            "  wt.NAME waste_type_name, " +
            "  wa.CODE waste_code  " +
            "FROM " +
            "  detail de, " +
            "  waste_type wt, " +
            "  waste wa  " +
            "WHERE " +
            "  de.work_order_id = #{oid} " +
            "  AND de.waste_type_id = wt.id  " +
            "  AND de.waste_id = wa.id")
    List<DetailDo> selectByOrderId(Serializable oid);
}
