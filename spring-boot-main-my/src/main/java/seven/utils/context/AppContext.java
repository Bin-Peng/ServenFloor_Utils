package seven.utils.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * Created by moche_000 on 2019/8/15.
 * 通过javacontext  直接获取javabean实例
 */
@Component
public class AppContext implements ApplicationContextAware {
    private static ApplicationContext context;

    /**
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (context == null) {
            context = applicationContext;
        }
    }

    /**
     * 获取spring容器
     *
     * @return
     */
    public static ApplicationContext getContext() {
        Assert.notNull(context, "ApplicationContext not init yet");
        return context;
    }

    /**
     * 按类名获取bean
     */
    public static Object getBean(String beanName) {
        return getContext().getBean(beanName);
    }

    /**
     * 按类型获取bean
     */
    public static <T> T getBean(Class<T> clazz) {
        return getContext().getBean(clazz);
    }

    /**
     * 按类名和类型获取bean
     */
    public static <T> T getBean(String beanName, Class<T> clazz) {
        return getContext().getBean(beanName, clazz);
    }


    /**
     * 判断是否包含某个名字的bean
     * @param beanName
     * @return
     */
    public static boolean containsBean(String beanName) {
        return getContext().containsBean(beanName);
    }

}
