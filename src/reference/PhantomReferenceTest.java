/**
 * 
 */
package reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;

/**
 * PhantomReferenceʾ��
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2013-6-5
 */
public class PhantomReferenceTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// PhantomReference���÷��������ú������ò�ͬ������Ҫ����һ��ReferenceQueue����
		// �������������ö����������պ������ûᱻ��ӵ���������С�
		ReferenceQueue<Bean> refQueue = new ReferenceQueue<Bean>();
		PhantomReference<Bean> ref = new PhantomReference<Bean>(new Bean("Phantom Reference Bean"),refQueue);
		// get()����ʼ�շ���null
		System.out.println(ref.get());
		
		// ��ʼ��������
		System.gc();
		System.runFinalization();
		// ���������true��˵�������ն�����������Ѿ������˶�����
		System.out.println(refQueue.poll() == ref);
		
		/** ������д�����������Ĵ�������-Xmx���ڴ��㹻С�Ļ�������OutOfMemoryError
		 * ��ΪPhantomReference���õĶ��󣬲����Զ������ڴ�����Զ���Ŀ��������  */
		Reference<Bean>[] beans = new PhantomReference[10000];
		for (int i = 0; i < beans.length; i++) {
			beans[i] = new PhantomReference(new Bean("Phantom Reference Bean" + i), refQueue);
		}
	}
}
