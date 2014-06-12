/**
 * 
 */
package classloader;

/**
 * JVM判断两个class是否相同测试
 * <p>
 * JVM在判定两个class是否相同时，不仅要判断两个类名是否相同，而且要判断是否由同一个类加载器实例加载的。
 * 只有两者同时满足的情况下，JVM才认为这两个class是相同的，就算两个class是同一份class字节码，
 * 如果被两个不同的classloader实例所加载，JVM也会认为它们是两个不同class。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2013-5-27
 */
public class ClassEqualsTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String rootUrl = "http://localhost:8080/webdemo/classes";
		String classname = "HelloWorld";
		
		NetworkClassLoader classloader1 = new NetworkClassLoader(rootUrl);
		NetworkClassLoader classloader2 = new NetworkClassLoader(rootUrl);
		try {
			Class<?> clazz1 = classloader1.loadClass(classname);
			Class<?> clazz2 = classloader2.loadClass(classname);
			
			System.out.println(clazz1.equals(clazz2));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}