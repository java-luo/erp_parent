package cn.itcast.erp.action;


import javax.annotation.Resource;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;

import cn.itcast.erp.biz.IBaseBiz;
import cn.itcast.erp.entity.Goods;
import cn.itcast.erp.entity.Menu;
@Controller
public class MenuAction extends BaseAction<Menu>{
	
	

	@Override
	@Resource(name="menuBizImpl")
	public void setBaseBiz(IBaseBiz baseBiz) {
		this.baseBiz=baseBiz;
	}
	
	public void getTreeMenu(){
		long i=0;
		Menu t= (Menu) baseBiz.get(i);
		String jsonString = JSON.toJSONString(t);
		//加前缀
		write(jsonString);
	}
	
}
