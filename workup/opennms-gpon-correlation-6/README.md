# Gpon Correlation Examples


THIS VERSION CONTAINS DROOLS EXAMPLE WITH RULE TO CREATE SITUATION ONLY WHEN ANOUGH ALARMS IN MEMORY


THIS VERSION HAS NO <PRI> IN LOGS

This simple proof of concept shows how OpenNMS parent child relationships can be used to represent a gpon network with passive splices and end points.

syslog parsing is provided to parse calex devices

[minimal-minion-kafka](../opennms-gpon-correlation-2/minimal-minion-kafka-simple) is a simple example using netsnmp docker containers to represent end points. 
