package seven.utils.facade;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import seven.utils.model.common.AuthRequetHeader;
import seven.utils.model.table.AuthProcessControl;
import seven.utils.service.AuthParaService;

import java.util.List;
import java.util.Map;

/**
 * 逻辑处理层
 * Created by moche_000 on 2019/8/16.
 */
@Component
public class OnlineFacade {
    private static Logger logger = LoggerFactory.getLogger(OnlineFacade.class);

    @Autowired
    private AuthParaService authParaService;

    @Transactional
    public Map<String, Object> process(Map<String, Object> request) {
        //根据交易码，获取授权检查组件，并按照顺序执行
        String authTrxnCode = String.valueOf(request.get(AuthRequetHeader.AUTH_TRXN_CODE));
        List<AuthProcessControl> authProcessControlList = authParaService.queryAuthProcessControlList(authTrxnCode);

        //默认的gotoStep值，要求配置表中的step值都大于等于0
        List<String> authProcessLogs = Lists.newArrayList();

        int gotoStep = -1;
        for (AuthProcessControl authProcessControl : authProcessControlList) {
            //只要当前步骤小于gotoStep跳过，大于等于gotoStep就执行，避免还需要重置gotoStep
            if (authProcessControl.getPcStep() < gotoStep) {
                continue;
            }
            try{
                //执行授权检查组件
                AuthServicd
            }

        }
        return null;
    }
}
