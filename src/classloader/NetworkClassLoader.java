/**
 * 
 */
package classloader;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;

/**
 * ����������class��ClassLoader
 * <p>
 * <li>1,�̳� java.lang.ClassLoader
 * <li>2,��д�����findClass����
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2013-5-27
 */
public class NetworkClassLoader extends ClassLoader {

	private String rootURL;

	public NetworkClassLoader(String rootUrl) {
		this.rootURL = rootUrl;
	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		Class<?> clazz = findLoadedClass(name);
		if (clazz == null) {// ����Ƿ��Ѿ�������
			byte[] classData = getClassData(name);
			if (classData == null) {
				throw new ClassNotFoundException();
			}
			// ��class���ֽ�������ת����Class���ʵ��
			clazz = defineClass(name, classData, 0, classData.length);
		}
		return clazz;
	}

	/**
	 * ��ָ��URL����class�ļ�����
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