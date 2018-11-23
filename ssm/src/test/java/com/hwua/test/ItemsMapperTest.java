package com.hwua.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hwua.dao.ItemsMapper;
import com.hwua.entity.Items;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext-tx.xml")
public class ItemsMapperTest {
	@Autowired // 根据类型自动注入
	private ItemsMapper itemsdao;

	@Test
	public void testSelectByPrimaryKey() {
		Items item = itemsdao.selectByPrimaryKey(1);
		System.out.println(item);
	}

	@Test
	public void testGetItemsList() {
		List<Items> ilist = itemsdao.getItemsList();
		System.out.println(ilist);
	}

}
