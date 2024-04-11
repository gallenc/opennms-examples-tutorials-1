# notes

## event translator

ideally we would create  new event from an existing event but the the EventTranslatorConfigFactory.java sets the  event source to the event translator - so we cannot write reentrant code to create events after the first translaton

```
-- finding nodes from asset number
-- SELECT a.nodeid FROM assets a WHERE a.assetnumber = '212064';

-- finding parent id of node
-- SELECT n.nodeparentid FROM node n WHERE n.nodeid = 838;
```

### secondary node
```
-- get node id of secondary node from asset number
-- SELECT n.nodeparentid FROM node n WHERE n.nodeid IN ( SELECT a.nodeid FROM assets a WHERE a.assetnumber = '212064' AND a.displaycategory='ONT');

-- get node label of secondary node from asset number
-- SELECT n.nodelabel FROM node n WHERE n.nodeid IN (SELECT n.nodeparentid FROM node n WHERE n.nodeid IN ( SELECT a.nodeid FROM assets a WHERE a.assetnumber = '212064' AND a.displaycategory='ONT') );

 counts the child nodes of secondary node node
 --SELECT COUNT(*) FROM node n WHERE n.nodeparentid IN (SELECT n.nodeparentid FROM node n WHERE n.nodeid IN ( SELECT a.nodeid FROM assets a WHERE a.displaycategory='ONT' AND a.assetnumber = '212064' ) );
 
counts same child alarms of secondary node in prepared statement 
SELECT COUNT(*) FROM alarms al WHERE al.reductionkey LIKE CONCAT( '%:','high-laser-bias')  AND al.nodeid IN (SELECT n.nodeparentid FROM node n WHERE n.nodeid IN ( SELECT a.nodeid FROM assets a WHERE a.displaycategory='ONT' and a.assetnumber = '212064' ) );
```

### primary node
```
-- get node label of primary node from asset number
SELECT n.nodelabel FROM node n WHERE n.nodeid IN ( SELECT n.nodeparentid FROM node n WHERE n.nodeid IN ( SELECT n.nodeparentid FROM node n WHERE n.nodeid IN ( SELECT a.nodeid FROM assets a WHERE a.displaycategory='ONT' AND a.assetnumber = '212064') ) );

-- get node id of primary node from asset number
 SELECT n.nodeparentid FROM node n WHERE n.nodeid IN ( SELECT n.nodeparentid FROM node n WHERE n.nodeid IN ( SELECT a.nodeid FROM assets a WHERE a.displaycategory='ONT' AND a.assetnumber = '212064') );
 
 counts the child nodes of primary node
 --SELECT COUNT(*) FROM node n WHERE n.nodeparentid IN ( SELECT n.nodeparentid FROM node n WHERE n.nodeid IN ( SELECT a.nodeid FROM assets a WHERE a.displaycategory='ONT' AND a.assetnumber ='61180' ) );

counts the same alarms in child nodes of primary node
SELECT COUNT(*) FROM alarms al WHERE al.reductionkey LIKE '%:high-laser-bias'  AND al.nodeid IN ( SELECT n.nodeid FROM node n WHERE n.nodeparentid IN ( SELECT n.nodeparentid FROM node n WHERE n.nodeid IN ( SELECT a.nodeid FROM assets a WHERE a.displaycategory='ONT' AND a.assetnumber ='61180' ) ) );

or in prepared statement counts the same alarms in child nodes of primary node
SELECT COUNT(*) FROM alarms al WHERE al.reductionkey LIKE CONCAT( '%:','high-laser-bias')  AND al.nodeid IN 
( SELECT n.nodeid FROM node n WHERE n.nodeparentid IN ( SELECT n.nodeparentid FROM node n WHERE n.nodeid IN ( SELECT a.nodeid FROM assets a WHERE a.displaycategory='ONT' AND a.assetnumber ='61180' ) ) );
```

### primary node Examples with WITH clauses

```
--primary node get child alarms count
--WITH ont_parent_id_query AS (SELECT n.nodeparentid AS parentId FROM node n WHERE n.nodeid IN ( SELECT a.nodeid FROM assets a WHERE a.displaycategory='ONT' AND a.assetnumber ='61180' ) ) SELECT COUNT(*) FROM alarms al WHERE al.reductionkey LIKE CONCAT( '%:','high-laser-bias')  AND al.nodeid IN (SELECT parentId FROM ont_parent_id_query);

--same as
--SELECT COUNT(*) FROM node n WHERE n.nodeparentid IN ( SELECT n.nodeparentid FROM node n WHERE n.nodeid IN ( SELECT a.nodeid FROM assets a WHERE a.displaycategory='ONT' AND a.assetnumber ='61180' ) );

-- primary node get child node count
WITH ont_parent_id_query AS (SELECT n.nodeparentid AS parentId FROM node n WHERE n.nodeid IN ( SELECT a.nodeid FROM assets a WHERE a.displaycategory='ONT' AND a.assetnumber ='61180' ) ) SELECT COUNT(*) FROM node n WHERE n.nodeparentid IN (SELECT parentId FROM ont_parent_id_query);

--same as
--SELECT COUNT(*) FROM alarms al WHERE al.reductionkey LIKE CONCAT( '%:','high-laser-bias')  AND al.nodeid IN ( SELECT n.nodeid FROM node n WHERE n.nodeparentid IN ( SELECT n.nodeparentid FROM node n WHERE n.nodeid IN ( SELECT a.nodeid FROM assets a WHERE a.displaycategory='ONT' AND a.assetnumber ='61180' ) ) );
```

### secondary node Examples with WITH clauses
```
-- secondary node get child alarms count
WITH ont_parent_id_query AS (SELECT n.nodeparentid AS parentId FROM node n where n.nodeid IN ( SELECT a.nodeid FROM assets a WHERE a.displaycategory='ONT' AND a.assetnumber ='61180' ) ) SELECT COUNT(*) FROM alarms al WHERE al.reductionkey LIKE CONCAT( '%:','high-laser-bias')  AND al.nodeid IN (SELECT parentId FROM ont_parent_id_query);

-- secondary node get child node count
WITH ont_parent_id_query AS (SELECT n.nodeparentid AS parentId FROM node n where n.nodeid IN ( SELECT a.nodeid FROM assets a WHERE a.displaycategory='ONT' AND a.assetnumber ='61180' ) ) SELECT COUNT(*) FROM node n WHERE n.nodeparentid IN (SELECT parentId FROM ont_parent_id_query);

```
full SECONDARY node severity query

```
WITH 
ont_parent_id_query AS (SELECT n.nodeparentid AS parentId FROM node n where n.nodeid IN ( SELECT a.nodeid FROM assets a WHERE a.displaycategory='ONT' AND a.assetnumber ='61180' ) ), 

alarm_count_query AS (SELECT COUNT(*) AS alarmCount FROM alarms al WHERE al.severity>4 AND al.reductionkey LIKE CONCAT( '%:','high-laser-bias') AND al.nodeid IN (SELECT n.nodeid FROM node n where n.nodeparentid IN (SELECT parentId FROM ont_parent_id_query))),

child_count_query AS (SELECT COUNT(*) AS childCount FROM node n WHERE n.nodeparentid IN (SELECT parentId FROM ont_parent_id_query) )

SELECT  childCount, alarmCount, (CASE WHEN alarmCount >= childCount THEN 'Major' ELSE 'Warning' END ) FROM  child_count_query,alarm_count_query;
```


 
### reloading translator configuration 
```
cd minimal-minion-activemq
docker compose cp ./container-fs/horizon/opt/opennms-overlay/etc/translator-configuration.xml horizon:/usr/share/opennms/etc/

## send an event to reload the daemon
docker compose exec horizon /usr/share/opennms/bin/send-event.pl uei.opennms.org/internal/reloadDaemonConfig -p 'daemonName Translator' 
```

NOTE BUG - RELOAD CONFIGURATION CAUSES DATABASE DISCONNECT FOR EVENT TRANSLATOR - JUST RESTART
```
2024-04-10 08:41:15,500 WARN  [event-translator-Thread] o.o.n.e.EventIpcManagerDefaultImpl: run: an unexpected error occured during ListenerThread event-translator
java.lang.NullPointerException: Cannot invoke "javax.sql.DataSource.getConnection()" because "this.m_db" is null
        at org.opennms.core.utils.JDBCTemplate.doExecute(JDBCTemplate.java:89) ~[org.opennms.core.lib-32.0.5.jar:?]
        at org.opennms.core.utils.JDBCTemplate.execute(JDBCTemplate.java:68) ~[org.opennms.core.lib-32.0.5.jar:?]
        at org.opennms.netmgt.config.EventTranslatorConfigFactory$SqlValueSpec$Query.execute(EventTranslatorConfigFactory.java:627) ~[opennms-config-32.0.5.jar:?]
        at org.opennms.netmgt.config.EventTranslatorConfigFactory$SqlValueSpec.matches(EventTranslatorConfigFactory.java:603) ~[opennms-config-32.0.5.jar:?]
        at org.opennms.netmgt.config.EventTranslatorConfigFactory$AssignmentSpec.matches(EventTranslatorConfigFactory.java:456) ~[opennms-config-32.0.5.jar:?]
        at org.opennms.netmgt.config.EventTranslatorConfigFactory$TranslationMapping.translate(EventTranslatorConfigFactory.java:370) ~[opennms-config-32.0.5.jar:?]
        at org.opennms.netmgt.config.EventTranslatorConfigFactory$TranslationSpec.translate(EventTranslatorConfigFactory.java:319) ~[opennms-config-32.0.5.jar:?]
        at org.opennms.netmgt.config.EventTranslatorConfigFactory.translateEvent(EventTranslatorConfigFactory.java:287) ~[opennms-config-32.0.5.jar:?]
        at org.opennms.netmgt.translator.EventTranslator.onEvent(EventTranslator.java:157) ~[opennms-services-32.0.5.jar:?]
        at org.opennms.netmgt.eventd.EventIpcManagerDefaultImpl$EventListenerExecutor$2.run(EventIpcManagerDefaultImpl.java:189) [org.opennms.features.events.daemon-32.0.5.jar:?]
        at java.util.concurrent.CompletableFuture$AsyncRun.run(CompletableFuture.java:1804) [?:?]
        at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1136) [?:?]
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:635) [?:?]
        at org.opennms.core.concurrent.LogPreservingThreadFactory$2.run(LogPreservingThreadFactory.java:106) [opennms-util-32.0.5.jar:?]
```

## reloading eventd configuration for syslog events

```
cd minimal-minion-activemq
docker compose cp ./container-fs/horizon/opt/opennms-overlay/etc/eventconf.xml horizon:/usr/share/opennms/etc/

docker compose cp ./container-fs/horizon/opt/opennms-overlay/etc/events/Calex-Axos.syslog.events.xml  horizon:/usr/share/opennms/etc/events/

## send an event to reload the daemon
docker compose exec horizon /usr/share/opennms/bin/send-event.pl uei.opennms.org/internal/reloadDaemonConfig -p 'daemonName Eventd' 
```

## reloading syslogd configuration

```
cd minimal-minion-kafka

docker compose cp ./container-fs/horizon/opt/opennms-overlay/etc/syslogd-grok-patterns.txt horizon:/usr/share/opennms/etc/

docker compose cp ./container-fs/horizon/opt/opennms-overlay/etc/syslogd-configuration.xml horizon:/usr/share/opennms/etc/

docker compose cp ./container-fs/horizon/opt/opennms-overlay/etc/syslog/Calex-Axos.syslog.xml  horizon:/usr/share/opennms/etc/syslog/


## send an event to reload the daemon
docker compose exec horizon /usr/share/opennms/bin/send-event.pl uei.opennms.org/internal/reloadDaemonConfig -p 'daemonName syslogd' 
```