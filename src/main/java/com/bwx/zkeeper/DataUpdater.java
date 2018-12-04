package com.bwx.zkeeper;

import com.bwx.config.Constant;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.UUID;

public class DataUpdater implements Watcher {
    private static String hostPort = Constant.zookeeper_hosts;
    private static String zooDataPath = "/MyConfig";
    private static int session = 2000;
    ZooKeeper zk;
    byte zoo_data[] = null;

    public DataUpdater() throws IOException {
        try {
            zk = new ZooKeeper(hostPort, session, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // updates the znode path /MyConfig every 5 seconds with a new UUID string.
    public void run() throws InterruptedException, KeeperException {
        while (true) {
            String uuid = UUID.randomUUID().toString();
            byte zoo_data[] = uuid.getBytes();
            zk.setData(zooDataPath, zoo_data, -1);
            try {
                Thread.sleep(3000); // Sleep for 5 secs
            } catch(InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) throws
            IOException, InterruptedException, KeeperException {
        DataUpdater dataUpdater = new DataUpdater();
        dataUpdater.run();
    }

    @Override
    public void process(WatchedEvent event) {
        System.out.printf("\nEvent Received: %s", event.toString());
    }
}
