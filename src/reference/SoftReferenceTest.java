/**
 * 
 */
package reference;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;

/**
 * SoftReferenceʾ��
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2013-6-5
 */
public class SoftReferenceTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// ͨ��SoftReference�Ĺ��췽��������Ҫ��������ָ��Ķ���(Bean)��װ����
		SoftReference<Bean> ref = new SoftReference<Bean>(new Bean("Soft Reference Bean"));
		// ��Ҫʹ�ö����ʱ�򣬵���get()������ȡ��������δ������ʱ�÷����᷵�ظö����ǿ���á�
		System.out.println(ref.get());
		
		// ִ����������
		System.gc();
		System.runFinalization();
		
		// �ڴ���㣬������ָ��Ķ��󲻻ᱻ����
		System.out.println(ref.get());
		
		/** ������д�����������Ĵ���  */
		Reference<Bean>[] beans = new SoftReference[10000];
		for (int i = 0; i < beans.length; i++) {
			beans[i] = new SoftReference(new Bean("Soft Reference Bean" + i));
		}
		// ��-Xmx���ڴ��㹻С�Ļ�����������null����Ϊbeans[1]�ѱ�����
		System.out.println(beans[1].get());
	}
}
