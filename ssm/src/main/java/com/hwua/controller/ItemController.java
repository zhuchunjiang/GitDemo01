package com.hwua.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.hwua.biz.ItemsBiz;
import com.hwua.entity.Items;
import com.hwua.entity.QueryVo;
import com.hwua.exception.CustomException;

/**
 * 
 * @ClassName: ItemController
 * @Description:TODO(商品列表控制器层)
 * @author: maple
 * @date: 2018年11月20日 下午2:03:46
 * 
 * @Copyright: 2018 www.hwua.com Inc. All rights reserved.
 *             注意：本内容仅限于海文科信息技术股份有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
@Controller
@RequestMapping("/items")
public class ItemController {

	// 调用业务逻辑层代码
	@Resource(name = "itemsBizImpl")
	private ItemsBiz ibiz;

	// 动态页面跳转
	@RequestMapping("/{forName}")
	public String dynaicPage(@PathVariable String forName) {
		return forName;
	}

	@RequestMapping("/itemList")
	public ModelAndView getItemList() {
		// 调用业务逻辑层代码
		List<Items> list = ibiz.getItemsList();
		ModelAndView modelAndView = new ModelAndView();
		// 将业务逻辑层获取的数据放入modelAndView
		modelAndView.addObject("itemList", list);
		// 设置modelAndView 逻辑视图
		/*
		 * /WEB-INF/jsp/逻辑视图.jsp
		 */
		modelAndView.setViewName("itemList");
		return modelAndView;
	}

	@RequestMapping("/doedit")
	public ModelAndView doedit(HttpServletRequest request) throws CustomException {
		String strId = request.getParameter("id");
		Integer id = null;
		// 如果id有值则转换成int类型
		if (strId != null && !"".equals(strId)) {
			id = Integer.valueOf(strId);
		} else {
			// 出错
			return null;
		}
		Items items = ibiz.getItemById(id);
		if (items == null) {
			throw new CustomException("商品信息不存在!");
		}
		// 创建ModelAndView
		ModelAndView modelAndView = new ModelAndView();
		// 向jsp传递数据
		modelAndView.addObject("item", items);
		// 设置跳转的jsp页面
		modelAndView.setViewName("doedit");
		return modelAndView;
	}

	// 跳转到批量修改页面
	@RequestMapping("/dobatchEdit")
	public String dobatchEdit(Model model) {
		List<Items> list = ibiz.getItemsList();
		model.addAttribute("itemList", list);
		return "batchEdit";
	}

	// 执行批量修改
	@RequestMapping("/batchEdit")
	public String batchEdit(QueryVo queryVo, String[] ids, HttpServletResponse response) throws IOException {
		boolean flag = ibiz.updateItems(queryVo, ids);
		if (flag) {
			return "redirect:itemList.action";
		} else {
			response.getWriter().println("<script>alert('修改失败！');</script>");
			return null;
		}
	}

	/**
	 * 
	 * @Title: doedit2 @Description: TODO(这里用一句话描述这个方法的作用) @param: @param id
	 *         形参名和请求的参数名要一致 @param: @param model
	 *         响应到视图层模型数据 @param: @return @return: String 逻辑视图名 @throws
	 */
	/*
	 * @RequestMapping("") public String doedit2(Integer id, Model model) { Items
	 * items = ibiz.getItemById(id); model.addAttribute("item", items); return
	 * "doedit"; }
	 */
	/*
	 * @RequestMapping("") public String
	 * doedit3(@RequestParam(value="id",required=true) Integer item_id, Model model)
	 * { Items items = ibiz.getItemById(item_id); model.addAttribute("item", items);
	 * return "doedit"; }
	 */

	@RequestMapping("/demo")
	public void demo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html;charset=utf-8");
		Items items = ibiz.getItemById(1);
		// 将对象转换成json格式输出
		String json = JSON.toJSONString(items);
		out.write(json);
	}

	/*
	 * @RequestMapping("/edit") public String edit(Items items, HttpServletResponse
	 * response) throws IOException { PrintWriter out = response.getWriter();
	 * boolean flag = ibiz.updateItem(items); if (flag) {// 修改成功 return
	 * "redirect:itemList.action"; } else {
	 * out.write("<script>alert('修改失败！');</script>"); return null; } }
	 */
	@RequestMapping("/edit")
	public String edit(Items items, HttpServletResponse response, MultipartFile pictureFile) throws IOException {
		// 进行图片上传
		if (pictureFile != null && pictureFile.getOriginalFilename() != null
				&& pictureFile.getOriginalFilename().length() > 0) {
			// 图片上传成功后，将图片的地址写到数据库
			String filePath = "D:\\upload\\temp\\";
			// 上传文件原始名称
			String originalFilename = pictureFile.getOriginalFilename();
			// 新的图片名称
			String newFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
			// 新文件
			File file = new java.io.File(filePath + newFileName);

			// 将内存中的文件写入磁盘
			pictureFile.transferTo(file);

			// 图片上传成功，将新图片地址写入数据库
			items.setPic(newFileName);
		}
		boolean flag = ibiz.updateItem(items);
		if (flag) {// 修改成功
			return "redirect:itemList.action";
		} else {
			response.getWriter().write("<script>alert('修改失败！');</script>");
			return null;
		}
	}

	// @RequestMapping(value={"/additems","/additems1","/additems2"})
	@RequestMapping(value = "/additems", method = RequestMethod.POST)
	public String additems(Items items, MultipartFile pictureFile, HttpServletResponse response)
			throws IllegalStateException, IOException {
		// 非空判断 用户上传了图片才执行上传操作
		if (pictureFile != null && pictureFile.getOriginalFilename() != null
				&& pictureFile.getOriginalFilename().length() > 0) {
			// 图片上传成功后，将图片的地址写到数据库
			String filePath = "D:\\upload\\temp\\";
			// 上传文件原始名称
			String originalFilename = pictureFile.getOriginalFilename();
			// 给原始文件重新命名 +源文件的后缀
			String newFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
			// 抽象文件
			File file = new File(filePath + newFileName);
			// 将内存中的文件写入磁盘
			pictureFile.transferTo(file);
			// 将新文件的路径 保存到items对象
			items.setPic(newFileName);
		}
		// 执行添加功能
		boolean flag = ibiz.addItems(items);
		if (flag) {
			return "redirect:itemList.action";
		} else {
			response.getWriter().println("<script>alert('删除失败！');</script>");
			return null;
		}

	}

	@RequestMapping("/vo")
	public String doqueryVo(Model model, QueryVo queryVo) {
		model.addAttribute("queryitems", queryVo);
		return "QueryVo";
	}

	/**
	 * 
	 * @Title: queryVo 包装类的请求 @Description: TODO(这里用一句话描述这个方法的作用) @param: @param
	 *         model 响应给视图(页面) 模型数据 @param: @param queryVo
	 *         接收页面的请求的数据 @param: @return @return: String 逻辑视图 @throws
	 */
	@RequestMapping("/queryVo")
	public String queryVo(Model model, QueryVo queryVo) {
		model.addAttribute("queryitems", queryVo);
		return "demo1";
	}

	@RequestMapping("/demo1")
	public String demo1(Model model, ModelMap modelmap) {
		Items items = ibiz.getItemById(1);
		model.addAttribute("items", items);
		return "demo1";
	}

	@RequestMapping("/delmore")
	public String delmore(QueryVo queryVo, String[] ids, HttpServletResponse response) throws IOException {
		System.out.println("-------------------");
		System.out.println(Arrays.toString(ids));
		boolean flag = ibiz.delItems(ids);
		if (flag) {
			return "redirect:itemList.action";
		} else {
			response.getWriter().println("<script>alert('删除失败！');</script>");
			return null;
		}
	}

	@RequestMapping("/delitems")
	public String delItems(Integer id, HttpServletResponse response) throws IOException {
		boolean flag = ibiz.delItems(id);
		if (flag) {
			return "redirect:itemList.action";
		} else {
			response.getWriter().println("<script>alert('删除失败！');</script>");
			return null;
		}
	}

	// 响应的内容为json格式的字符串
	@RequestMapping(value = "/dodetails/{id}", method = RequestMethod.GET)
	public @ResponseBody Items dodetails(@PathVariable("id") Integer id) throws CustomException {
		Items item = ibiz.getItemById(id);
		if (item == null) {
			throw new CustomException("商品信息不存在！");
		}
		return item;
	}

}
