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
 * �������ݷ��ʲ�ʵ��
 * @author javaluo
 *
 */
@Repository
public  class DepDaoImpl implements IDepDao{
	@Resource(name="hibernateTemplate")
	private HibernateTemplate template;
	/**
	 * ����criteria��ѯ
	 * dep:��ѯ����
	 * firstResult:��ʼ����
	 * maxResults:��������
	 */

	public List<Dep> getList(Dep dep,int firstResult, int maxResults) {
		DetachedCriteria criteria= DetachedCriteria.forClass(Dep.class);
		//������ѯ
		if(dep!=null){
			if(dep.getName()!=null&&dep.getName().trim().length()>0){
				criteria.add(Restrictions.like("name", dep.getName(),MatchMode.ANYWHERE));
			}
			if(dep.getTele()!=null&&dep.getTele().trim().length()>0){
				criteria.add(Restrictions.like("tele", dep.getTele(),MatchMode.ANYWHERE));
			}
		}
		return (List<Dep>) template.findByCriteria(criteria,firstResult,maxResults) ;
	}
	/**
	 * dep:��ѯ����
	 * ��ѯ��������������
	 */
	public long getCount(Dep dep) {
		DetachedCriteria criteria= DetachedCriteria.forClass(Dep.class);
		//������ѯ
		if(dep!=null){
			if(dep.getName()!=null&&dep.getName().trim().length()>0){
				criteria.add(Restrictions.like("name", dep.getName(),MatchMode.ANYWHERE));
			}
			if(dep.getTele()!=null&&dep.getTele().trim().length()>0){
				criteria.add(Restrictions.like("tele", dep.getTele(),MatchMode.ANYWHERE));
			}
		}
		criteria.setProjection(Projections.rowCount());
		return (Long) template.findByCriteria(criteria).get(0);
	}
	public void delete(long uuid) {
		template.delete(get(uuid));
	}
	public void update(Dep dep1) {
		// TODO Auto-generated method stub
		template.update(dep1);
	}
	public void save(Dep dep1) {
		// TODO Auto-generated method stub
		template.save(dep1);
	}
	public Dep get(Long uuid) {
	return	template.get(Dep.class,uuid );
	}
}
