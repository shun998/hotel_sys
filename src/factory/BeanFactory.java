package factory;

/**
 * 创建实例的工程类
 */
import java.util.ResourceBundle;

public class BeanFactory {

	private static ResourceBundle bundle;
	static {
		bundle = ResourceBundle.getBundle("instance");
	}

	public static <T> T getInstance(String key, Class<T> clazz) {
		try {
			String className = bundle.getString(key);
			return (T) Class.forName(className).newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
}
