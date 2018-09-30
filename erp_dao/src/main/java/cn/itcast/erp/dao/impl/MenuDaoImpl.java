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
import cn.itcast.erp.dao.IGoodsDao;
import cn.itcast.erp.dao.IMenuDao;
import cn.itcast.erp.entity.Dep;
import cn.itcast.erp.entity.Goods;
import cn.itcast.erp.entity.Menu;

/**
 * 部门数据访问层
 * @author javaluo
 *
 */
@Repository
public  class MenuDaoImpl extends BaseDaoImpl<Menu> implements IMenuDao {
	@Override
	public Class<Menu> getClazz() {
		return Menu.class;
	}
	@Override 
	@Resource(name="hibernateTemplate")
	public void setTemplate(HibernateTemplate template) {
		this.template=template;
	}
	@Override
	public DetachedCriteria getDetachedCriteria(Menu goods1) {
		DetachedCriteria dc= DetachedCriteria.forClass(getClazz());
		return dc;
	}
	@Override
	public Menu get(Long uuid) {
		String id=uuid.toString();
		return template.get(Menu.class, id);
	}
}
