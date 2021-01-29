package com.suave.msm.constant;

import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Suave
 * @date 2021/1/29 5:05 下午
 */
@Component
public class MsmConstant implements InitializingBean {
    @Value("${aliyun.oss.file.keyId}")
    private String keyId;

    @Value("${aliyun.oss.file.keySecret}")
    private String keySecret;
    public static String KEY_ID;
    public static String KEY_SECRET;

    /**
     * Invoked by the containing {@code BeanFactory} after it has set all bean properties
     * and satisfied {@link BeanFactoryAware}, {@code ApplicationContextAware} etc.
     * <p>This method allows the bean instance to perform validation of its overall
     * configuration and final initialization when all bean properties have been set.
     *
     * @throws Exception in the event of misconfiguration (such as failure to set an
     *                   essential property) or if initialization fails for any other reason
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        KEY_ID = keyId;
        KEY_SECRET = keySecret;
    }
}
