package cn.itcast.erp.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;

import cn.itcast.erp.biz.IBaseBiz;

public abstract class BaseAction<T> {
	
	protected IBaseBiz baseBiz;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public abstract void setBaseBiz(IBaseBiz baseBiz) ;
	

	private T t1;
	private int page;
	private int rows;
	private long id;
	public void getList(){
		//��װ��Ӧ����ҳ�ý����  total{��ѯ������},rows[���������]
		Map<String,Object> map=new HashMap();
		//��ʼλ��
		int FristResult=(page-1)*rows;
		List<T> list = baseBiz.getList(t1,FristResult,rows);
		long count=baseBiz.getCount(t1);
		map.put("total", count);
		map.put("rows", list);
		String json=JSON.toJSONString(map);
		write(json);
	}
	public void save(){
		try {
			baseBiz.save(t1);
			write(ajaxReturn(true, "��ӳɹ�"));
		} catch (Exception e) {
			write(ajaxReturn(false, "�����쳣"));
		}
	}
	public void delete(){
		try {
			System.out.println(id);
			baseBiz.delete(id);
			write(ajaxReturn(true, "ɾ���ɹ�"));
		} catch(Exception e)  {
			e.printStackTrace();
			// TODO: handle finally clause
			write(ajaxReturn(false, "�����쳣"));
		}
	}
	public void update(){
		try {
			baseBiz.update(t1);
			write(ajaxReturn(true, "�޸ĳɹ�"));
		} catch (Exception e) {
			// TODO: handle exception
			write(ajaxReturn(false, "�����쳣"));
		}
	}
	public void get(){
		T t=(T) baseBiz.get(id);
		String json=mapJson(JSON.toJSONString(t), "t1");
		write(json);
	}
	private String ajaxReturn(boolean success,String msg){
		Map map=new HashMap();
		map.put("success", success);
		map.put("msg", msg);
		String json=JSON.toJSONString(map);
		return	json;
	}  
	/**
	 * ��ȡ����д����
	 * @param json
	 */
	private void write(String json) {
		HttpServletResponse response= ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		try {
			response.getWriter().write(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * ��json����ǰ׺
	 * @param json :��Ҫ�޸ĵ�json
	 * @param prefix:�ӵ�ǰ׺
	 * @return
	 */
	private String mapJson(String json,String prefix){
		System.out.println(json);
		//��jsonת��Ϊmap
 		Map<String, Object> map=JSON.parseObject(json);
		Map<String,Object> newMap=new HashMap<String, Object>();
		//�������е�key����ǰ׺������map��
		for (String key:map.keySet()) {
			newMap.put(prefix+"."+key, map.get(key));
		}
		String newJson=JSON.toJSONString(newMap);
		return newJson;
	} 
	public IBaseBiz getTBiz() {
		return baseBiz;
	}
	public void setTBiz(IBaseBiz baseBiz) {
		this.baseBiz = baseBiz;
	}
	public T getT1() {
		return t1;
	}
	public void setT1(T t1) {
		this.t1 = t1;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}
		
	
}
