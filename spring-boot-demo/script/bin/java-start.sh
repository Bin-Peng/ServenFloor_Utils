#!/bin/sh

# JDK·��
# JAVA_HOME="/usr/java/jdk1.6.0_31"

# JVM��������
# -server��һ��Ҫ��Ϊ��һ�����������CPUʱ���ܼ�
# -Xloggc����¼GC��־������д�ɾ���·������˱��������Ŀ¼��ִ�и�shell�ű�
JAVA_OPTS="-server -Xms2048m -Xmx2048m -Xloggc:/app/code/CucPayTradePortalLog/gc.log"

# Java������־���ڵ�Ŀ¼
APP_LOG=/app/code/CucPayTradePortalLog

# Java�����������ڵ�Ŀ¼����classes����һ��Ŀ¼
APP_HOME=/app/code/CucPayTradePortal

# Java������Ҳ����main(String[] args)������
APP_MAIN=com.cucpay.tradeportal.MainApp

# classpath����������ָ��libĿ¼�µ�����jar
CLASSPATH=$APP_HOME/classes
for tradePortalJar in "$APP_HOME"/lib/*.jar;
do
   CLASSPATH="$CLASSPATH":"$tradePortalJar"
done

# ��ʼ��ȫ�ֱ��������ڱ�ʶ����ǰ��ϵͳ��PID��0��ʾδ������
tradePortalPID=0

# ��ȡJavaӦ�õ�PID
# ------------------------------------------------------------------------------------------------------
# ˵����ͨ��JDK�Դ���jps�������Linux�е�grep�������׼ȷ���ҵ�JavaӦ�õ�PID
#       [jps -l]��ʾ��ʾJava�������������·��
#       awk������Էָ��PID��$1���֣���Java���������ƣ�$2���֣�
# ���ӣ�[$JAVA_HOME/bin/jps -l | grep $APP_MAIN]����ִ�У��ῴ��[5775 com.cucpay.tradeportal.MainApp]
# ���⣺�������Ҳ����ȡ�������PID-->[ps aux|grep java|grep $APP_MAIN|grep -v grep|awk '{print $2}']
# ------------------------------------------------------------------------------------------------------
getTradeProtalPID(){
    javaps=`$JAVA_HOME/bin/jps -l | grep $APP_MAIN`
    if [ -n "$javaps" ]; then
        tradePortalPID=`echo $javaps | awk '{print $1}'`
    else
        tradePortalPID=0
    fi
}

# ����JavaӦ�ó���
# ------------------------------------------------------------------------------------------------------
# 1������getTradeProtalPID()������ˢ��$tradePortalPIDȫ�ֱ���
# 2���������Ѿ�������$tradePortalPID������0��������ʾ����������
# 3��������δ����������ִ����������
# 4����������ִ�к��ٴε���getTradeProtalPID()����
# 5��������4ִ�к󣬳����PID������0�����ӡSuccess����֮��ӡFailed
# ע�⣺[echo -n]��ʾ��ӡ�ַ��󲻻���
# ע�⣺[nohup command > /path/nohup.log &]�ǽ���ҵ�����nohup.log����������������ýű�Ŀ¼�µ�nohup.out��
# ------------------------------------------------------------------------------------------------------
startup(){
    getTradeProtalPID
    echo "==============================================================================================="
    if [ $tradePortalPID -ne 0 ]; then
        echo "$APP_MAIN already started(PID=$tradePortalPID)"
        echo "==============================================================================================="
    else
        echo -n "Starting $APP_MAIN"
        nohup $JAVA_HOME/bin/java $JAVA_OPTS -classpath $CLASSPATH $APP_MAIN > $APP_LOG/nohup.log &
        getTradeProtalPID
        if [ $tradePortalPID -ne 0 ]; then
            echo "(PID=$tradePortalPID)...[Success]"
            echo "==============================================================================================="
        else
            echo "[Failed]"
            echo "==============================================================================================="
        fi
    fi
}