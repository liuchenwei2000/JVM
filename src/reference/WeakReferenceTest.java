/**
 * 
 */
package reference;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * WeakReferenceʾ��
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2013-6-5
 */
public class WeakReferenceTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// ͨ��WeakReference�Ĺ��췽��������Ҫ��������ָ��Ķ���(Bean)��װ����
		WeakReference<Bean> ref = new WeakReference<Bean>(new Bean("Weak Reference Bean"));
		// ��Ҫʹ�ö����ʱ�򣬵���get()������ȡ��������δ������ʱ�÷����᷵�ظö����ǿ���á�
		System.out.println(ref.get());

		// ִ����������
		System.gc();
		System.runFinalization();

		// ������ָ��Ķ���һ������GC�ͻᱻ���գ�����get()����null
		System.out.println(ref.get());
		
		// �ɴ���һ��ReferenceQueue����WeakReference�������ö��󱻱�ʾΪ�ɻ���ʱ��isEnqueued����true��
		ReferenceQueue<Bean> refQueue = new ReferenceQueue<Bean>();
		WeakReference<Bean> ref2 = new WeakReference<Bean>(new Bean("Weak Reference Bean2"),refQueue);
		System.out.println(ref2.get());
		System.out.println(ref2.isEnqueued());
		// ��ʼ��������
		System.gc();
		System.out.println(ref2.isEnqueued());
		
		/** ������д�����������Ĵ���  */
		Reference<Bean>[] beans = new WeakReference[10000];
		for (int i = 0; i < beans.length; i++) {
			beans[i] = new WeakReference(new Bean("Weak Reference Bean" + i));
		}
		// ��-Xmx���ڴ��㹻С�Ļ�����������null����Ϊbeans[1]�ѱ�����
		System.out.println(beans[1].get());
	}
}