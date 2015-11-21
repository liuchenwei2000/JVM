/**
 * 
 */
package classloader;

/**
 * 加载类的一些异常
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2013-5-26
 */
public class LoadClassException {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws ClassNotFoundException {
		// java.lang.ClassNotFoundException: a.b
		Class.forName("a.b");
		// java.lang.ExceptionInInitializerError
		new Foo();
	}
}

class Foo {
	
	private static String s;
	
	static {
		s.toString();
	}
}
