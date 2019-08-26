package seven.utils.config.list;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by moche_000 on 2019/8/26.
 */
@Component
@Order(99)
public class PayMsgStorage extends MsgStorageAdaptor {
    private static final Logger logger = LoggerFactory.getLogger(PayMsgStorage.class);

    @Override
    public void storageA(Object msg) {
        logger.info("storageA");
    }

}
