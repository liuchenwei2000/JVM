/**
 * 
 */
package classloader;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;

/**
 * 加载网络上class的ClassLoader
 * <p>
 * <li>1,继承 java.lang.ClassLoader
 * <li>2,重写父类的findClass方法
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2013-5-27
 */
public class NetworkClassLoader extends ClassLoader {

	private String rootURL;

	public NetworkClassLoader(String rootUrl) {
		this.rootURL = rootUrl;
	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		Class<?> clazz = findLoadedClass(name);
		if (clazz == null) {// 检查是否已经被加载
			byte[] classData = getClassData(name);
			if (classData == null) {
				throw new ClassNotFoundException();
			}
			// 将class的字节码数组转化成Class类的实例
			clazz = defineClass(name, classData, 0, classData.length);
		}
		return clazz;
	}

	/**
	 * 从指定URL下载class文件数据
	 */
	private byte[] getClassData(String name) {
		try {
			String path = className2Path(name);
			URL url = new URL(path);
			byte[] buffer = new byte[1024 * 4];
			int len = -1;

			InputStream is = url.openStream();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();

			while ((len = is.read(buffer)) != -1) {
				baos.write(buffer, 0, len);
			}
			is.close();
			baos.close();
			return baos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private String className2Path(String name) {
		return rootURL + "/" + name.replace(".", "/") + ".class";
	}
}
