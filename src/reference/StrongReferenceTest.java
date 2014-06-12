/**
 * 
 */
package reference;

/**
 * ǿ����ʾ��
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2013-6-5
 */
public class StrongReferenceTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Bean bean = new Bean("Strong Reference Bean");
		System.out.println(bean);
		
		// ִ����������
		System.gc();
		System.runFinalization();
		
		// ǿ������Զ���ᱻ����
		System.out.println(bean);
		
		/** ������д�����������Ĵ�������-Xmx���ڴ��㹻С�Ļ�������OutOfMemoryError */
		Bean[] beans = new Bean[10000];
		for (int i = 0; i < beans.length; i++) {
			beans[i] = new Bean("Strong Reference Bean" + i);
		}
	}
}
