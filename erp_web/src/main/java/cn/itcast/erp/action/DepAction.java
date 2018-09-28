package cn.itcast.erp.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;

import cn.itcast.erp.biz.IDepBiz;
import cn.itcast.erp.entity.Dep;
@Controller
public class DepAction {
	@Autowired
	private IDepBiz depBiz;
	private Dep dep1;
	private int page;
	private int rows;
	public void getList(){
		//封装响应给分页得结果集  total{查询出条数},rows[结果集数组]
		Map<String,Object> map=new HashMap();
		//开始位置
		int FristResult=(page-1)*rows;
		if(dep1!=null){
			System.out.println(dep1.getName());
		}
		List<Dep> list = depBiz.getList(dep1,FristResult,rows);
		long count=depBiz.getCount(dep1);
		map.put("total", count);
		map.put("rows", list);
		String json=JSON.toJSONString(map);
		write(json);
	}
	
	public void save(){
		try {
			depBiz.save(dep1);
			write(ajaxReturn(true, "添加成功"));
		} catch (Exception e) {
			write(ajaxReturn(false, "发生异常"));
		}
	}
	public void delete(){
		try {
			System.out.println(dep1.getUuid());
			depBiz.delete(dep1.getUuid());
			write(ajaxReturn(true, "删除成功"));
		} catch(Exception e)  {
			e.printStackTrace();
			// TODO: handle finally clause
			write(ajaxReturn(false, "发生异常"));
		}
	}
	public void update(){
		try {
			depBiz.update(dep1);
			write(ajaxReturn(true, "修改成功"));
		} catch (Exception e) {
			// TODO: handle exception
			write(ajaxReturn(false, "发生异常"));
		}
	}
	public void get(){
		Dep dep=depBiz.get(dep1.getUuid());
		String json=mapJson(JSON.toJSONString(dep), "dep1");
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
	 * 抽取公共写方法
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
	 * 给json加上前缀
	 * @param json :需要修改的json
	 * @param prefix:加的前缀
	 * @return
	 */
	private String mapJson(String json,String prefix){
		System.out.println(json);
		//将json转换为map
 		Map<String, Object> map=JSON.parseObject(json);
		Map<String,Object> newMap=new HashMap<String, Object>();
		//遍历所有的key加上前缀存入新map中
		for (String key:map.keySet()) {
			newMap.put(prefix+"."+key, map.get(key));
		}
		String newJson=JSON.toJSONString(newMap);
		return newJson;
	} 
	public IDepBiz getDepBiz() {
		return depBiz;
	}
	public void setDepBiz(IDepBiz depBiz) {
		this.depBiz = depBiz;
	}
	public Dep getDep1() {
		return dep1;
	}
	public void setDep1(Dep dep1) {
		this.dep1 = dep1;
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
