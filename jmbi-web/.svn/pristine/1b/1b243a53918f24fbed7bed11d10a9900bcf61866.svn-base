package com.jumore.jmbi.controller.index;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jumore.dove.controller.base.BaseController;
import com.jumore.dove.util.ParamMap;
import com.jumore.jmbi.common.util.Consts;
import com.jumore.jmbi.common.util.DictConsts;
import com.jumore.jmbi.dao.entity.permission.Account;
import com.jumore.jmbi.service.permission.AccountService;

@Controller
public class IndexController extends BaseController {
    public static final String Platform_Id = "platformId";
    public static final String Platform_Name = "platformName";
    public static final int Four_Int = 4;
    /**
     * 用户服务
     */
    @Autowired
    private AccountService accountService;

    /**
     * index:主页.
     *
     * @param request
     * @return
     * @throws Exception
     * @author Administrator
     * @date 2017年6月5日 上午10:02:18
     */
    @RequestMapping("/index")
    public ModelAndView index(HttpServletRequest request) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        ModelAndView mv = new ModelAndView();
        Session session = SecurityUtils.getSubject().getSession();
        Account account = (Account) session.getAttribute(Consts.Session_User_Key);
        map.put("user", account);
        if (StringUtils.isNotBlank(account.getPlatformIds())) {
            String platformId = account.getPlatformIds().split(",")[0];
            if ("100100_100700_100300_100900_101000_100800_101200_101400_101100_101300_100200_102200_100400_101500_101900_101501_101901_100401".indexOf(platformId) > -1) {
                mv.setViewName("/tradeIndex");
            }
            setSession(session, account, platformId);
        }
        mv.addAllObjects(map);
        return mv;
    }

    private void setSession(Session session, Account account, String platformId) {
        if (!("admin".equalsIgnoreCase(account.getAccount()) || "-1".equalsIgnoreCase(account.getRoles()))) {// 管理员和超级管理员不需要重新设置
            account.setFunctions(this.getFunctions(account.getRoles(), platformId));
            session.setAttribute(Consts.Session_User_Key, account);
        }
        session.setAttribute(Platform_Id, platformId);
        platformId = platformId.endsWith("00") ? StringUtils.left(platformId, Four_Int) : platformId;
        session.setAttribute(Platform_Name, DictConsts.platformMap.get(platformId));
    }

    /**
     * index2:用于平台切换.
     *
     * @param request
     * @param platformId
     * @return
     * @throws Exception
     * @author Administrator
     * @date 2017年5月15日 下午4:34:38
     */
    @RequestMapping("/index2")
    public ModelAndView index2(HttpServletRequest request, String platformId) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        Session session = SecurityUtils.getSubject().getSession();
        Account account = (Account) session.getAttribute(Consts.Session_User_Key);
        ModelAndView mv = new ModelAndView();
        String viewName = "/index";
        map.put("user", session.getAttribute(Consts.Session_User_Key));
        if (StringUtils.isNotBlank(platformId)) {
            if ("100100_100700_100300_100900_101000_100800_101200_101400_101100_101300_100200_102200_100400_101500_101900_101501_101901_100401".indexOf(platformId) > -1) {
                viewName = "/tradeIndex";
            }
            setSession(session, account, platformId);
        }

        mv.setViewName(viewName);
        mv.addAllObjects(map);
        return mv;
    }

    @RequestMapping(value = {"/", "/*-analysis", "/overview", "/*-user", "/signature-pay", "/shop-*", "/dealing*", "/*-amount",
            "/home", "/order", "/company", "/importExport", "/*-attension", "/capacity", "/warehouse", "/logistics", "/shipping", "/business"})
    public ModelAndView refresh(HttpServletRequest request) throws Exception {
        Session session = SecurityUtils.getSubject().getSession();
        return index2(request, session.getAttribute(Platform_Id).toString());
    }

    @SuppressWarnings("unchecked")
    private String getFunctions(String ids, String platformId) {
        String functions = null;
        ParamMap paramMap = new ParamMap();
        paramMap.put("ids", ids.split(","));
        paramMap.put("platformId", platformId);
        Map<String, Object> map = accountService.findOne(".RoleMapper.selectByIds", paramMap);
        if (map.get("functions") != null) {
            functions = String.valueOf(map.get("functions"));
        }
        return functions;
    }

}
