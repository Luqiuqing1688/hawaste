package com.web.hawaste.service.impl;

import com.web.hawaste.entity.SysResource;
import com.web.hawaste.mapper.SysResourceMapper;
import com.web.hawaste.service.ISysResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author gec
 * @since 2022-05-26
 */
@Service
public class SysResourceServiceImpl extends ServiceImpl<SysResourceMapper, SysResource> implements ISysResourceService {

    @Override
    public List<SysResource> selectResourceByUserId(Long uid) {

        return baseMapper.selectByUid(uid);
    }
}
