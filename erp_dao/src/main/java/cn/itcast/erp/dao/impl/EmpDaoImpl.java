package cn.itcast.erp.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.itcast.erp.common.Md5Util;
import com.itcast.erp.exception.ErpException;

import cn.itcast.erp.dao.IEmpDao;
 import cn.itcast.erp.entity.Emp;
@Repository
public  class EmpDaoImpl extends BaseDaoImpl<Emp> implements IEmpDao{
	@Override
	public Class<Emp> getClazz() {
		return Emp.class;
	}
	@Override
	@Resource(name="hibernateTemplate")
	public void setTemplate(HibernateTemplate template) {
		this.template=template;
	}
	@Override
	public DetachedCriteria getDetachedCriteria(Emp t) {
		DetachedCriteria criteria= DetachedCriteria.forClass(getClazz());
		if(t!=null){
			if(t.getName()!=null&&t.getName().trim().length()>0){
				criteria.add(Restrictions.like("name", t.getName(),MatchMode.ANYWHERE));
			}
			if(t.getTele()!=null&&t.getTele().trim().length()>0){
				criteria.add(Restrictions.like("tele", t.getTele(),MatchMode.ANYWHERE));
			}
			if(t.getEmail()!=null&&t.getEmail().trim().length()>0){
				criteria.add(Restrictions.like("email", t.getEmail(),MatchMode.ANYWHERE));
			}
			if(t.getUsername()!=null&&t.getUsername().trim().length()>0){
				criteria.add(Restrictions.like("username", t.getUsername(),MatchMode.ANYWHERE));
			}
		}
		return criteria;
	}
	public Emp login(String username, String password) {
		List<Emp> emp=(List<Emp>) template.find("from Emp where username =? and pwd =?", username,password);
		if(emp!=null&&emp.size()>0){
			return emp.get(0);
		}
		return null;
	}
	public void resetPwd(String username, String oldPwd, String newPwd) {
		int count=	template.bulkUpdate("update Emp set pwd=? where username= ? and pwd= ? ", newPwd,username,oldPwd);
		if(count==0){
			throw new ErpException("密码错误");
		}
	}
	@Override
	//手动添加要修改的值  :以免前台传递过来密码,更新进去
	public void update(Emp emp) {
		Emp dbEmp=template.get(Emp.class, emp.getUuid());
	    //修改真实姓名
	    dbEmp.setName(emp.getName());
	    //修改联系电话
	    dbEmp.setTele(emp.getTele());
	    //修改联系地址
	    dbEmp.setAddress(emp.getAddress());
	    //修改出生年月日
	    dbEmp.setBirthday(emp.getBirthday());
	    //修改邮箱地址
	    dbEmp.setEmail(emp.getEmail());
	    //修改所属部门
	    dbEmp.setDep(emp.getDep());
	    
	    dbEmp.setUsername(emp.getUsername());
	    
	    dbEmp.setGender(emp.getGender());
		super.update(dbEmp);
	}
	
	
}
