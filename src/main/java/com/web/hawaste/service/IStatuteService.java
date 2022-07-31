package com.web.hawaste.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.web.hawaste.entity.Statute;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author gec
 * @since 2022-05-26
 */
public interface IStatuteService extends IService<Statute> {

    IPage<Statute> selectByConditions(IPage<Statute> page, Integer type);
}
