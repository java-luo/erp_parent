package cn.itcast.erp.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

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
}
