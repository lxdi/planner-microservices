kubectl create configmap postgres-init --from-file=config/postgres/0-create.sql
kubectl create configmap logstash-config --from-file=config/logstash/logstash.conf
