package com.bwx.obj;


import com.bwx.config.Constant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class WdsiObj {
	public static Map<String, HashMap<String, String>> dict_wdsi=new HashMap<String,HashMap<String,String>>();
	
	static{
		List<HashMap<String,String>> list = (List<HashMap<String, String>>) ObjUtils.readObj(Constant.WDSI_OBJ);
		for (HashMap<String, String> hashmap : list) {
			String key =hashmap.get("rowkey");
			String code =hashmap.get("code");
			String name =hashmap.get("name");
			String ename =hashmap.get("ename");
            
			HashMap<String, String> map = new HashMap<String,String>();
			map.put("code", code);
			map.put("name", name);
			map.put("ename", ename);
			dict_wdsi.put(key, map);
			
		}
		
		
	}
}
