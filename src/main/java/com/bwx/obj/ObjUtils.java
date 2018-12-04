package com.bwx.obj;

import java.io.*;
import java.util.HashMap;
import java.util.List;

public class ObjUtils {

	
	public static Object readObj(String objStr){
		
		Object obj =null;
		ObjectInputStream or = null;
		try {
			File objFile = new File(objStr);
			or = new ObjectInputStream(new BufferedInputStream(new FileInputStream(objFile)));
			obj =  or.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				or.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return obj;
	}
	
	
	public static void writeObj(String objFile,List<HashMap<String, String>> list){
		ObjectOutputStream ow = null;
		try {
			File file =new File(objFile);
			ow = new ObjectOutputStream(new FileOutputStream(file));
			ow.writeObject(list);
			ow.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ow.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	
	
	
	
}
