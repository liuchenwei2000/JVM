/**
 * 
 */
package reference;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;

/**
 * SoftReference示例
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2013-6-5
 */
public class SoftReferenceTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 通过SoftReference的构造方法，将需要用弱引用指向的对象(Bean)包装起来
		SoftReference<Bean> ref = new SoftReference<Bean>(new Bean("Soft Reference Bean"));
		// 需要使用对象的时候，调用get()方法获取，当对象未被回收时该方法会返回该对象的强引用。
		System.out.println(ref.get());
		
		// 执行垃圾回收
		System.gc();
		System.runFinalization();
		
		// 内存充足，软引用指向的对象不会被回收
		System.out.println(ref.get());
		
		/** 下面进行大数据量对象的创建  */
		Reference<Bean>[] beans = new SoftReference[10000];
		for (int i = 0; i < beans.length; i++) {
			beans[i] = new SoftReference(new Bean("Soft Reference Bean" + i));
		}
		// 若-Xmx的内存足够小的话，下面会输出null，因为beans[1]已被回收
		System.out.println(beans[1].get());
	}
}
