package seven.utils.init;

import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationListener;
import seven.utils.context.AppContext;
import seven.utils.dao.AuthProcessControlMapper;
import seven.utils.dao.AuthProcessMapper1;
import seven.utils.dao.AuthProcessMapper2;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author pengbin
 * 内部实例注册器  可以通过指定参数获取对应实现实例
 * 主要用于提供数据库操作实例对象
 */
public class MapperRegistry implements ApplicationListener<ApplicationPreparedEvent> {

    private final ConcurrentHashMap<String, AuthProcessControlMapper> mappers = new ConcurrentHashMap<>();

    public AuthProcessControlMapper select(String routeType){
        if(!mappers.containsKey(routeType)){
            throw new IllegalArgumentException("无法识别的类型");
        }
        return mappers.get(routeType);
    }

    private void init(){
        register("mapper1", AppContext.getBean(AuthProcessMapper1.class));
        register("mapper2", AppContext.getBean(AuthProcessMapper2.class));
    }

    private void register(String name, AuthProcessControlMapper mapper){
        this.mappers.put(name, mapper);
    }
    @Override
    public void onApplicationEvent(ApplicationPreparedEvent applicationPreparedEvent) {
        init();
    }
}
