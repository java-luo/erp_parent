package cn.itcast.erp.biz.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.itcast.erp.common.Md5Util;

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
		String Md5Pas=Md5Util.Md5Encode(password,username);
		// TODO Auto-generated method stub
		return empDao.login(username, Md5Pas);
	}
	/**
	 * 重写添加方法 给密码加密
	 */
	@Override
	public void save(Emp t1) {
		//使用name作为盐值  给密码加密
		String Md5Pwd=Md5Util.Md5Encode(t1.getPwd(),t1.getUsername());
		t1.setPwd(Md5Pwd);
		super.save(t1);
	}
	public void resetPwd(String username, String oldPwd, String newPwd) {
		String Md5oldPwd=Md5Util.Md5Encode(oldPwd, username);
		String Md5newPwd=Md5Util.Md5Encode(newPwd, username);
		empDao.resetPwd(username, Md5oldPwd, Md5newPwd);
	}
}
