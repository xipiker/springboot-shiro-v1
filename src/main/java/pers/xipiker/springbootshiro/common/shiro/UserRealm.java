package pers.xipiker.springbootshiro.common.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import pers.xipiker.springbootshiro.domain.springShiroUser;
import pers.xipiker.springbootshiro.service.springShiroUserService;

public class UserRealm extends AuthorizingRealm {
    @Autowired
    private springShiroUserService springShiroUserService;
    private Logger log = LoggerFactory.getLogger(this.getClass());
    /**
     * 执行授权逻辑
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("执行授权逻辑");
        //给资源进行授权
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //添加授权字符串
        //info.addStringPermission("user:add");
        Subject subject = SecurityUtils.getSubject();
        springShiroUser user = (springShiroUser) subject.getPrincipal();
        springShiroUser user2 = springShiroUserService.findById(user.getId());

        info.addStringPermission(user2.getPerms());

        return info;
    }

    /**
     * 执行认证逻辑
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.printf("执行认证逻辑");
        //假设数据库的用户名和密码
//        String username = "xipiker";
//        String password = "123456";

        //编写shiro判断逻辑，判断用户名密码
        //1.判断用户名
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        springShiroUser springShiroUser = springShiroUserService.findByName(usernamePasswordToken.getUsername());
        if(springShiroUser == null){
            //用户名不存在
            return null;
        }
        //2.判断密码
        return new SimpleAuthenticationInfo(springShiroUser, springShiroUser.getPassword(), "");
    }
}
