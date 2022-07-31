package com.web.hawaste.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.web.hawaste.entity.Examine;
import com.web.hawaste.domain.ExamineDo;
import com.web.hawaste.mapper.ExamineMapper;
import com.web.hawaste.service.IExamineService;
import org.springframework.stereotype.Service;

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
public class ExamineServiceImpl extends ServiceImpl<ExamineMapper, Examine> implements IExamineService {

    @Override
    public IPage<ExamineDo> selectByCondition(IPage<ExamineDo> page, Map<String, Object> params) {

        QueryWrapper<ExamineDo> query = new QueryWrapper<>();
        //sql拼接固定部分
        query.apply("ex.del_flag=0 " +
                " AND ex.examine_user_id =su.id " +
                " AND su.office_id=so.id ");
        //sql动态拼接
        query.eq(params.containsKey("type") && !ObjectUtils.isEmpty(params.get("type")), "ex.type", params.get("type"))
                .like(params.containsKey("name") && !ObjectUtils.isEmpty(params.get("name")), "su.name", params.get("name"))
                .eq(params.containsKey("officeId") && !ObjectUtils.isEmpty(params.get("officeId")), "so.id", params.get("officeId"));
        return baseMapper.selectByCondition(page, query);
    }
}
