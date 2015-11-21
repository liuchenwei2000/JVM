/**
 * 
 */
package reference;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * WeakReference示例
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2013-6-5
 */
public class WeakReferenceTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 通过WeakReference的构造方法，将需要用弱引用指向的对象(Bean)包装起来
		WeakReference<Bean> ref = new WeakReference<Bean>(new Bean("Weak Reference Bean"));
		// 需要使用对象的时候，调用get()方法获取，当对象未被回收时该方法会返回该对象的强引用。
		System.out.println(ref.get());

		// 执行垃圾回收
		System.gc();
		System.runFinalization();

		// 弱引用指向的对象一旦运行GC就会被回收，所以get()返回null
		System.out.println(ref.get());
		
		// 可传入一个ReferenceQueue对象到WeakReference，当引用对象被标示为可回收时，isEnqueued返回true。
		ReferenceQueue<Bean> refQueue = new ReferenceQueue<Bean>();
		WeakReference<Bean> ref2 = new WeakReference<Bean>(new Bean("Weak Reference Bean2"),refQueue);
		System.out.println(ref2.get());
		System.out.println(ref2.isEnqueued());
		// 开始垃圾回收
		System.gc();
		System.out.println(ref2.isEnqueued());
		
		/** 下面进行大数据量对象的创建  */
		Reference<Bean>[] beans = new WeakReference[10000];
		for (int i = 0; i < beans.length; i++) {
			beans[i] = new WeakReference(new Bean("Weak Reference Bean" + i));
		}
		// 若-Xmx的内存足够小的话，下面会输出null，因为beans[1]已被回收
		System.out.println(beans[1].get());
	}
}
