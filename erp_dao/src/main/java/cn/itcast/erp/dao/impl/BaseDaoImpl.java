package cn.itcast.erp.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.HibernateTemplate;

import cn.itcast.erp.dao.IBaseDao;

public abstract class BaseDaoImpl<T> implements IBaseDao<T>{
	
	protected HibernateTemplate template;
	/**
	 * 离线criteria查询
	 * t:查询条件
	 * firstResult:起始条数
	 * maxResults:结束条数
	 */

	public List<T> getList(T t,int firstResult, int maxResults) {
		DetachedCriteria criteria= getDetachedCriteria(t);
		//条件查询
		return (List<T>) template.findByCriteria(criteria,firstResult,maxResults) ;
	}
	/**
	 * t:查询条件
	 * 查询符合条件得条数
	 */
	public long getCount(T t) {
		DetachedCriteria criteria=getDetachedCriteria(t);
		//条件查询
		criteria.setProjection(Projections.rowCount());
		return (Long) template.findByCriteria(criteria).get(0);
	}
	public void delete(long uuid) {
		template.delete(get(uuid));
	}
	public void update(T t1) {
		template.update(t1);
	}
	public void save(T t1) {
		template.save(t1);
	}
	public T get(Long uuid) {
		return	template.get(getClazz(),uuid );
	}
	 public abstract Class<T> getClazz();  
	 
	public abstract void setTemplate(HibernateTemplate template);
	
	public abstract DetachedCriteria getDetachedCriteria(T t);

}
