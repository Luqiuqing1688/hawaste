package com.web.hawaste.service.impl;

import com.web.hawaste.entity.AppVersion;
import com.web.hawaste.mapper.AppVersionMapper;
import com.web.hawaste.service.IAppVersionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class AppVersionServiceImpl extends ServiceImpl<AppVersionMapper, AppVersion> implements IAppVersionService {

}
