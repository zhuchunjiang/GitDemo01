package com.hwua.test;

import static org.junit.Assert.fail;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hwua.biz.ItemsBiz;
import com.hwua.entity.Items;
import com.hwua.entity.QueryVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext-tx.xml")
public class ItemsBizImplTest {
	@Resource(name = "itemsBizImpl")
	private ItemsBiz itmesBiz;

	@Test
	public void testGetItemsList() {
		List<Items> ilist = itmesBiz.getItemsList();
		System.out.println(ilist);
	}

	@Test
	public void testUpdateItems() {
		List<Items> ilist = itmesBiz.getItemsList();
		ilist.get(0).setName("000000000");
		ilist.get(1).setName("111111111");
		String[] ids = new String[] { "1", "2" };
		
		QueryVo queryVo=new QueryVo();
		queryVo.setItemsList(ilist);
		
		itmesBiz.updateItems(queryVo, ids);
	}
}
