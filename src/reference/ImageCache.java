/**
 * 
 */
package reference;

import java.awt.Image;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

/**
 * Í¼Æ¬»º´æ
 * 
 * @author Áõ³¿Î°
 * 
 * ´´½¨ÈÕÆÚ£º2013-6-6
 */
public class ImageCache {

	private Map<String, SoftReference<Image>> cache = new HashMap<String, SoftReference<Image>>();

	public void store(String key, Image image) {
		cache.put(key, new SoftReference<Image>(image));
	}

	public Image load(String key) {
		SoftReference<Image> ref = cache.get(key);
		if (ref != null) {
			return ref.get();
		}
		return null;
	}
}