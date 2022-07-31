package com.web.hawaste.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.web.hawaste.domain.ExamineDo;
import com.web.hawaste.entity.Examine;
import org.apache.ibatis.annotations.Select;


public interface ExamineMapper extends BaseMapper<Examine> {
    // ${ew.customSqlSegment}表示 动态条件的拼接   customSqlSegment固定名
    @Select(" select " +
            " ex.*," +
            " su.name user_name, " +
            " so.name office_name " +
            " from " +
            " examine ex, " +
            " sys_user su, " +
            " sys_office so ${ew.customSqlSegment}")
    IPage<ExamineDo> selectByCondition(IPage<ExamineDo> page, Wrapper ew);
}
