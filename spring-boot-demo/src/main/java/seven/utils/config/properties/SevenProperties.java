package seven.utils.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import seven.utils.config.netty.TcpClientConfig;
import seven.utils.config.netty.TcpServerConfig;
import seven.utils.config.netty.TopicProperties;

import java.util.List;

/**
 * 读取配置文件
 * 支持yaml  properties
 * 默认读取bootstrap.properties  bootstrap.yml
 * Created by moche_000 on 2019/8/26.
 */
@ConfigurationProperties(prefix = "seven.utils")
public class SevenProperties {
    private TcpClientConfig client;

    private TcpServerConfig sevenServer;

    private TopicProperties topic;

    private List<TcpClientConfig> clientList;


    public List<TcpClientConfig> getClientList() {
        return clientList;
    }

    public void setClientList(List<TcpClientConfig> clientList) {
        this.clientList = clientList;
    }

    public TcpClientConfig getClient() {
        return client;
    }

    public void setClient(TcpClientConfig client) {
        this.client = client;
    }

    public TcpServerConfig getSevenServer() {
        return sevenServer;
    }

    public void setSevenServer(TcpServerConfig sevenServer) {
        this.sevenServer = sevenServer;
    }

    public TopicProperties getTopic() {
        return topic;
    }

    public void setTopic(TopicProperties topic) {
        this.topic = topic;
    }
}
