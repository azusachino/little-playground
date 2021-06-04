mkdir -p /var/lib/etcd/
mkdir -p /opt/etcd/config/

cat <<EOF | sudo tee /opt/etcd/config/etcd.conf
#节点名称
ETCD_NAME=$(hostname -s)
#数据存放位置
ETCD_DATA_DIR=/var/lib/etcd
EOF

cat <<EOF | sudo tee /etc/systemd/system/etcd.service

[Unit]
Description=Etcd Server
Documentation=https://github.com/etcd-io/etcd
After=network.target

[Service]
User=root
Type=notify
EnvironmentFile=-/opt/etcd/config/etcd.conf
ExecStart=/usr/local/bin/etcd
Restart=on-failure
RestartSec=10s
LimitNOFILE=40000

[Install]
WantedBy=multi-user.target
EOF