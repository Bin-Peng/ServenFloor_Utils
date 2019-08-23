package seven.utils.service.api;

/**
 * 响应码映射表
 * Created by moche_000 on 2019/8/21.
 */
public interface AuthRespCodeMappingService {


    /**
     * 错误码转换，如果没有配置映射，则按照内部错误码返回
     * @param errorCode
     * @param channelId
     * @return 响应码
     */
    String responseCodeTransform(String errorCode, String channelId);

    /**
     * 根据内部错误码获取细化响应码
     * @param errorCode 内部错误码
     * @return 细化响应码
     */
    String queryDetailCode(String errorCode);


    /**
     * 根据内部错误码获取两位内部响应码
     * @param errorCode 内部错误码
     * @return 两位内部响应码
     */
    String queryInternalRespCode(String errorCode);
}
