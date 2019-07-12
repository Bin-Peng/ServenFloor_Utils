#!/bin/sh
APP_MAIN=com.cucpay.tradeportal.MainApp

tradePortalPID=0

getTradeProtalPID(){
    javaps=`$JAVA_HOME/bin/jps -l | grep $APP_MAIN`
    if [ -n "$javaps" ]; then
        tradePortalPID=`echo $javaps | awk '{print $1}'`
    else
        tradePortalPID=0
    fi
}

# ֹͣJavaӦ�ó���
# ------------------------------------------------------------------------------------------------------
# 1������getTradeProtalPID()������ˢ��$tradePortalPIDȫ�ֱ���
# 2���������Ѿ�������$tradePortalPID������0������ʼִ��ֹͣ���������������ʾ����δ����
# 3��ʹ��[kill -9 PID]����ǿ��ɱ������
# 4��ʹ��[$?]��ȡ��һ������ķ���ֵ������Ϊ0����ʾ������ֹͣ���У����ӡSuccess����֮��ӡFailed
# 5��Ϊ��ֹJava����������Σ����������˷�����������̵Ĺ��ܣ�ͨ���ݹ����shutdown()�����ķ�ʽ������kill
# ע�⣺Shell����У�[$?]��ʾ��һ�����������һ�������ķ���ֵ
# ------------------------------------------------------------------------------------------------------
shutdown(){
    getTradeProtalPID
    echo "==============================================================================================="
    if [ $tradePortalPID -ne 0 ]; then
        echo -n "Stopping $APP_MAIN(PID=$tradePortalPID)..."
        kill -9 $tradePortalPID
        if [ $? -eq 0 ]; then
            echo "[Success]"
            echo "==============================================================================================="
        else
            echo "[Failed]"
            echo "==============================================================================================="
        fi
        getTradeProtalPID
        if [ $tradePortalPID -ne 0 ]; then
            shutdown
        fi
    else
        echo "$APP_MAIN is not running"
        echo "==============================================================================================="
    fi
}