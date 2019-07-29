package com.bwx.hbase;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.HTablePool;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

public class TablePool{
	private static TablePool myHTablePool=null;
	private HTablePool pool;
	
	@SuppressWarnings("unused")
	private TablePool(){
		try{
			Configuration conf = HBaseConfiguration.create();
			conf.set("hbase.zookeeper.quorum", "note1");
//			conf.set("hbase.zookeeper.quorum", "172.16.185.90,172.16.185.91,172.16.185.92");
			conf.set("hbase.zookeeper.property.clientPort", "2182");
//			conf.set("zookeeper.znode.parent", "/hbase-unsecure");
			pool = new HTablePool(conf, 5);
		}catch(Exception e){
			e.printStackTrace();
		}
	};
	
	public synchronized static TablePool getInstance(){
		if(myHTablePool==null)
			myHTablePool= new TablePool();
		return myHTablePool;
	}
	

	public HTableInterface getTable(String tableName){
		HTableInterface table = pool.getTable(tableName);
		return table;
	}
//	public void addData(String tableName, String rowKey, String[] column1, String[] value1, String[] column2,
//						String[] value2) throws Exception {
//
//		Put put=new Put(Bytes.toBytes(rowKey));
//		HTable htable=new HTable(conf, Bytes.toBytes(tableName));
//		HColumnDescriptor[] columnFamilies = htable.getTableDescriptor().getColumnFamilies();
//		for(int i=0;i<=columnFamilies.length;i++){
//			String nameAsString = columnFamilies[i].getNameAsString();
//			if(nameAsString.equals("lie01")){
//				for(int j=0;j<column1.length;j++){
//					put.add(Bytes.toBytes(nameAsString), Bytes.toBytes(column1[j]),Bytes.toBytes(value1[j]));
//				}
//			}
//			if(nameAsString.equals("lie02")){
//				for(int j=0;j<column2.length;j++){
//					put.add(Bytes.toBytes(nameAsString), Bytes.toBytes(column2[j]),Bytes.toBytes(value2[j]));
//				}
//			}
//
//		}
//		htable.put(put);
//		System.out.println("addData ok!");
//	}

}
