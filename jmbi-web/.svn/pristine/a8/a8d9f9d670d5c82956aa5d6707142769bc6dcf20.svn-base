package com.jumore.jmbi.service.impl.permission;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.jumore.dove.util.ParamMap;
import com.jumore.jmbi.common.util.Consts;
import com.jumore.jmbi.dao.entity.permission.Account;
import com.jumore.jmbi.service.permission.IAuthorizationService;

public class LoRealm extends AuthorizingRealm {

    @Resource
    private IAuthorizationService authorizationService;

    @SuppressWarnings({"rawtypes"})
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal(); // 得到用户名
        String password = new String((char[]) token.getCredentials()); // 得到密码
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            throw new RuntimeException("用户名或密码不能为空");
        }
        Account vo = new Account();
        vo.setAccount(username);
        vo.setPassword(password);
        vo.setIsDel(0);// 未删除
        // vo.setStatus(0);//启用：状态
        Account po = (Account) authorizationService.getByExample(vo);

        if (po == null) {
            throw new RuntimeException("用户名或密码不正确");
        }
        if (po.getStatus() == 1) {
            throw new RuntimeException("该用户已被禁用");
        }

        // 查询功能项
        if (StringUtils.isNotBlank(po.getRoles())) {
            ParamMap paramMap = new ParamMap();
            paramMap.put("ids", po.getRoles().split(","));

            Map map = authorizationService.findOne(".RoleMapper.selectByIds", paramMap);
            if (map.get("functions") != null) {
                po.setFunctions(String.valueOf(map.get("functions")));
            }

            if (map.get("platform_id") != null) {
                po.setPlatformIds(String.valueOf(map.get("platform_id")));
            }

        }
        if ("admin".equalsIgnoreCase(username) || "-1".equalsIgnoreCase(po.getRoles())) {// 管理员或者超级管理员
            po.setFunctions("8101,8102,8103,8104,8104,8105,8106,8107,8108,8109,8110,8111,8112,8113,8114,8115,8116,"
                    + "81701,81702,81703,81704,81705,81706,81707,"
                    + "8001,8002,8003,8004,8004,8005,8006,8007,8008,8009,8010,8011,8012,8013,8104,8015,8016,8017,"
                    + "81201,81202,81203,81204,81205,81206,81207,81208,81209,81210,81211,81212,81213,81214,81215,81216,81217,"
                    + "81301,81302,81303,81304,81305,81306," + "81601,81602,81603,81604,81605,81606,"
                    + "91301,91302,91303,91304,91305,91306," + "91601,91602,91603,91604,91605,91606,"
                    + "91201,91202,91203,91204,91205,91206,91207,91208,91209,91210,91211,91212,91213,91214,91215,91216,91217,");
            po.setPlatformIds("100300,100200,100700,100800,100900,101000,101100,101200,101300,101400,102000,102200,100100,100400,101500,101900,101501,101901,100401");
        }

        // 用户信息放入session
        SecurityUtils.getSubject().getSession().setAttribute(Consts.Session_User_Key, po);
        return new SimpleAuthenticationInfo(username, password, getName());

    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermission("*");
        info.addRole("default");
        return info;
    }
}