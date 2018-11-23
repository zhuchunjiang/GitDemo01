package com.hwua.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 测试案例
 * 
 * @ClassName: DemoCotroller
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: maple
 * @date: 2018年11月21日 下午3:43:52
 * 
 * @Copyright: 2018 www.hwua.com Inc. All rights reserved.
 *             注意：本内容仅限于海文科信息技术股份有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
@Controller
@RequestMapping("/demo")
public class DemoCotroller {

	// /demo/demo01.action
	@RequestMapping(value="/{forName}",method=RequestMethod.GET)
	public String dynaicPage(@PathVariable String forName) {
		return forName;
	}
	@RequestMapping(value="/uid/{usersId}/uname/{uname}")
	public String demo01(@PathVariable("usersId") Integer uid,@PathVariable("uname") String name,Model model) {
		model.addAttribute("uid", uid);
		model.addAttribute("uname", name);
		return "demo01";
	}
}
