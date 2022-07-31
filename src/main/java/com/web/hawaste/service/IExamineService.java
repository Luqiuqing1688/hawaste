package com.web.hawaste.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.web.hawaste.entity.Examine;
import com.baomidou.mybatisplus.extension.service.IService;
import com.web.hawaste.domain.ExamineDo;

import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author gec
 * @since 2022-05-26
 */
public interface IExamineService extends IService<Examine> {

    IPage<ExamineDo> selectByCondition(IPage<ExamineDo> page, Map<String, Object> params);
}
