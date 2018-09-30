package cn.itcast.erp.biz;

import cn.itcast.erp.entity.Emp;

public interface IEmpBiz extends IBaseBiz<Emp>{

	public Emp login(String username, String password);
}
