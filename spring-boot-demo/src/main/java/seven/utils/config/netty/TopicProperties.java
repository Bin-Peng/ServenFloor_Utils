package seven.utils.config.netty;

/**
 * 复杂配置样例
 * Created by moche_000 on 2019/8/26.
 */
public class TopicProperties {
    private Sema sema = new Sema();


    public Sema getSema() {
        return sema;
    }

    public void setSema(Sema sema) {
        this.sema = sema;
    }

    class Sema{
        private String req;
        private String res;

        public String getReq() {
            return req;
        }

        public void setReq(String req) {
            this.req = req;
        }

        public String getRes() {
            return res;
        }

        public void setRes(String res) {
            this.res = res;
        }
    }
}
