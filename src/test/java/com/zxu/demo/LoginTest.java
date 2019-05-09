package com.zxu.demo;

import junit.framework.Assert;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

public class LoginTest {
    @Test
    public void testHelloWorld() {
        // 获取SecurityManager工厂（此书使用ini配置文件初始化SecurityManager)
        Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        // 获取Security实例 并绑定给SecurityUtils ，全局设置，设置一次即可
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        // 获取Subject，会自动绑定到当前线程，如果是web环境，请求结束后要解除绑定；
        Subject subject = SecurityUtils.getSubject();
        // 获取身份验证Token
        UsernamePasswordToken userToken = new UsernamePasswordToken("zhang", "123");
        // 登陆验证
        try {
            subject.login(userToken);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        // 断言用户已经登陆
        Assert.assertEquals(true, subject.isAuthenticated());
        // 退出
        subject.logout();
    }
    @Test
    public void testCustomRealm(){
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-realm.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang1", "123");
        try{
            subject.login(token);
        }catch (AuthenticationException e){
            e.printStackTrace();
        }
        Assert.assertEquals(true, subject.isAuthenticated());
        subject.logout();
    }
    @Test
    public void testMultiCustomRealm(){
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro-realm.ini");
        SecurityManager manager = factory.getInstance();
        SecurityUtils.setSecurityManager(manager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("wang", "1234");
        try {
            subject.login(token);
        }catch (AuthenticationException e){
            e.printStackTrace();
        }
        Assert.assertEquals(true, subject.isAuthenticated());
    }


}
