package cn.itcast.erp.biz.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.itcast.erp.biz.IEmpBiz;
import cn.itcast.erp.dao.IBaseDao;
import cn.itcast.erp.dao.IEmpDao;
import cn.itcast.erp.entity.Emp;
@Service
public class EmpBizImpl extends IBaseBizImpl<Emp> implements IEmpBiz{
	@Resource(name="empDaoImpl")
	private IEmpDao empDao;
	@Override
	@Resource(name="empDaoImpl")
	public void setBaseDao(IBaseDao baseDao) {
		this.baseDao=empDao;
	}
	public Emp login(String username, String password) {
		// TODO Auto-generated method stub
		return empDao.login(username, password);
	}
}
