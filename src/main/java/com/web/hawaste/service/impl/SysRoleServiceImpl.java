package com.web.hawaste.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.web.hawaste.domain.SysRoleDo;
import com.web.hawaste.entity.*;
import com.web.hawaste.mapper.SysOfficeMapper;
import com.web.hawaste.mapper.SysResourceMapper;
import com.web.hawaste.mapper.SysRoleMapper;
import com.web.hawaste.service.ISysRoleOfficeService;
import com.web.hawaste.service.ISysRoleResourceService;
import com.web.hawaste.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author gec
 * @since 2022-05-26
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Autowired
    private SysResourceMapper resourceMapper;
    @Autowired
    private SysOfficeMapper officeMapper;
    @Autowired
    private ISysRoleOfficeService roleOfficeService;
    @Autowired
    private ISysRoleResourceService roleResourceService;

    @Override
    public IPage<SysRoleDo> selectByCondition(IPage<SysRoleDo> page, Map<String, Object> params) {
        QueryWrapper<SysRoleDo> query = new QueryWrapper<>();
        query.apply("sr.del_flag =0 and sr.office_id = so.id")
                .eq(params.containsKey("dataScope") && !ObjectUtils.isEmpty(params.get("dataScope")), "sr.data_scope", params.get("dataScope"))
                .like(params.containsKey("name") && !ObjectUtils.isEmpty(params.get("name")), "sr.name", params.get("name"))
                .like(params.containsKey("remakrs") && !ObjectUtils.isEmpty(params.get("remakrs")), "sr.remakrs", params.get("remakrs"))
                .eq(params.containsKey("id") && !ObjectUtils.isEmpty(params.get("id")), "so.id", params.get("id"));
        return baseMapper.selectByCondition(page, query);
    }

    @Override
    public List<SysRole> selectRoleByUserId(Long uid) {

        return baseMapper.selectByUid(uid);
    }

    @Override
    public SysRoleDo selectOne(Long rid) {
        //查询角色信息
        SysRoleDo role = baseMapper.selectRoleAndOfficeByRid(rid);
        //查询角色授权资源信息
        role.setResources(resourceMapper.selectByRid(rid));
        //如果是按明细设置，查询角色授权公司信息
        if ("9".equals(role.getDataScope())) {
            role.setOffices(officeMapper.selectByRid(rid));
        }
        return role;
    }

    @Override
    @Transactional
    public boolean updateById(SysRole entity) {
        SysRoleDo roleDo = (SysRoleDo) entity;

        //1.更新role本表信息
        super.updateById(entity);

        //2.更新role对应的resources
        List<SysResource> resources = roleDo.getResources();
        if (!ObjectUtils.isEmpty(resources)) {
            updateRoleResources(roleDo.getId(), resources);
        }
        //3.更新role对应的offices
        List<SysOffice> offices = roleDo.getOffices();
        if (!ObjectUtils.isEmpty(offices)) {//更新
            updateRoleOffices(roleDo.getId(), offices);
        } else {//取消跨机构授权
            roleOfficeService.remove(new QueryWrapper<SysRoleOffice>().eq("role_id", roleDo.getId()));
        }
        return true;
    }

    /*
     * 更新中间表sys_role_resource数据
     * */
    private void updateRoleResources(long rid, List<SysResource> resources) {
        List<SysRoleResource> roleResources = new ArrayList<>();
        resources.forEach(resource -> {
            SysRoleResource roleResource = new SysRoleResource();
            roleResource.setRoleId(rid);
            roleResource.setResourceId(resource.getId());
            roleResources.add(roleResource);
        });
        //删除旧数据
        roleResourceService.remove(new QueryWrapper<SysRoleResource>().eq("role_id", rid));
        //插入新数据
        roleResourceService.saveBatch(roleResources);
    }

    /*
     * 更新中间表sys_role_office数据
     * */
    private void updateRoleOffices(Long rid, List<SysOffice> offices) {
        List<SysRoleOffice> roleOffices = new ArrayList<>();
        offices.forEach(office -> {
            SysRoleOffice roleOffice = new SysRoleOffice();
            roleOffice.setRoleId(rid);
            roleOffice.setOfficeId(office.getId());
            roleOffices.add(roleOffice);
        });
        //删除旧数据
        roleOfficeService.remove(new QueryWrapper<SysRoleOffice>().eq("role_id", rid));
        //插入新数据
        roleOfficeService.saveBatch(roleOffices);
    }
}
