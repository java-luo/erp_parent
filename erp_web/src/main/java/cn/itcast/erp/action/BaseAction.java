package cn.itcast.erp.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

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
		Map<String,Object> map=new HashMap();
		//计算开始位置
		int FristResult=(page-1)*rows;
		List<T> list = baseBiz.getList(t1,FristResult,rows);
		long count=baseBiz.getCount(t1);
		map.put("total", count);
		map.put("rows", list);
		
		String json=JSON.toJSONString(map,SerializerFeature.DisableCircularReferenceDetect);
		write(json);
	}
	public void list(){
		Map<String,Object> map=new HashMap();
		//计算开始位置
		int FristResult=(page-1)*rows;
		List<T> list = baseBiz.getList(t1,FristResult,100);
		String json=JSON.toJSONString(list,SerializerFeature.DisableCircularReferenceDetect);
		write(json);
	}
	
	public void save(){
		try {
			baseBiz.save(t1);
			write(ajaxReturn(true, "添加成功"));
		} catch (Exception e) {
			e.printStackTrace();
			write(ajaxReturn(false, "添加失败"));
		}
	}
	public void delete(){
		try {
			System.out.println(id);
			baseBiz.delete(id);
			write(ajaxReturn(true, "删除成功"));
		} catch(Exception e)  {
			e.printStackTrace();
			// TODO: handle finally clause
			write(ajaxReturn(false, "删除失败"));
		}
	}
	public void update(){
		try {
			baseBiz.update(t1);
			write(ajaxReturn(true, "修改成功"));
		} catch (Exception e) {
			// TODO: handle exception
			write(ajaxReturn(false, "修改失败"));
		}
	}
	public void get(){
		T t=(T) baseBiz.get(id);
		//转换为json字符串 如果有date类型的 转换为指定格式的时间类型
		String jsonString = JSON.toJSONStringWithDateFormat(t,"yyyy-MM-dd");
		//加前缀
		String json=mapJson(jsonString, "t1");
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
	 * 向前台相应数据
	 * @param json
	 */
	protected void write(String json) {
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
	 * 给指定json加前缀
	 * @param json :传递过来的参数
	 * @param prefix:添加的前缀
	 * @return
	 */
	private String mapJson(String json,String prefix){
 		Map<String, Object> map=JSON.parseObject(json);
		Map<String,Object> newMap=new HashMap<String, Object>();
		//给json字符串加前缀
		for (String key:map.keySet()) {
			//如果他里边还有其他json对象  给他的属性也加上前缀
			if(map.get(key) instanceof Map){
				//取出他的key
				Map<String,Object> map2=(Map<String, Object>) map.get(key);
				//给他加上前缀
				for(String k :map2.keySet()){
					newMap.put(prefix+"."+key+"."+k, map2.get(k));
				}
			}else{
				newMap.put(prefix+"."+key, map.get(key));
			}
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
