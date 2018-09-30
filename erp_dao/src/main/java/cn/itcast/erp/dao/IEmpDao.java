package cn.itcast.erp.dao;

import cn.itcast.erp.entity.Emp;

public interface IEmpDao extends IBaseDao<Emp>{

	public Emp login(String username,String password);
	
	public void resetPwd(String username,String oldPwd,String newPwd);
	
}
