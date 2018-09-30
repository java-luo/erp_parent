package cn.itcast.erp.dao.impl;

import javax.annotation.Resource;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import cn.itcast.erp.dao.IGoodsTypeDao;
import cn.itcast.erp.entity.Goodstype;
/**
 * 部门数据访问层
 * @author javaluo
 *
 */
@Repository
public  class GoodsTypeDaoImpl extends BaseDaoImpl<Goodstype> implements IGoodsTypeDao {
	@Override
	public Class<Goodstype> getClazz() {
		return Goodstype.class;
	}
	@Override 
	@Resource(name="hibernateTemplate")
	public void setTemplate(HibernateTemplate template) {
		this.template=template;
	}
	@Override
	public DetachedCriteria getDetachedCriteria(Goodstype goods1) {
		DetachedCriteria dc= DetachedCriteria.forClass(getClazz());
		if(goods1!=null){
			if(null != goods1.getName() && goods1.getName().trim().length()>0){
				dc.add(Restrictions.like("name", goods1.getName(), MatchMode.ANYWHERE));
			}
		}
		return dc;
	}
	
}
