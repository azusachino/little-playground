etcd --name iris126 
  --initial-advertise-peer-urls http://104.224.188.126:2380 \
  --listen-peer-urls http://104.224.188.126:2380 \
  --listen-client-urls http://104.224.188.126:2379,http://127.0.0.1:2379 \
  --advertise-client-urls http://104.224.188.126:2379 \
  --initial-cluster-token iris-etcd-cluster \
  --initial-cluster iris190=http://64.64.224.190:2380,iris126=http://104.224.188.126:2380\
  --initial-cluster-state new
  --logger zap