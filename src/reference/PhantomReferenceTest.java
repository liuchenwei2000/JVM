/**
 * 
 */
package reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;

/**
 * PhantomReference示例
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2013-6-5
 */
public class PhantomReferenceTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// PhantomReference的用法与软引用和弱引用不同，它需要传入一个ReferenceQueue对象。
		// 当虚引用所引用对象被垃圾回收后，虚引用会被添加到这个队列中。
		ReferenceQueue<Bean> refQueue = new ReferenceQueue<Bean>();
		PhantomReference<Bean> ref = new PhantomReference<Bean>(new Bean("Phantom Reference Bean"),refQueue);
		// get()方法始终返回null
		System.out.println(ref.get());
		
		// 开始垃圾回收
		System.gc();
		System.runFinalization();
		// 下面输出是true，说明被回收对象的虚引用已经加入了队列中
		System.out.println(refQueue.poll() == ref);
		
		/** 下面进行大数据量对象的创建，若-Xmx的内存足够小的话，会抛OutOfMemoryError
		 * 因为PhantomReference引用的对象，不会自动根据内存情况自动对目标对象回收  */
		Reference<Bean>[] beans = new PhantomReference[10000];
		for (int i = 0; i < beans.length; i++) {
			beans[i] = new PhantomReference(new Bean("Phantom Reference Bean" + i), refQueue);
		}
	}
}
