package com.bwx.config;

public class Constant {
	public final static String zookeeper_hosts = Configure.getInstance().getString("zookeeper_hosts");
	public final static String kafka_host = Configure.getInstance().getString("kafka_host");

	public static final String KAFKA_SERVER =Configure.getInstance().getString("KAFKA_SERVER");
	public static final String VM_DIR =Configure.getInstance().getString("VM_DIR");
	
	
	public static final String DOMESTIC_OBJ =Configure.getInstance().getString("DOMESTIC_OBJ");
	public static final String TOURISM_OBJ =Configure.getInstance().getString("TOURISM_OBJ");
	public static final String OVERSEAS_OBJ =Configure.getInstance().getString("OVERSEAS_OBJ");
	public static final String INDEX_OBJ =Configure.getInstance().getString("INDEX_OBJ");
	public static final String WDSI_OBJ =Configure.getInstance().getString("WDSI_OBJ");
	
	
	
	public static final String OBS_YESTERDAY =Configure.getInstance().getString("OBS_YESTERDAY");
	public static final String OBS_TODAY =Configure.getInstance().getString("OBS_TODAY");
	
	public static final String CITY_BJ =Configure.getInstance().getString("CITY_BJ");
	
	public static final String SHTML_TEXT_FC_DISTRICT =Configure.getInstance().getString("SHTML_TEXT_FC_DISTRICT");
	public static final String SHTML_TEXT_FC_PROV =Configure.getInstance().getString("SHTML_TEXT_FC_PROV");
	public static final String HTM_PROVINCE_FC =Configure.getInstance().getString("HTM_PROVINCE_FC");
	
	public static final String HTM_AROUND_FC =Configure.getInstance().getString("HTM_AROUND_FC");
	public static final String HTM_AROUND_FC_NEW =Configure.getInstance().getString("HTM_AROUND_FC_NEW");
	public static final String HTM_AROUND_FC_EN =Configure.getInstance().getString("HTM_AROUND_FC_EN");
	
	public static final String HTM_VM_7D =Configure.getInstance().getString("HTM_VM_7D");
	public static final String HTM_JSON_7D =Configure.getInstance().getString("HTM_JSON_7D");
	public static final String HTM_WAP =Configure.getInstance().getString("HTM_WAP");
	
	public static final String HTML_JSON_SHOW_24H =Configure.getInstance().getString("HTML_JSON_SHOW_24H");
	public static final String HTML_JSON_SHOW_3H =Configure.getInstance().getString("HTML_JSON_SHOW_3H");
	public static final String HTM_VM_Index =Configure.getInstance().getString("HTM_VM_Index");
	public static final String HTM_JSON_Index =Configure.getInstance().getString("HTM_JSON_Index");
	//键名规范
	public static final String TEMP = "temp";
	public static final String TMAX = "tmax";
	public static final String TMIN = "tmin";
	public static final String RAIN = "rain";
	public static final String WS = "ws";
	public static final String WD = "wd";
	public static final String WEATHER= "weather";
	public static final String RH= "rh";
	public static final String CLOUD= "cloud";
	
	public static final String FF= "ff";
	public static final String DD= "dd";
	
	public static final String VTI= "vti";
	public static final String VTI2= "vti2";
	
	public static final String FC_TIME= "fc_time";
	
	
	public static final String RH_MAX= "rhmax";
	public static final String RH_MIN= "rhmin";
	
	
	//附加
	public static final String HEAD= "head";
	public static final String OCF_12H= "OCF_12H";
	public static final String OCF_3H= "OCF_3H";
	public static final String OCF_1H= "OCF_LST_1H";

}
