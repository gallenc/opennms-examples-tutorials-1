# requisitions

# gpon1 
Derived from supplied logs.
no splitters.

```
 docker compose exec horizon /usr/share/opennms/bin/send-event.pl uei.opennms.org/internal/importer/reloadImport -p 'url http://pris:8000/requisitions/gpon1'
```


# gpon2
Adding splitters into the mix

```
 docker compose exec horizon /usr/share/opennms/bin/send-event.pl uei.opennms.org/internal/importer/reloadImport -p 'url http://pris:8000/requisitions/gpon2'
```

# copy drules rules and restart events

```
in windows / docker desktop use

cd minimal-minion-kafka

docker compose cp container-fs/horizon/opt/opennms-overlay/etc/alarmd/drools-rules.d/gpon-rules.drl horizon:/usr/share/opennms/etc/alarmd/drools-rules.d/gpon-rules.drl

or simply

docker compose cp gpon-rules.drl horizon:/usr/share/opennms/etc/alarmd/drools-rules.d/gpon-rules.drl

to reload eventd use

docker compose exec horizon /usr/share/opennms/bin/send-event.pl uei.opennms.org/internal/reloadDaemonConfig -p 'daemonName alarmd'

```