package cn.itcast.erp.entity;
/**
 * ����ʵ����
 * @author javaluo
 *
 */
public class Dep {

	private Long uuid;//����Id
	
	private String name;//��������
	
	private String tele;//���ŵ绰

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
