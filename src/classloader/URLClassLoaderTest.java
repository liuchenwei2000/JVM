/**
 * 
 */
package classloader;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * URLClassLoader����ָ��λ����
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2013-5-28
 */
public class URLClassLoaderTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
//			URL url = new URL("file:/d:/lib/test.jar");// ָ��λ��
			URL url = new URL("file:/" + new File("./lib/test.jar").getAbsolutePath());
			
			URLClassLoader classloader = new URLClassLoader(new URL[] { url });
			
			Class<?> clazz = classloader.loadClass("test.A");
			System.out.println(clazz.getName());
			
			clazz = classloader.loadClass("test.B");
			System.out.println(clazz.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}