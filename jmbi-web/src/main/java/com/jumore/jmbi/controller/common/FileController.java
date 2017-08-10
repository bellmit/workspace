package com.jumore.jmbi.controller.common;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jumore.dove.controller.base.BaseController;

@Controller()
@RequestMapping("/file")
public class FileController extends BaseController {

    @RequestMapping(value = "/uploader")
    public ModelAndView index(HttpServletRequest request) throws Exception {
        ModelAndView mv = new ModelAndView();
        return mv;
    }

}
