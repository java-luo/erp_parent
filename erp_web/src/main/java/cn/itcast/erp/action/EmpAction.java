package cn.itcast.erp.action;

import javax.annotation.Resource;

import cn.itcast.erp.biz.IBaseBiz;
import cn.itcast.erp.entity.Emp;

public class EmpAction extends BaseAction<Emp>{
	@Override
	@Resource(name="empBizImpl")
	public void setBaseBiz(IBaseBiz baseBiz) {
		this.baseBiz=baseBiz;
	}
}
