package cn.itcast.erp.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.erp.biz.IDepBiz;
import cn.itcast.erp.dao.IDepDao;
import cn.itcast.erp.entity.Dep;
@Service
public class IDepBizImpl implements IDepBiz{
	
	@Autowired
	private IDepDao depDao;

	public List<Dep> getList(Dep dep1, int fristResult, int maxResults) {
		// TODO Auto-generated method stub
		return depDao.getList(dep1,fristResult,maxResults);
	}
		public long getCount(Dep dep1){
		
		return depDao.getCount(dep1);
	}
	public void save(Dep dep1) {
			
		depDao.save(dep1);
	}
	public void update(Dep dep1) {
		depDao.update(dep1);
	}
	public void delete(long uuid) {
		depDao.delete(uuid);
		
	}
	public Dep get(Long uuid) {
		return depDao.get(uuid);
	}
	
}
