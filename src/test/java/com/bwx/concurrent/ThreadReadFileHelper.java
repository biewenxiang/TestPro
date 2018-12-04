package com.bwx.concurrent;

import java.io.*;
import java.security.InvalidParameterException;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadReadFileHelper {
    // 模拟数据
    private static void writeData() throws FileNotFoundException, IOException {
//        File file = new File("D:\\Test\\test.txt");
//        if (!file.exists()) {
//            file.mkdir();
//        }
        FileOutputStream fileOutputStream = new FileOutputStream("D:\\Test\\test.txt");
        Random random = new Random();
        for (int n = 0; n < 10000000; n++) {
            int count = random.nextInt(10) + 1;
            StringBuilder builder = new StringBuilder();

            for (int i = 0; i < count; i++) {
                builder.append(UUID.randomUUID().toString());
            }

            builder.append("\n");
            fileOutputStream.write(builder.toString().getBytes());
        }
        fileOutputStream.close();
        System.out.println("ok");
    }

    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    // 231498耗时 3线程(本机2核4线程)
    // 278592耗时 2线程
    // 397115耗时 单线程
    // 245657耗时 cpu线程数（4线程）
    //94090 8线程
    public static void main(String[] args) throws Exception {
        long beginTime = System.currentTimeMillis();
        ThreadReadFileHelper helper = new ThreadReadFileHelper();
        //writeData();
        helper.read("D:\\Test\\test.txt", Runtime.getRuntime().availableProcessors(), '\n', new StringCallback("UTF-8") {
            @Override
            void callback(String data) {
                int count = atomicInteger.incrementAndGet();
                //System.out.println(count);
                if (count == 1000000) {
                    System.out.println("总耗时毫秒：" + (System.currentTimeMillis() - beginTime));
                    System.out.println(data);
                }
            }
        });

        // RandomAccessFile randomAccessFile = new RandomAccessFile("C:\\Users\\lianghaohui\\Desktop\\test.txt", "r");
        // while (true) {
        // if (randomAccessFile.readLine() == null) {
        // System.out.println("总耗时毫秒：" + (System.currentTimeMillis() - beginTime));
        // break;
        // } else {
        // int count = atomicInteger.incrementAndGet();
        // System.out.println(count);
        // }
        // }
        // randomAccessFile.close();
    }

    public void read(String path, int threadCount, char separator, StringCallback callback) throws IOException {

        if (threadCount < 1) {
            throw new InvalidParameterException("The threadCount can not be less than 1");
        }

        if (path == null || path.isEmpty()) {
            throw new InvalidParameterException("The path can not be null or empty");
        }

        if (callback == null) {
            throw new InvalidParameterException("The callback can not be null");
        }

        RandomAccessFile randomAccessFile = new RandomAccessFile(path, "r");

        long fileTotalLength = randomAccessFile.length();
        long gap = fileTotalLength / threadCount;
        long checkIndex = 0;
        long[] beginIndexs = new long[threadCount];
        long[] endIndexs = new long[threadCount];

        for (int n = 0; n < threadCount; n++) {
            beginIndexs[n] = checkIndex;
            if (n + 1 == threadCount) {
                endIndexs[n] = fileTotalLength;
                break;
            }
            checkIndex += gap;
            long gapToEof = getGapToEof(checkIndex, randomAccessFile, separator);

            checkIndex += gapToEof;
            endIndexs[n] = checkIndex;
        }

        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        executorService.execute(() -> {
            try {
                readData(beginIndexs[0], endIndexs[0], path, randomAccessFile, separator, callback);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        for (int n = 1; n < threadCount; n++) {
            long begin = beginIndexs[n];
            long end = endIndexs[n];
            executorService.execute(() -> {
                try {
                    readData(begin, end, path, null, separator, callback);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private long getGapToEof(long beginIndex, RandomAccessFile randomAccessFile, char separator) throws IOException {
        randomAccessFile.seek(beginIndex);
        long count = 0;

        while (randomAccessFile.read() != separator) {
            count++;
        }

        count++;

        return count;
    }

    private void readData(long begin, long end, String path, RandomAccessFile randomAccessFile, char separator, StringCallback callback) throws FileNotFoundException, IOException {
        System.out.println("开始工作" + Thread.currentThread().getName());
        if (randomAccessFile == null) {
            randomAccessFile = new RandomAccessFile(path, "r");
        }

        randomAccessFile.seek(begin);
        StringBuilder builder = new StringBuilder();

        while (true) {
            int read = randomAccessFile.read();
            begin++;
            if (separator == read) {
                if (callback != null) {
                    callback.callback0(builder.toString());
                }
                builder = new StringBuilder();
            } else {
                builder.append((char) read);
            }

            if (begin >= end) {
                break;
            }
        }
        randomAccessFile.close();
    }

    public static abstract class StringCallback {
        private String charsetName;
        private ExecutorService executorService = Executors.newSingleThreadExecutor();

        public StringCallback(String charsetName) {
            this.charsetName = charsetName;
        }

        private void callback0(String data) {
            executorService.execute(() -> {
                try {
                    callback(new String(data.getBytes("ISO-8859-1"), charsetName));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            });

        }

        abstract void callback(String data);
    }

}
