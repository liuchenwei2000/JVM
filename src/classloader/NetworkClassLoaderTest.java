/**
 * 
 */
package classloader;

/**
 * NetworkClassLoader加载类演示
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2013-5-27
 */
public class NetworkClassLoaderTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String rootUrl = "http://localhost:8080/webdemo/classes";
		NetworkClassLoader classloader = new NetworkClassLoader(rootUrl);
		String classname = "HelloWorld";
		try {
			Class<?> clazz = classloader.loadClass(classname);
			System.out.println(clazz.getClassLoader());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}