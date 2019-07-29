package com.bwx.hbase;

import com.alibaba.fastjson.JSONObject;
import javafx.scene.effect.Light;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.bwx.obj.ObjUtils.readObj;

public class HBaseTest {
    public static void main(String[] args) {
        List list = new ArrayList();
        HTableInterface table = null;
        try {
            table = TablePool.getInstance().getTable("t_student");

            Scan scan = new Scan();
            scan.setCaching(200);
            ResultScanner scanner = table.getScanner(scan);
            JSONObject obj = new JSONObject();
            for (Result result : scanner) {

                String row = Bytes.toString(result.getRow());

                HashMap<String, String> map = new HashMap<String, String>();
                map.put("rowkey", row);
                for (KeyValue kv : result.list()) {
                    map.put(Bytes.toString(kv.getFamily()) + "," + Bytes.toString(kv.getQualifier()), Bytes.toString(kv.getValue()));
                }

                list.add( map);

            }
            System.out.println(JSONObject.toJSONString(list));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                table.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        recoverObj("C:\\Users\\admin\\Desktop\\data\\wdsi.obj","t_student2",list);

    }
    private static void recoverObj(String obj ,String table_name, List list){

        HTableInterface table =TablePool.getInstance().getTable(table_name);
        try {
            table.setAutoFlush(false, false);
            table.setWriteBufferSize(1024*1024*6);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        List<HashMap<String,String>> datalist = (List<HashMap<String, String>>) list;

        for (HashMap<String, String> hashMap : datalist) {


            Put put = new Put(hashMap.get("rowkey").getBytes());
            for (String key : hashMap.keySet()) {
                if(!key.equals("rowkey")){
                    String[] split = key.split(",");
                    put.add(split[0].getBytes(), (split[1]+"qua").getBytes(),(hashMap.get(key)+"test").getBytes());
                }
            }
            try {
                put.setWriteToWAL(false);
                table.put(put);
            } catch (IOException e) {
                e.printStackTrace();
            }



        }
        try {
            table.flushCommits();
            table.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
