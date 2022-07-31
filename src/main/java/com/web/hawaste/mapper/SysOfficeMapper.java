package com.web.hawaste.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.web.hawaste.entity.SysOffice;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 机构表 Mapper 接口
 * </p>
 *
 * @author gec
 * @since 2022-05-26
 */
public interface SysOfficeMapper extends BaseMapper<SysOffice> {

    @Select(" select  " +
            "  sof.id, " +
            "  sof.parent_id, " +
            "  sof.name, " +
            "  sof.icon " +
            "  from " +
            "  sys_role sr, " +
            "  sys_role_office sro, " +
            "  sys_office sof " +
            " where " +
            "  sro.role_id = #{rid} " +
            " and sr.del_flag = 0 " +
            " and sro.del_flag = 0 " +
            " and sof.del_flag  = 0 " +
            " and sro.office_id =sof.id")
    List<SysOffice> selectByRid(Long rid);
}
