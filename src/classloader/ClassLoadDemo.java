package classloader;

/**
 * Class���صĲ�ͬ��ʽ
 * <p>
 * ��JVM�������ʱ����Ҫ�����������裺װ�ء����ӡ���ʼ����
 * װ�ؾ����ҵ���Ӧ��class�ļ�������JVM��
 * ���ӷ���������һ����֤class�Ƿ���Ϲ��
 * �ڶ�����׼����Ϊ����������ڴ�ͬʱ����Ĭ�ϳ�ʼֵ��
 * �������ǽ��ͣ��ⲽ�ǿ�ѡ�ġ�
 * ��ʼ������ִ����ĳ�ʼ�������������������
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2013-5-26
 */
public class ClassLoadDemo {

	/**
	 * @param args
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) throws ClassNotFoundException {
		/*
		 * JVM��ʹ��Person���ClassLoader��Person��װ���ڴ棨���Person����δװ���ڴ�Ļ���
		 * ������Person����г�ʼ���� ������Person���Class����
		 */
		// ʲô�������
		Class<?> pclass1 = Person.class;

		/*
		 * JVM��ʹ�õ�ǰ�̵߳�ClassLoaderװ��ָ���࣬���ǲ����Ӳ���ʼ����
		 */
		// ʲô�������
		Class<?> pclass2 = Thread.currentThread().getContextClassLoader().loadClass("classloader.Person");

		/*
		 * JVM��ʹ�õ�������ʹ�õ�ClassLoaderװ��ָ���࣬���������ࡢ��ʼ���ࡣ
		 */
		// ��� initialize age in static block.
		Class<?> pclass3 = Class.forName("classloader.Person");

		/*
		 * object.getClass()���ض���object����ʱ������ָ�Ķ��������ࣨPerson��
		 */
		// ��� initialize age in static block.��ʹ��new��ʱ����ز���ʼ���ࣩ
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