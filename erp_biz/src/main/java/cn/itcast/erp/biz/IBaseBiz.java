package cn.itcast.erp.biz;

import java.util.List;


public interface IBaseBiz<T> {
	public List<T> getList(T t1, int fristResult, int maxResults);
	
	public long getCount(T t1);

	public void save(T t1);
	
	public void update(T t1);
	
	public void delete(long uuid);

	public T get(Long uuid);
}
