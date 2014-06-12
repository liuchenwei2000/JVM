/**
 * 
 */
package classloader;

/**
 * ClassLoader加载类原理
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2013-5-27
 */
public class ClassLoaderTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		 * 输出结果：
		 * 
		 * class sun.misc.Launcher$AppClassLoader
		 * class sun.misc.Launcher$ExtClassLoader
		 * null
		 * 
		 * 以程序验证了
		 * ClassLoaderTest的类加载器是AppClassLoader；
		 * AppClassLoader的类加载器是ExtClassLoader
		 * ExtClassLoader的类加载器是BootstrapClassLoader——它不是Java类
		 */
		ClassLoader loader = ClassLoaderTest.class.getClassLoader();
		while (loader != null) {
			System.out.println(loader.getClass());
			loader = loader.getParent();
		}
		/*
		 * 若想用BootstrapClassLoader来加载ClassLoaderTest，有两种方式：
		 * 
		 * 1，在JVM中添加-Xbootclasspath参数，指定BootstrapClassLoader加载类的路径，并追加我们自己的jar包。
		 * 2，将class文件放到JAVA_HOME/jre/classes目录下。
		 */
		System.out.println(loader);
	}
}