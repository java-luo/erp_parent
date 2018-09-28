package cn.itcast.erp.dao;
import java.util.List;
public interface IBaseDao<T> {
	
	List<T> getList(T T, int page, int rows);
	
	long getCount(T t);

	void delete(long uuid);

	void update(T t1);

	void save(T t1);

	T get(Long uuid);
	
	
}
