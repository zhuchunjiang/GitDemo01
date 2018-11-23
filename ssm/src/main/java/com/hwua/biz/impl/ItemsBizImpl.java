package com.hwua.biz.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hwua.biz.ItemsBiz;
import com.hwua.dao.ItemsMapper;
import com.hwua.entity.Items;
import com.hwua.entity.QueryVo;

/**
 * 
 * @ClassName: ItemsBizImpl
 * @Description:TODO(业务逻辑层实现类)
 * @author: maple
 * @date: 2018年11月20日 下午1:57:36
 * 
 * @Copyright: 2018 www.hwua.com Inc. All rights reserved.
 *             注意：本内容仅限于海文科信息技术股份有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
@Service
public class ItemsBizImpl implements ItemsBiz {
	@Autowired
	private ItemsMapper itemsMapper;

	/**
	 * 
	 * <p>
	 * Title: getItemsList
	 * </p>
	 * <p>
	 * Description:获取所有用户信息
	 * </p>
	 * 
	 * @return 所有用户信息
	 * @see com.hwua.biz.ItemsBiz#getItemsList()
	 */
	public List<Items> getItemsList() {
		return itemsMapper.getItemsList();
	}

	public Items getItemById(int id) {
		// TODO Auto-generated method stub
		return itemsMapper.selectByPrimaryKey(id);
	}

	public boolean updateItem(Items items) {
		int row = itemsMapper.updateByPrimaryKeySelective(items);
		return (row > 0) ? true : false;
	}

	public boolean delItems(String[] ids) {
		int row = 0; // 受影响的函数
		int count = 0;// 计数
		for (int i = 0; i < ids.length; i++) {
			row = itemsMapper.deleteByPrimaryKey(new Integer(ids[i]));
			if (row > 0) {
				count++;
			}
		}
		return (count == ids.length) ? true : false;
	}

	public boolean delItems(Integer ids) {
		int row = itemsMapper.deleteByPrimaryKey(ids);
		return (row > 0) ? true : false;
	}

	public boolean updateItems(QueryVo queryVo, String[] ids) {
		int count = 0;// 修改的次数
		// 根据id筛选出 你要修改的对象
		List<Items> itemlist = queryVo.getItemsList();
		for (int i = 0; i < itemlist.size(); i++) {
			for (int j = 0; j < ids.length; j++) {
				Integer id = new Integer(ids[j]);
				if (id.equals(itemlist.get(i).getId())) {
					// 执行修改操作
					int row = itemsMapper.updateByPrimaryKeySelective(itemlist.get(i));
					if (row > 0) {
						count++;
					}
				}
			}
		}
		return (count == ids.length) ? true : false;
	}

	public boolean addItems(Items items) {
		int row = itemsMapper.insertSelective(items);
		return (row > 0) ? true : false;
	}
}
