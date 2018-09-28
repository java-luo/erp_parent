package cn.itcast.erp.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.engine.spi.TypedValue;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import cn.itcast.erp.dao.IDepDao;
import cn.itcast.erp.entity.Dep;

/**
 * 部门数据访问层实现
 * @author javaluo
 *
 */
@Repository
public  class DepDaoImpl extends BaseDaoImpl<Dep> implements IDepDao {
	@Override
	public Class<Dep> getClazz() {
		return Dep.class;
	}
	@Override 
	@Resource(name="hibernateTemplate")
	public void setTemplate(HibernateTemplate template) {
		this.template=template;
	}
	@Override
	public DetachedCriteria getDetachedCriteria(Dep t) {
		DetachedCriteria criteria= DetachedCriteria.forClass(getClazz());
		//条件查询
		if(t!=null){
			if(t.getName()!=null&&t.getName().trim().length()>0){
				criteria.add(Restrictions.like("name", t.getName(),MatchMode.ANYWHERE));
			}
			if(t.getTele()!=null&&t.getTele().trim().length()>0){
				criteria.add(Restrictions.like("tele", t.getTele(),MatchMode.ANYWHERE));
			}
		}
		return criteria;
	}
	
}
