package cn.itcast.erp.biz.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import cn.itcast.erp.biz.IDepBiz;
import cn.itcast.erp.biz.IGoodsBiz;
import cn.itcast.erp.biz.IGoodsTypeBiz;
import cn.itcast.erp.dao.IBaseDao;
import cn.itcast.erp.entity.Goods;
import cn.itcast.erp.entity.Goodstype;
@Service
public class GoodsTypeBizImpl extends IBaseBizImpl<Goodstype> implements IGoodsTypeBiz {
	@Override
	@Resource(name="goodsTypeDaoImpl")
	public void setBaseDao(IBaseDao baseDao) {
		this.baseDao=baseDao;
	}
}
