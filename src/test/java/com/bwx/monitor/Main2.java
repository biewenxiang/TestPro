package com.bwx.monitor;

import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.io.File;

public class Main2 {
    public static void main(String[] args) throws Exception {
        File directory = new File(new File("."), "src");
        FileAlterationObserver observer = new FileAlterationObserver(directory);
        observer.addListener(new FileAlterationListenerAdaptor(){
            @Override
            public void onFileCreate(File file) {
                System.out.println("---------onFileCreate--------");
                try {
                    if (!file.exists() || !file.isFile()) {
                        return;
                    }

                    System.out.println("new file=" + file.getAbsolutePath() + "; file size=" + file.length());
//                    this.getDistributeExec(file);
                } catch (Exception e) {
                    System.err.println("onFileCreate err [" + file.getAbsolutePath()+"]"+e );
                }
            }
        });
        long interval = 1000;
        FileAlterationMonitor monitor = new FileAlterationMonitor(interval);
        monitor.addObserver(observer);
        monitor.start();
    }
}
