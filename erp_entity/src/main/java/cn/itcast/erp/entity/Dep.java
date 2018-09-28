package cn.itcast.erp.entity;
/**
 * 部门实体类
 * @author javaluo
 *
 */
public class Dep {

	private Long uuid;//部门Id
	
	private String name;//部门名称
	
	private String tele;//部门电话

	public Long getUuid() {
		return uuid;
	}

	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTele() {
		return tele;
	}

	public void setTele(String tele) {
		this.tele = tele;
	}
}
