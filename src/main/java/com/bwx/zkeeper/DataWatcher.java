package com.bwx.zkeeper;

import org.apache.zookeeper.*;

import java.io.IOException;

public class DataWatcher implements Watcher, Runnable {
    private static String hostPort = "note1:2181,note2:2181,note3:2181";
    private static String zooDataPath = "/MyConfig";
    byte zoo_data[] = null;
    ZooKeeper zk;

    public DataWatcher() {
        try {
            zk = new ZooKeeper(hostPort, 2000, this);
            if (zk != null) {
                try {
                    //Create the znode if it doesn't exist, with the following code:
                    if (zk.exists(zooDataPath, this) == null) {
                        zk.create(zooDataPath, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
                    }
                } catch (KeeperException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printData() throws InterruptedException, KeeperException {
        zoo_data = zk.getData(zooDataPath, this, null);
        String zString = new String(zoo_data);
        // The following code prints the current content of the znode to the console:
        System.out.printf("\nCurrent Data @ ZK Path %s: %s", zooDataPath, zString);
    }

    @Override
    public void run() {
        try {
            synchronized (this) {
                while (true) {
                    wait();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.printf(
                "\nEvent Received: %s", watchedEvent.toString());
        //We will process only events of type NodeDataChanged
        if (watchedEvent.getType() == Event.EventType.NodeDataChanged) {
            try {
                printData();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (KeeperException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args)
            throws InterruptedException, KeeperException {
        DataWatcher dataWatcher = new DataWatcher();
        dataWatcher.printData();
        Thread thread = new Thread(dataWatcher);
        thread.start();
//        dataWatcher.run();
    }
}
