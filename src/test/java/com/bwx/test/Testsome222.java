//package com.bwx.test;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//
//public class Testsome222 {
//    public static void main(String[] args) {
//
//        // TODO 用户修改
//
//        String aid = "3ab50537ff444cedb8d10573ea81";
//
//        String akey = "9e2fd4b5b6d54cd5b5297f937a64";
//
//        String bucketName = "product-nmc-nwpr-eki";
//
//        String downloadPathPre = "D:\\user\\file\\";
//
//        try {
//
//            System.out.println("download file start!");
//
//            //1、获取bucketId 示例
//
//            String bucketId = getBucketIdByName(aid, akey, bucketName);
//
//            // 2、获取可下载的文件信息示例,返回类型为JSONArray，可能为null，需要进行空值判断，避免空指针
//
//            JSONArray downFileIds = getDownFileIds(aid, akey, bucketId);
//
//            for (int i = 0; i < (downFileIds != null ? downFileIds.size() : 0); i++) {
//
//                JSONObject jo = JSON.parseObject(downFileIds.get(i).toString());
//
//                // 3、申请下载示例
//
//                String fileId = jo.getString("fileId");
//
//                String targetFileName = jo.getString("name");
//
//                JSONObject downApply = downApply(fileId, aid, akey, bucketId);
//
//                // 4、执行下载示例
//
//                String downloadURL = downApply.getString("downloadURL");
//
//                String downloadToken = downApply.getString("downloadToken");
//
//                down(downloadURL, downloadToken, downloadPathPre + targetFileName);
//
//            }
//
//            System.out.println("download file end");
//
//        } catch (Exception e) {
//
//            e.printStackTrace();
//
//        }
//
//    }
//}
