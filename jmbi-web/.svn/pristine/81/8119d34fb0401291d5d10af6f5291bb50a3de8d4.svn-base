package com.jumore.jmbi.controller.jdt.dict;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jumore.dove.controller.base.BaseController;
import com.jumore.jmbi.common.requests.CommResponse;
import com.jumore.jmbi.common.util.DictConsts;
import com.jumore.jmbi.entites.CustomsBean;

@Controller
@RequestMapping("/v1/dict")
public class DictController extends BaseController {
    
    public static final int Four_Int   = 4;
    public static final int Three_Int   = 3;

    /**
     * 获取关区列表：
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/firstLevelCustoms", method = RequestMethod.GET)
    @ResponseBody
    public CommResponse firstLevelCustoms() throws Exception {

        List<CustomsBean> topList = new ArrayList<CustomsBean>();
        for (Entry<String, String> entry : DictConsts.customsMap.entrySet()) {
            if (entry.getKey().length() < Four_Int) {// 海关总署编码是all;其他关区都是2位编码：所以这里编码小于4位的都是关区
                CustomsBean bean = new CustomsBean(entry.getKey(), entry.getValue());
                topList.add(bean);
            }
        }
        return CommResponse.success(topList);
    }

    /**
     * 获取某一关区下的：海关列表：
     * 
     * @param code 关区编码
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/secondLevelCustoms/code/{code}", method = RequestMethod.GET)
    @ResponseBody
    public CommResponse secondLevelCustoms(@PathVariable String code) throws Exception {
        List<CustomsBean> topList = new ArrayList<CustomsBean>();
        for (Entry<String, String> entry : DictConsts.customsMap.entrySet()) {
            if (entry.getKey().length() > Three_Int && entry.getKey().startsWith(code)) {// 海关总署编码是all;其他关区都是2位编码：所以这里编码大于3位的都是海关
                CustomsBean bean = new CustomsBean(entry.getKey(), entry.getValue());
                topList.add(bean);
            }
        }
        return CommResponse.success(topList);
    }

}
