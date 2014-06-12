package classloader;

/**
 * Class加载的不同方式
 * <p>
 * 在JVM加载类的时候，需要经过三个步骤：装载、连接、初始化。
 * 装载就是找到相应的class文件，读入JVM。
 * 连接分三步，第一步验证class是否符合规格，
 * 第二步是准备，为类变量分配内存同时设置默认初始值，
 * 第三部是解释，这步是可选的。
 * 初始化就是执行类的初始化工作，诸如类变量。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2013-5-26
 */
public class ClassLoadDemo {

	/**
	 * @param args
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) throws ClassNotFoundException {
		/*
		 * JVM将使用Person类的ClassLoader将Person类装入内存（如果Person类尚未装入内存的话）
		 * 但不对Person类进行初始化， 仅返回Person类的Class对象。
		 */
		// 什么都不输出
		Class<?> pclass1 = Person.class;

		/*
		 * JVM将使用当前线程的ClassLoader装载指定类，但是不连接不初始化。
		 */
		// 什么都不输出
		Class<?> pclass2 = Thread.currentThread().getContextClassLoader().loadClass("classloader.Person");

		/*
		 * JVM将使用调用类所使用的ClassLoader装载指定类，并且连接类、初始化类。
		 */
		// 输出 initialize age in static block.
		Class<?> pclass3 = Class.forName("classloader.Person");

		/*
		 * object.getClass()返回对象object运行时真正所指的对象所属类（Person）
		 */
		// 输出 initialize age in static block.（使用new的时候加载并初始化类）
		Object object = new Person();
		Class<?> pclass4 = object.getClass();
	}
}

class Person {
	
	private static int age;
	
	static {
		age = 20;
		System.out.println("initialize age in static block." + age);
	}
}