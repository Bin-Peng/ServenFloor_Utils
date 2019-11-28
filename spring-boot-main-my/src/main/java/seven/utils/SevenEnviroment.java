package seven.utils;

import org.springframework.web.context.support.StandardServletEnvironment;

import java.util.Set;

/**
 * 自定义环境，支持多profile公用同一套组装类
 * 说明  假设 类名上profile值为 @Profile("front") ,配置写入时，为spring.proflies = front-cc
 * 则 默认为匹配到 @Profile("front") ，装载对应的类bean实例
 * @author pengbin
 */
public class SevenEnviroment extends StandardServletEnvironment {

    @Override
    protected boolean isProfileActive(String profile) {
        validateProfile(profile);
        Set<String> currentActiveProfiles = doGetActiveProfiles();
        for(String currentActive: currentActiveProfiles){
            //如果当前激活的profile,例如front-cc 匹配到front，则使front组装类生效
            if(currentActive.startsWith(profile)){
                return true;
            }
        }


        return currentActiveProfiles.isEmpty() && doGetDefaultProfiles().contains(profile);
    }
}
