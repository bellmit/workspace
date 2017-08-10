package com.jumore.jmbi.controller.common;

import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jumore.dove.controller.base.BaseController;
import com.jumore.dove.soa.SOAService;
import com.jumore.jmbi.model.vo.common.PageRespModel;
import com.jumore.ucenter.dubbo.service.api.UcenterConstant;
import com.jumore.ucenter.dubbo.service.api.base.dto.Page;
import com.jumore.ucenter.dubbo.service.api.base.dto.ResponseDTO;
import com.jumore.ucenter.dubbo.service.api.company.dto.CompanyDTO;
import com.jumore.ucenter.dubbo.service.api.user.UserService;
import com.jumore.ucenter.dubbo.service.api.user.dto.UserDTO;

@Controller()
@RequestMapping("/company")
public class CompanyController extends BaseController {

	@Resource
	private SOAService soaService;

	@RequestMapping(value = "/index")
	public ModelAndView index(HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		return mv;
	}

	/**
	 * load table data
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public PageRespModel list(UserDTO paramDTO, Integer startIndex,
			Integer pageSize) {
		// 判断用户是否存在
		paramDTO.setPlatform("1016");
		paramDTO.setQueryCompanyName(StringUtils.stripToNull(paramDTO
				.getQueryCompanyName()));
		paramDTO.setMobileNo(StringUtils.stripToNull(paramDTO.getMobileNo()));
		paramDTO.setQueryRegisterTimeStart(StringUtils.stripToNull(paramDTO
				.getQueryRegisterTimeStart()));
		paramDTO.setQueryRegisterTimeEnd(StringUtils.stripToNull(paramDTO
				.getQueryRegisterTimeEnd()));

		ResponseDTO<Page<UserDTO>> response = soaService.getRemoteService(
				UserService.class, UcenterConstant.APIVERSION)
				.pageQueryUserAndCompany(paramDTO, startIndex, pageSize);
		Page<UserDTO> result = response.getResult();
		PageRespModel model = new PageRespModel();
		if (result != null) {
			model = new PageRespModel(0, "操作成功", response.getResult().result);
			model.setTotal(response.getResult().getTotalResult());
		} else {
			model.setRows(new ArrayList<CompanyDTO>());
		}
		return model;
	}
}
