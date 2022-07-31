package com.web.hawaste.controller;

import com.web.hawaste.entity.ResponseStatus;
import com.web.hawaste.entity.ResultBean;
import com.web.hawaste.entity.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {

    @PostMapping("/doLogin")
    @ResponseBody
    public ResultBean doLogin(@RequestBody Map<String, Object> params, HttpSession session, HttpServletRequest request) {


        String username = (String) params.get("username");
        String password = (String) params.get("password");
        System.out.println("登录名：" + username);
        System.out.println("密码：" + password);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);

        if (subject.isAuthenticated()) {
            System.out.println("登录成功！");
            SysUser user = (SysUser) subject.getPrincipal();
            session.setAttribute("loginUser", user);

            HashMap<String, Object> map = new HashMap<>();
            map.put("loginUser", user);
            return ResultBean.ok(map);
        }
        return ResultBean.fail(ResponseStatus.USERNAME_PASS_ERROR);
    }

    @GetMapping("/logout")
    public String logout() {
        SecurityUtils.getSubject().logout();
        System.out.println("注销用户成功！");
        return "redirect:/login.html";
    }
}
