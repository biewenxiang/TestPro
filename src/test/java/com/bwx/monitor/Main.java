package com.bwx.monitor;

import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.io.File;

public class Main {
    public static void main(String[] args) throws Exception {
        File directory = new File(new File("."), "src");
        FileAlterationObserver observer = new FileAlterationObserver(directory);
        observer.addListener(new MyFileAlterationListener());
        long interval = 1000;
        FileAlterationMonitor monitor = new FileAlterationMonitor(interval);
        monitor.addObserver(observer);
        monitor.start();
    }
}
