package cn.itcast.erp.biz.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import cn.itcast.erp.biz.IDepBiz;
import cn.itcast.erp.biz.IGoodsBiz;
import cn.itcast.erp.dao.IBaseDao;
import cn.itcast.erp.entity.Goods;
@Service
public class GoodsBizImpl extends IBaseBizImpl<Goods> implements IGoodsBiz {
	@Override
	@Resource(name="goodsDaoImpl")
	public void setBaseDao(IBaseDao baseDao) {
		this.baseDao=baseDao;
	}
}
