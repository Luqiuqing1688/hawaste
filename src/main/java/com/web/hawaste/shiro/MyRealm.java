package com.web.hawaste.shiro;

import com.web.hawaste.entity.SysUser;
import com.web.hawaste.service.ISysResourceService;
import com.web.hawaste.service.ISysRoleService;
import com.web.hawaste.service.ISysUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class MyRealm extends AuthorizingRealm {

    @Autowired
    private ISysUserService userService;
    @Autowired
    private ISysRoleService roleService;
    @Autowired
    private ISysResourceService resourceService;

    //用户认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String username = (String) token.getPrincipal();
        System.out.println("验证登录名：" + username);
        SysUser user = userService.findUserByUsername(username);

        if (user == null) {
            return null;
        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
                user,
                user.getPassword(),
                //用户名作为“盐”
                ByteSource.Util.bytes(user.getUsername()),
                "MyRealm"
        );
        return info;
    }

    //权限管理
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        SysUser user = (SysUser) principal.getPrimaryPrincipal();


        //角色列表
        ArrayList<String> roleList = new ArrayList<>();
        //权限列表
        ArrayList<String> resourceList = new ArrayList<>();
        //根据用户Id查询角色
        roleService.selectRoleByUserId(user.getId()).forEach(role -> {
            if (!roleList.contains(role.getName())) {
                roleList.add(role.getName());
            }
        });
        //根据用户Id查询权限
        resourceService.selectResourceByUserId(user.getId()).forEach(resource -> {
            if (!resourceList.contains(resource.getName())) {
                resourceList.add(resource.getPermissionStr());
            }
        });
        System.out.println("角色列表：" + roleList);
        System.out.println("权限列表：" + resourceList);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRoles(roleList);
        info.addStringPermissions(resourceList);

        return info;
    }


}
