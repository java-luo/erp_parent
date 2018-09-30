package cn.itcast.erp.biz.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import cn.itcast.erp.biz.IDepBiz;
import cn.itcast.erp.biz.IMenuBiz;
import cn.itcast.erp.dao.IBaseDao;
@Service
public class MenuBizImpl extends IBaseBizImpl implements IMenuBiz {
	@Override
	@Resource(name="menuDaoImpl")
	public void setBaseDao(IBaseDao baseDao) {
		this.baseDao=baseDao;
	}
}
