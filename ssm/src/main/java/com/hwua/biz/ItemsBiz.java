package com.hwua.biz;

import java.util.List;

import com.hwua.entity.Items;
import com.hwua.entity.QueryVo;

/**
 * 
 * @ClassName: ItemsBiz
 * @Description:TODO(业务逻辑层接口)
 * @author: maple
 * @date: 2018年11月20日 下午1:56:46
 * 
 * @Copyright: 2018 www.hwua.com Inc. All rights reserved.
 *             注意：本内容仅限于海文科信息技术股份有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public interface ItemsBiz {
	/**
	 * 
	 * @Title: getItemsList 实现商品查询列表 @Description:
	 * TODO(这里用一句话描述这个方法的作用) @param: @return @return: List<Items> @throws
	 */
	List<Items> getItemsList();

	/**
	 * 
	 * @Title: getItemById @Description: TODO(这里用一句话描述这个方法的作用) @param: @param
	 * id @param: @return @return: Items @throws
	 */
	public Items getItemById(int id);

	/**
	 * 
	 * @Title: updateItem @Description: TODO(修改操作) @param: @param
	 * items @param: @return @return: boolean @throws
	 */
	public boolean updateItem(Items items);

	public boolean delItems(String[] ids);

	public boolean delItems(Integer ids);
	/**
	 * 
	 * @Title: updateItems   批量修改操作
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param queryVo 批量修改操作的数据
	 * @param: @param ids 批量修改的id
	 * @param: @return  
	 * @return: boolean      
	 * @throws
	 */
	public boolean updateItems(QueryVo queryVo,String[] ids);
	/**
	 * 添加功能
	 * @Title: addItems   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param items
	 * @param: @return  
	 * @return: boolean      
	 * @throws
	 */
	public boolean addItems(Items items);
}
