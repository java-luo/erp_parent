package cn.itcast.erp.dao;

import java.util.List;

import cn.itcast.erp.entity.Dep;

/**
 * �������ݷ��ʲ�ӿ�
 * @author javaluo
 *
 */
public interface IDepDao {

	List<Dep> getList(Dep dep, int page, int rows);
	
	long getCount(Dep dep);

	void delete(long uuid);

	void update(Dep dep1);

	void save(Dep dep1);

	Dep get(Long uuid);
}
