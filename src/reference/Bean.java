/**
 * 
 */
package reference;

/**
 * һ���������õļ򵥶���
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2013-6-5
 */
public class Bean {

	private String name;

	public Bean(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Bean:" + name;
	}
}