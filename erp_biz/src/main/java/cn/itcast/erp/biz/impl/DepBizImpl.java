package cn.itcast.erp.biz.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import cn.itcast.erp.biz.IDepBiz;
import cn.itcast.erp.dao.IBaseDao;
@Service
public class DepBizImpl extends IBaseBizImpl implements IDepBiz {
	@Override
	@Resource(name="depDaoImpl")
	public void setBaseDao(IBaseDao baseDao) {
		this.baseDao=baseDao;
	}
}
