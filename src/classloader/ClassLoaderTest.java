/**
 * 
 */
package classloader;

/**
 * ClassLoader������ԭ��
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2013-5-27
 */
public class ClassLoaderTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		 * ��������
		 * 
		 * class sun.misc.Launcher$AppClassLoader
		 * class sun.misc.Launcher$ExtClassLoader
		 * null
		 * 
		 * �Գ�����֤��
		 * ClassLoaderTest�����������AppClassLoader��
		 * AppClassLoader�����������ExtClassLoader
		 * ExtClassLoader�����������BootstrapClassLoader����������Java��
		 */
		ClassLoader loader = ClassLoaderTest.class.getClassLoader();
		while (loader != null) {
			System.out.println(loader.getClass());
			loader = loader.getParent();
		}
		/*
		 * ������BootstrapClassLoader������ClassLoaderTest�������ַ�ʽ��
		 * 
		 * 1����JVM�����-Xbootclasspath������ָ��BootstrapClassLoader�������·������׷�������Լ���jar����
		 * 2����class�ļ��ŵ�JAVA_HOME/jre/classesĿ¼�¡�
		 */
		System.out.println(loader);
	}
}