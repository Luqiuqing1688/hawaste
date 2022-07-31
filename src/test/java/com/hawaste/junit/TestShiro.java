package com.hawaste.junit;

import com.web.hawaste.entity.SysUser;
import com.web.hawaste.service.ISysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TestShiro {

    @Autowired
    private ISysUserService userService;

    @Test
    public void test1() {

        List<SysUser> list = userService.list();
        String password = "123";
        list.forEach(user -> {
            Md5Hash md5 = new Md5Hash(password, user.getUsername(), 3);
            System.out.println(md5);
            user.setPassword(md5.toString());

            userService.saveOrUpdate(user);
        });
    }

    @Autowired
    DefaultWebSecurityManager securityManager;

    @Test
    public void test2() {

        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("13648333852", "123");

        subject.login(token);
        System.out.println("是否是合法用户：" + subject.isAuthenticated());
        System.out.println("是否具有权限user:select：" + subject.isPermitted("user:select"));

        subject.logout();
        System.out.println("是否是合法用户：" + subject.isAuthenticated());

    }
}
