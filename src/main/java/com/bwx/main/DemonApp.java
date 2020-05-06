package com.bwx.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * Hello world!
 *
 */
public class DemonApp 
{
    public static void main( String[] args ) throws Exception
    {

    		Map<String,Object> name = new HashMap<String,Object>();
    		name.put("bb", "123");
    		List<String> arr = new ArrayList<String>();
    		arr.add("one");
    		arr.add("two");
    		arr.add("three");
    		EjsEngine ejs = new EjsEngine();
    		name.put("arr", arr);
    		System.out.println(ejs.rander("<ul><% for(var i=0;i<arr.length;i++) {%><li><%=arr[i]%></li><%}%></ul>	<%=bb%>", (JSONObject) JSONObject.toJSON(name)));;
    }
}
