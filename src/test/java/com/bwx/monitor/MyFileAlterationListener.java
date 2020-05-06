package com.bwx.monitor;

import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.io.File;

public class MyFileAlterationListener implements FileAlterationListener {
    public void onStart(FileAlterationObserver observer) {
        // TODO Auto-generated method stub
        System.out.println("--------onStart ---------");
    }
    public void onDirectoryCreate(File directory) {
        // TODO Auto-generated method stub
        System.out.println("--------onDirectoryCreate ---------");
    }

    public void onDirectoryChange(File directory) {
        // TODO Auto-generated method stub
        System.out.println("--------onDirectoryChange ---------");
    }

    public void onDirectoryDelete(File directory) {
        // TODO Auto-generated method stub
        System.out.println("--------onDirectoryDelete ---------");
    }

    public void onFileCreate(File file) {
        // TODO Auto-generated method stub
        System.out.println("--------onFileCreate ---------");
    }

    public void onFileChange(File file) {
        // TODO Auto-generated method stub
        System.out.println("--------onFileChange ---------");
    }

    public void onFileDelete(File file) {
        // TODO Auto-generated method stub
        System.out.println("--------onFileDelete ---------");
    }

    public void onStop(FileAlterationObserver observer) {
        // TODO Auto-generated method stub
        System.out.println("--------onStop ---------");
    }

}
