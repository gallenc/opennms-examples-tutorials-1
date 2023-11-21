# node red mqtt example

to load mqtt plugin 
a) you can install the kar in the deploy directory
b) run mvn clean install to copy the kar to the deploy directory
c) install using maven command in OpeNMS

setting up firewall in virtuabox
sudo firewall-cmd --zone=public --permanent --add-port=8980/tcp
sudo firewall-cmd --zone=public --permanent --add-port=1880/tcp
sudo firewall-cmd --zone=public --permanent --add-port=9001/tcp
sudo firewall-cmd --zone=public --permanent --add-port=1883/tcp
sudo firewall-cmd --reload

ports for systems
opennms http://localhost:8980/opennms

node-red http://localhost:1880

node red dashboard http://localhost:1880/ui/

hivemq-mqtt-web-client http://localhost:80

set broker localhost 9001
subscribe to topic ioThinx_4510/write/#

publish to topic ioThinx_4510/write/45MR-2404-0Relay-02/relayStatus_final



influxdb 8086

eclipse mosquitto 
1883 default mqtt port
9001 default mqtt port for websockets