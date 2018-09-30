package cn.itcast.erp.action;


import javax.annotation.Resource;
import org.springframework.stereotype.Controller;


import cn.itcast.erp.biz.IBaseBiz;
import cn.itcast.erp.entity.Goods;
import cn.itcast.erp.entity.Goodstype;
@Controller
public class GoodsTypeAction extends BaseAction<Goodstype>{

	@Override
	@Resource(name="goodsTypeBizImpl")
	public void setBaseBiz(IBaseBiz baseBiz) {
		this.baseBiz=baseBiz;
	}
}
