package com.bwx.monitor;

import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;

import java.io.File;

public class FileAlterationListener2 extends FileAlterationListenerAdaptor {
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

    @Override
    public void onFileChange(File file) {
        super.onFileChange(file);
    }

    @Override
    public void onFileDelete(File file) {
        super.onFileDelete(file);
    }
}
