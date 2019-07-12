#!/usr/bin/env bash

#Tips：
# for those who like compile source code and run under linux environment, you can quick run using command with '-d' option
#    example:xxxx.sh startup -d
#    where -d will load executable jar from target directory and config files from ./src/main/resources/config by default
#    see the function is get_mainjar_debug()

# exit if meet any error
set -e

# 转换路径保证在什么目录都可以执行
# convert relative path to absolute path 
bin='dirname "$0"'
APP_HOME='cd "$bin"/..;pwd'
PG_NAME='basename "$0"'


# set java options
JAVA_OPTS="${JAVA_OPTS} -Xms1024m -Xmx1024m"

#设置profiles_active
# export config center address
if [ -z "${SPRINT_PROFILES_ACTIVE}" ];then
   echo "Error:SPRINT_PROFILES_ACTIVE is not set,must be dev,test or prod."
   exit 1
fi

#自动加载环境变量文件
#read environment parameter and export , for easy debug usage
CONF_ENV_FILE="$APP_HOME/bin/config-${SPRINT_PROFILES_ACTIVE}.env"
source "$CONF_ENV_FILE"

function print_usage(){
        echo " "
	echo "Usage:$PG_NAME [parameters]"
	echo " Required parameter is one of:"
	echo "    start     start $PG_NAME"
	echo "    stop      stop  $PG_NAME"
	echo "    status    show status of $PG_NAME"
	echo "    "
	echo " Optional parameters"
	echo "    -d    for debug usage,which load executable jar from target folder"
	echo ""

}
if [ $# = 0 ];then
        print_usage
	exit
fi

# 自动加载在应用目录下的可执行jar包
# auto find the executable jar with version
function get_mainjar()
{
    CONF_PATH="$APP_HOME"/config/
    for jar in "$APP_HOME"/*.jar
    do
            RPG_NAME=${jar}
    done
}

#自动加载需要调试的可执行jar包
# auto find the executable jar with version
function get_mainjar_debug()
{
    CONF_PATH="$APP_HOME"/src/main/resources/config
    for jar in "$APP_HOME"/target/*.jar
    do
            RPG_NAME=${jar}
    done
}


# 搜索在应用目录下的jar并加到 classpath目录下
# scan jar lib under given directories and append to classpath
function load_classpath()
{
        for jar in "$APP_HOME"/lib/*.jar
	        do CLASSPATH=${CLASSPATH}:${jar}
        done

	for jar in "$APP_HOME"/target/*.jar
	        do CLASSPATH=${CLASSPATH}:${jar}
	done

	for jar in "$APP_HOME"/target/lib/*.jar
	        do CLASSPATH=${CLASSPATH}:${jar}
	done	
}




function load_java()
{
    if [[ ${DEBUG} == 'false' ]]; then
            get_mainjar
    else 
            get_mainjar_debug
    fi


    # some java parameters
    if [  "$JAVA_HOME" != "" ]; then
             # echo "run java in $JAVA_HOME"
	     JAVA_HOME=$JAVA_HOME
    else
             JAVA_HOME=$(readlink -f /usr/bin/java | sed "s:bin/java::")
    fi


    if [ "$JAVA_HOME" = "" ]; then
            echo "Error: JAVA_HOME is not set."
	    exit 1
    fi

    load_classpath


    # http running port value, 8080 by default is not set
    SERVER_PORT="${SERVER_PORT:-8080}"


    # app running environment, vm by default if not set
    RUN_ENV="${RUN_ENV:-vm}"

    # print out env properties
    echo \$SPRING_PROFILES_ACTIVE=${SPRING_PROFILES_ACTIVE}
    echo \$LOG_HOME=$LOG_HOME
    echo \$JAVA_OPTS="${JAVA_OPTS}"
    echo \$DEBUG=$DEBUG
    echo \$RPG_NAME=$RPG_NAME
    echo \$JAVA_HOME=$JAVA_HOME
    echo \$CLASS_PATH=$CLASSPATH
    echo \$spring.config.location=$CONF_PATH
    echo \$logging.config=$CONFI_PATH/log4j2-$SPRING_PROFILES_ACTIVE.xml
    echo \$server.port="${SERVER_PORT}"
    echo \$RUN_ENV=$RUN_ENV
}




function start(){
      
        load_java
	RUNNING='ps -ef|grep $RPG_NAME|grep -v grep|awk '{print $2}''
	if [ -n "$RUNNING" ]; then
	       echo "$RPG_NAME is running! $RUNNING"
	else 
	    if [ "$RUN_ENV" == "docker" ];then #the ${RUN_ENV} is defined by container template
	      echo "App is running in docker as frontend mode"
	      echo "$JAVA_HOME/bin/java $JAVA_OPTS -jar $RPG_NAME --spring.config.location=$CONF_PATH --logging.config=$CONF_PATH/log4j2-$SPRING_PROFILES_ACTIVE.xml ..... "
	      exec $JAVA_HOME/bin/java $JAVA_OPTS -jar $RPG_NAME --server.port=${SERVER_PORT}  --spring.config.location=$CONF_PATH --logging.config=$CONF_PATH/log4j2-$SPRING_PROFILES_ACTIVE.xml 2>&1
	    else                #the ${RUN_ENV} is defined by container template
	      echo "App is running in virtual machine as frontend mode"
	      echo "nohup $JAVA_HOME/bin/java $JAVA_OPTS -jar $RPG_NAME --spring.config.location=$CONF_PATH --logging.config=$CONF_PATH/log4j2-$SPRING_PROFILES_ACTIVE.xml &..... "
	      exec nohup $JAVA_HOME/bin/java $JAVA_OPTS -jar $RPG_NAME --spring.config.location=$CONF_PATH --logging.config=$CONF_PATH/log4j2-$SPRING_PROFILES_ACTIVE.xml  > /dev/null 2>&1 &
	    fi
	sleep 2s
	        if [ $? -eq 0]; then
		 echo "$RPG_NAME start success"
		else
		 echo "$RPG_NAME start failed"
		 exit 1
		fi
	fi
}

function status(){
        load_java

	processid='pgrep -f "$RPG_NAME"'
	if [ $processid ]; then
	       echo "$RPG_NAME is running as process $processid"
	else
	       echo "$RPG_NAME is not running."
	fi
}


function stop(){

        load_java

	echo " stopping $RPG_NAME..."
	pkill -f "$RPG_NAME"
	echo "$RPG_NAME is stopped!"

}



ARGS='getopt -o d -lserver.port: -- "$@"'

if [ $? != 0 ]; then
    print_usage
	exit 1;
fi

eval set -- "$ARGS"

DEBUG='false'

while true; do
    case "$1" in
	   -d) DEBUG='true'; shift;;
	   --server.port)
	   SERVER_PORT="$2";
	   shift 2;;
	   --) shift;;
	   start) start; break;;
	   status) status; break;;
	   stop) stop; break;;
	   *) echo "Error: unexpected option $1..."
	       print_usage;exit 1;;
 esac
done

exit $?;









