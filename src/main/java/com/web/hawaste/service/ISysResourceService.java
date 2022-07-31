package com.web.hawaste.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.web.hawaste.entity.SysResource;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author gec
 * @since 2022-05-26
 */
public interface ISysResourceService extends IService<SysResource> {

    List<SysResource> selectResourceByUserId(Long uid);


}
