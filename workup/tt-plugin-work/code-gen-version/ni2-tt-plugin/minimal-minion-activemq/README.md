# test environment for ni2 trouble ticket plugin

```
cd minimal-minon-activemq
docker compose exec horizon bash
ssh -p 8101 admin@localhost

```
SSH into the Karaf shell: ssh -p 8101 admin@localhost

feature:install ni2-ticketing

to reinstall kar

cd minimal-minion-activemq

docker compose cp ../ni2-tt-api/assembly/kar/target/ni2-tt-api-kar-0.0.1-SNAPSHOT.kar  horizon:/usr/share/opennms/deploy/
