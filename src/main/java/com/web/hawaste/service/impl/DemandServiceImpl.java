package com.web.hawaste.service.impl;

import com.web.hawaste.entity.Demand;
import com.web.hawaste.mapper.DemandMapper;
import com.web.hawaste.service.IDemandService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 客户的需求填写 服务实现类
 * </p>
 *
 * @author gec
 * @since 2022-05-26
 */
@Service
public class DemandServiceImpl extends ServiceImpl<DemandMapper, Demand> implements IDemandService {

}
