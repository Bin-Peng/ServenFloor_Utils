package seven.utils.service.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import seven.utils.context.RunContext;
import seven.utils.model.table.AuthProcessControl;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * ClassName: AbstractAuthService <br/>
 * Description: <br/>
 * date: 2019/11/30 13:22 <br/>
 *
 * @author pengbin <br/>
 * @since JDK 1.8
 */
public abstract class AbstractAuthService<T, O> implements AuthService {

    @Override
    public void execute(AuthProcessControl authProcessControl) {
        //将请求map转换为序列化
        T requestDTO = fromJson(JSONObject.toJSONString(RunContext.getDataArea().getInput()), this.getClass(), 0);

        O output = doExecute(requestDTO, authProcessControl);
        // 反序列化
        Map<String, Object> input = JSONObject.parseObject(JSONObject.toJSONString(output), Map.class);
        RunContext.getDataArea().getInput().putAll(input);
    }

    /**
     * 授权控制入口
     *
     * @param requestDTO         请求参数模型
     * @param authProcessControl 授权控制规则
     */

    protected abstract O doExecute(T requestDTO, AuthProcessControl authProcessControl);


    /**
     * @param clazz 需要获取泛型类型的类
     * @param no    需要获取第几个泛型，第一个为 0
     * @return 反格式化后的对象，失败则为 null
     */
    public <T> T fromJson(String json, Class<?> clazz, int no) {
        Type entityType = ((ParameterizedType) clazz.getGenericSuperclass()).getActualTypeArguments()[no];
        TypeReference typeReference = new TypeReference<Object>() {
        };
        T obj = null;
        try {
            Field field = TypeReference.class.getDeclaredField("_type");
            field.setAccessible(true);
            field.set(typeReference, entityType);
            obj = (T) JSON.parseObject(json, typeReference);
        } catch (Exception ignored) {
        }
        return obj;
    }

//    public static void main(String[] args) {
//        AuthServiceImpl2DTO authServiceImpl2DTO = new AuthServiceImpl2DTO();
//        authServiceImpl2DTO.setTest1("output1");
//        authServiceImpl2DTO.setTest2("output2");
//        List list = new ArrayList<>();
//        list.add("outputList");
//        authServiceImpl2DTO.setStringList(list);
//
//        Map<String, Object> output = JSONObject.parseObject(JSONObject.toJSONString(authServiceImpl2DTO), Map.class);
//
//        AuthServiceImpl2DTO authServiceImpl2DTOback = JSONObject.parseObject(JSONObject.toJSONString(output),  AuthServiceImpl2DTO.class);
//
//
//        AuthServiceImpl2DTO authServiceImpl1DTO = new AuthServiceImpl2DTO();
//        authServiceImpl1DTO.setTest1("input1");
//        authServiceImpl1DTO.setTest2("input2");
//        List list2 = new ArrayList<>();
//        list2.add("inputList");
//
//        Map<String, Object> inputput = JSONObject.parseObject(JSONObject.toJSONString(authServiceImpl2DTO), Map.class);
//        inputput.put("key3", new AuthServiceImpl1DTO());
//        inputput.put("key4","value4");
//
//        inputput.putAll(output);
//        System.out.println(JSONObject.toJSONString(inputput));
//
//    }
}