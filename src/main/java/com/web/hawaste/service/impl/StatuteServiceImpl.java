package com.web.hawaste.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.web.hawaste.entity.Statute;
import com.web.hawaste.mapper.StatuteMapper;
import com.web.hawaste.service.IStatuteService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author gec
 * @since 2022-05-26
 */
@Service
public class StatuteServiceImpl extends ServiceImpl<StatuteMapper, Statute> implements IStatuteService {

    @Override
    public IPage<Statute> selectByConditions(IPage<Statute> page, Integer type) {

        QueryWrapper<Statute> query = new QueryWrapper<>();
        query.select("statute.id," +
                "statute.type," +
                "statute.title," +
                "statute.pub_date," +
                "statute.stem_from," +
                "statute.del_flag");
        query.eq(!ObjectUtils.isEmpty(type), "statute.type", type);
        return baseMapper.selectPage(page, query);
    }
}
