package com.onlineshop.service;

import com.onlineshop.common.EUDataGridResult;
import com.onlineshop.pojo.TbItem;

public interface ItemService {
	public TbItem getItemById(long itemId) ;
	public EUDataGridResult getItemList(int page, int rows) ;
}
