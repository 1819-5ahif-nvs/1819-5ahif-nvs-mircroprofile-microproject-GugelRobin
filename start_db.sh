DBDIR=/media/robin/989881DA9881B772/Users/Robin/Desktop/Schule/NVS_GIT_STUETZ/microproject/1819-5ahif-nvs-mircroprofile-microproject-GugelRobin/db
sudo docker run -w /home -v $DBDIR:/home -d -t -p 1527:1527 datagrip/derby-server:10.11
