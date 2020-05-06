/**
 * 
 */
package com.bwx.main;

import java.util.HashMap;
import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleBindings;

import com.alibaba.fastjson.JSONObject;

/**
 * ejs 模板引擎
 * @author kingboy
 *
 */
public class EjsEngine {

	    private final ScriptEngineManager scriptEngineManager = 
		      new ScriptEngineManager(); 
	    
	    private final ScriptEngine nashorn = 
		      scriptEngineManager.getEngineByName("nashorn"); 
	    
	    private String ejsPath = "ejs/ejs.js";
	    
	    
	    public EjsEngine(){
	    
	    }
	    
	    public EjsEngine(String ejsPath){
	    	this.ejsPath = ejsPath;
	    }
	    
	    public Object rander(String templeteStr,JSONObject context) throws Exception{
			SimpleBindings simpleBindings = new SimpleBindings(); 
			simpleBindings.putAll(context);
			try {
				//System.out.println(String.format("load('%s');ejs.render('%s',%s)",ejsPath,templeteStr.replaceAll("(\\r)*\\n",""),context.toString()));
				return nashorn.eval(String.format("load('%s');ejs.render('%s',%s)",ejsPath,templeteStr.replaceAll("\\r*\\n",""),context.toString()),simpleBindings);
			} catch (ScriptException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new Exception("rander error,templete is "+templeteStr.replaceAll("\\r*\\n",""));
			}
	    };
	    
}
