package cn.itcast.erp.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionContext;

import cn.itcast.erp.biz.IDepBiz;
import cn.itcast.erp.biz.IEmpBiz;
import cn.itcast.erp.entity.Dep;
import cn.itcast.erp.entity.Emp;

public class LoginAction {
	
	private String username;
	
	private String password;
	@Autowired
	private IEmpBiz empBiz;
	
	public void login(){
		Emp emp=empBiz.login(username, password);
		try {
			if(emp!=null){
			
			HttpSession session=ServletActionContext.getRequest().getSession();
			session.setAttribute("loginUser", emp);
			write(ajaxReturn(true, "登陆成功"));
			}else{
			write(ajaxReturn(false, "没有此用户"));
		}	
		} catch (Exception e) {
			write(ajaxReturn(false, "登陆失败"));
		}
    }	
	public void logout(){
		HttpSession session=ServletActionContext.getRequest().getSession();
		Emp emp= (Emp) session.getAttribute("loginUser");
		if(emp!=null){
			session.removeAttribute("loginUser");
			write(ajaxReturn(true, "退出登陆"));
		}else{
			write(ajaxReturn(true, "用户未登陆"));
		}
		
	}
	public void getInfo(){
		
		HttpSession session=ServletActionContext.getRequest().getSession();
		Emp emp= (Emp) session.getAttribute("loginUser");
		if(emp!=null){
			JSON.toJSONString(emp);
			write(JSON.toJSONString(emp));
		}else{
		write(ajaxReturn(false, "用户未登录"));}
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
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
