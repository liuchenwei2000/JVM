/**
 * 
 */
package classloader;

/**
 * JVM�ж�����class�Ƿ���ͬ����
 * <p>
 * JVM���ж�����class�Ƿ���ͬʱ������Ҫ�ж����������Ƿ���ͬ������Ҫ�ж��Ƿ���ͬһ���������ʵ�����صġ�
 * ֻ������ͬʱ���������£�JVM����Ϊ������class����ͬ�ģ���������class��ͬһ��class�ֽ��룬
 * �����������ͬ��classloaderʵ�������أ�JVMҲ����Ϊ������������ͬclass��
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2013-5-27
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