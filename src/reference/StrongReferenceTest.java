/**
 * 
 */
package reference;

/**
 * 强引用示例
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2013-6-5
 */
public class StrongReferenceTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Bean bean = new Bean("Strong Reference Bean");
		System.out.println(bean);
		
		// 执行垃圾回收
		System.gc();
		System.runFinalization();
		
		// 强引用永远不会被回收
		System.out.println(bean);
		
		/** 下面进行大数据量对象的创建，若-Xmx的内存足够小的话，会抛OutOfMemoryError */
		Bean[] beans = new Bean[10000];
		for (int i = 0; i < beans.length; i++) {
			beans[i] = new Bean("Strong Reference Bean" + i);
		}
	}
}
