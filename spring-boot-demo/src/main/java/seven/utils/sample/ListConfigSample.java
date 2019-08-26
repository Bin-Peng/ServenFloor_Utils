package seven.utils.sample;

import org.springframework.beans.factory.annotation.Autowired;
import seven.utils.config.list.MsgStorage;

import java.util.List;

/**
 * Created by moche_000 on 2019/8/26.
 */
public class ListConfigSample {

    @Autowired(required = false)
    private List<MsgStorage> msgStorageList;

    public void msgStorage(Object msg){
        if(msgStorageList != null){
            for (MsgStorage msgStorage: msgStorageList){
                try{
                    msgStorage.storageA(msg);
                }catch (Exception e){
                    throw e;
                }
            }
        }
    }
}
