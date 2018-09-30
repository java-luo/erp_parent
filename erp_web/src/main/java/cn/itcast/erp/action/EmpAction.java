package cn.itcast.erp.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import org.apache.shiro.web.session.HttpServletSession;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.itcast.erp.exception.ErpException;

import cn.itcast.erp.biz.IBaseBiz;
import cn.itcast.erp.biz.IEmpBiz;
import cn.itcast.erp.entity.Emp;

public class EmpAction extends BaseAction<Emp>{
	private String newPwd;
	private String oldPwd;
	@Autowired
	private IEmpBiz empBiz;
	
	@Override
	@Resource(name="empBizImpl")
	public void setBaseBiz(IBaseBiz baseBiz) {
		this.baseBiz=baseBiz;
	}
	
	public void reset(){
		Emp emp=getInfo();
		if(emp==null){
			write(ajaxReturn(false, "对不起用户未登录"));
		}else{
			try {
				empBiz.resetPwd(emp.getUsername(), oldPwd, newPwd);
				HttpSession session=ServletActionContext.getRequest().getSession();
				session.removeAttribute("loginUser");
				write(ajaxReturn(true, "修改成功"));
			} catch (ErpException e) {
				e.printStackTrace();
				write(ajaxReturn(false, e.getMessage()));
			}catch (Exception e) {
				write(ajaxReturn(false, "发生异常"));
			}
		}
	}
	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

	public String getOldPwd() {
		return oldPwd;
	}

	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}
}
