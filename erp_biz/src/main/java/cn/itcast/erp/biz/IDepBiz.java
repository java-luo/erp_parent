package cn.itcast.erp.biz;

import java.util.List;

import cn.itcast.erp.entity.Dep;

/**
 * 部门业务逻辑接口
 * @author javaluo
 *
 */
public interface IDepBiz {

	public List<Dep> getList(Dep dep1, int fristResult, int maxResults);
	
	
	public long getCount(Dep dep1);


	public void save(Dep dep1);
	
	public void update(Dep dep1);
	
	public void delete(long uuid);


	public Dep get(Long uuid);
}
