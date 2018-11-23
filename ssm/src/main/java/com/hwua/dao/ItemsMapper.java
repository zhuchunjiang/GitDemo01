package com.hwua.dao;

import java.util.List;

import com.hwua.entity.Items;
/**
 * 
 * @ClassName:  ItemsMapper   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: maple
 * @date:   2018年11月20日 下午1:51:29   
 *     
 * @Copyright: 2018 www.hwua.com Inc. All rights reserved. 
 * 注意：本内容仅限于海文科信息技术股份有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public interface ItemsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Items record);

    int insertSelective(Items record);

    Items selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Items record);

    int updateByPrimaryKeyWithBLOBs(Items record);

    int updateByPrimaryKey(Items record);
    /**
     * 
     * @Title: getItemsList  实现商品查询列表
     * @Description: TODO(这里用一句话描述这个方法的作用)   
     * @param: @return  
     * @return: List<Items>      
     * @throws
     */
    List<Items> getItemsList();
}