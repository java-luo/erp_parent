package cn.itcast.erp.action;


import javax.annotation.Resource;
import org.springframework.stereotype.Controller;


import cn.itcast.erp.biz.IBaseBiz;
import cn.itcast.erp.entity.Goods;
@Controller
public class GoodsAction extends BaseAction<Goods>{

	@Override
	@Resource(name="goodsBizImpl")
	public void setBaseBiz(IBaseBiz baseBiz) {
		this.baseBiz=baseBiz;
	}
}
