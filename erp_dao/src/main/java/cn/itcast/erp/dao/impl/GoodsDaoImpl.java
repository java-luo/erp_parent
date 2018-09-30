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
import cn.itcast.erp.entity.Dep;
import cn.itcast.erp.entity.Goods;

/**
 * 部门数据访问层
 * @author javaluo
 *
 */
@Repository
public  class GoodsDaoImpl extends BaseDaoImpl<Goods> implements IGoodsDao {
	@Override
	public Class<Goods> getClazz() {
		return Goods.class;
	}
	@Override 
	@Resource(name="hibernateTemplate")
	public void setTemplate(HibernateTemplate template) {
		this.template=template;
	}
	@Override
	public DetachedCriteria getDetachedCriteria(Goods goods1) {
		DetachedCriteria dc= DetachedCriteria.forClass(getClazz());
		if(goods1!=null){
			if(null != goods1.getName() && goods1.getName().trim().length()>0){
				dc.add(Restrictions.like("name", goods1.getName(), MatchMode.ANYWHERE));
			}
			if(null != goods1.getOrigin() && goods1.getOrigin().trim().length()>0){
				dc.add(Restrictions.like("origin", goods1.getOrigin(), MatchMode.ANYWHERE));
			}
			if(null != goods1.getProducer() && goods1.getProducer().trim().length()>0){
				dc.add(Restrictions.like("producer", goods1.getProducer(), MatchMode.ANYWHERE));
			}
			if(null != goods1.getUnit() && goods1.getUnit().trim().length()>0){
				dc.add(Restrictions.like("unit", goods1.getUnit(), MatchMode.ANYWHERE));
			}
			//根据商品类型查询
			if(null != goods1.getGoodstype() && goods1.getGoodstype().getUuid() != null){
				dc.add(Restrictions.eq("goodstype", goods1.getGoodstype()));
			}
		}
		return dc;
	}
	
}
