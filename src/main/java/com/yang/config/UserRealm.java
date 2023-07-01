package com.yang.config;

import com.yang.mapper.UserMapper;
import com.yang.pojo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {
    @Autowired
    UserMapper userMapper;
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //获取当前登录对象
        Subject subject = SecurityUtils.getSubject();
        //将当前用户强制转换成user
        User user = (User) subject.getPrincipal();
        //获取当前用户的权限（数据库中查询）
        info.addStringPermission(user.getPerm());
        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //将token转化为UsernamePasswordToken
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        //通过当前登录账号查询用户是否存在
        User user = userMapper.queryUserById(userToken.getUsername());
        //不存在则返回null
        if (user==null){
            return null;
        }
        //shiro帮我们处理密码
        return new  SimpleAuthenticationInfo(user,user.getPwd(),"");
    }
}
