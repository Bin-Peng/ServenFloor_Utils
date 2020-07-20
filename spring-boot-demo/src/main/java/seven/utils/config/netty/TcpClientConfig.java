package seven.utils.config.netty;

/**
 * 读取配置文件
 * Created by moche_000 on 2019/8/26.
 */
public class TcpClientConfig {
    private String configName = "tcpClientConfig";
    private String host = "localhost";
    private int port = 20990;


    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
