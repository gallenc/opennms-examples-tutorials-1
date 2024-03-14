# notes


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