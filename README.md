# iSquareServer


Server setup
============


    export JETTY_HOME=/opt/jetty
    export JETTY_BASE=/opt/jetty-http2
    mkdir $JETTY_BASE
    cd $JETTY_BASE
    
    java -jar $JETTY_HOME/start.jar --add-to-startd=http,https,deploy

It requires JDK 1.8


now add HTTP2

    java -jar $JETTY_HOME/start.jar --add-to-startd=http2
    
if you have alnp error, ask me how to fix it


Start
=====
java -jar $JETTY_HOME/start.jar
