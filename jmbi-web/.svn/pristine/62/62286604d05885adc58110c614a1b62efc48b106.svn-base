/**
 * 
 */
package com.jumore.jmbi.controller;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jumore.dove.common.log.LogHelper;
import com.jumore.jmbi.common.requests.CommResponse;
import com.jumore.jmbi.common.util.Consts;

/**
 * 登陆控制器
 * 
 * @author: luochao
 * @since: 2016年8月1日 下午1:25:54
 * @history:
 */
@Controller
public class LoginController {
    private static final LogHelper Log_Helper = LogHelper.getLogger(LoginController.class);
    public static final int Four_Int   = 4;

    /**
     * 登陆页
     *
     * @param request
     * @return
     * @history
     */
    @RequestMapping(value = "/login")
    public ModelAndView login(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        return mv;
    }

    /**
     * 登陆
     *
     * @param username
     * @param password
     * @throws Exception
     * @history
     */
    @RequestMapping(value = "/doLogin")
    @ResponseBody
    public CommResponse doLogin(String username, String password) throws Exception {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            SecurityUtils.getSubject().login(token);
            return CommResponse.success(Four_Int);
        } catch (Exception ex) {

            return CommResponse.failure(ex.getCause().getMessage());
        }
    }

    /**
     * 登出
     *
     * @return
     * @throws Exception
     * @history
     */
    @RequestMapping(value = "/doLogout")
    public String doLogout() throws Exception {
        SecurityUtils.getSubject().logout();
        SecurityUtils.getSubject().getSession().removeAttribute(Consts.Session_User_Key);
        return "redirect:/login";
    }

    /**
     * releasenotes:(这里用一句话描述这个方法的作用).
     * 
     * @author Administrator
     * @date 2017年6月5日 下午12:58:59
     * @return
     */
    @RequestMapping(value = "/releasenotes")
    public ModelAndView releasenotes() {
        InputStream in = this.getClass().getResourceAsStream("/version.txt");
        ModelAndView mv = new ModelAndView();
        try {
            String version = IOUtils.toString(in);
            mv.addObject("svnVersion", version);
        } catch (Exception ex) {
            Log_Helper.getBuilder().error(ex.getMessage());
        }
        return mv;
    }
}
