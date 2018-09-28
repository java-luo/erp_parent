package cn.itcast.erp.biz.impl;

import java.util.List;


import cn.itcast.erp.biz.IBaseBiz;
import cn.itcast.erp.dao.IBaseDao;

public abstract class IBaseBizImpl<T> implements IBaseBiz<T>{
	
	protected IBaseDao<T> baseDao;

	public List<T> getList(T t1, int fristResult, int maxResults) {
		// TODO Auto-generated method stub
		return baseDao.getList(t1, fristResult, maxResults);
	}
	public long getCount(T t1) {
		return baseDao.getCount(t1);
	}

	public void save(T t1) {
		baseDao.save(t1);
	}

	public void update(T t1) {
		baseDao.update(t1);
	}

	public void delete(long uuid) {
		baseDao.delete(uuid);
	}

	public T get(Long uuid) {
		return  baseDao.get(uuid);
	}
	public abstract void setBaseDao(IBaseDao baseDao) ;
	
}
