package com.bwx.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.bwx.config.Constant.*;

public class OcfReader {


	private BufferedReader reader;
	private String charset="utf-8";

	private int headline_nums=3;
	private List<String> heads = new ArrayList<String>();


	private String ocf_type;
	public int stn_nums;
	private int stnline_columns;

	private int fcline_columns;
	private int fcline_nums;

	private long line_count;

	public OcfReader(File file) {

		try {
			reader =new BufferedReader(new InputStreamReader(new FileInputStream(file), charset));

			List<String[]> head_arrays = new ArrayList<String[]>();
			for (int i = 0; i < headline_nums; i++) {
				head_arrays.add(reader.readLine().trim().split("\\s+"));
			}

			//第一行
			String[] head0 = head_arrays.get(0);
			this.ocf_type=head0[0];
			//强转
			head0[1]="999999";
			this.stn_nums=Integer.parseInt(head0[1]);
			if(head0.length<7){
				if(this.ocf_type.equals("OCF_LST_HOUR")){
					this.fcline_nums=106;
				}else{
					this.fcline_nums=Integer.parseInt(head0[3]);
				}
			}else{
				this.fcline_nums=Integer.parseInt(head0[4]);
			}

			//第二行
			String[] head1 = head_arrays.get(1);
			if(head1.length<10){
				if(this.ocf_type.equals("OCF_LST_HOUR")){
					this.stnline_columns=9;
				}else{
					this.stnline_columns=10;
				}

			}else{
				this.stnline_columns=head1.length;
			}



			//第三行
			String[] head2 = head_arrays.get(2);
			this.fcline_columns=head2.length;


			line_count=headline_nums;



			for (String[] array : head_arrays) {
				StringBuilder sb = new StringBuilder();
				for (String ele : array) {
					sb.append(ele).append("  ");
				}

				heads.add(sb.toString().trim());
			}




		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	public List<String> nextStn(){
		List<String> list = new ArrayList<String>();

		String line = null;
		int line_columns=0;
		try {
			for (int i = 0; i < fcline_nums+1; i++) {
				line = reader.readLine();
				if(line==null){
					return null;
				}
				line=line.trim();

				line_columns=line.split("\\s+").length;
				line_count++;

				if((i==0 &&line_columns==stnline_columns)||(i!=0 &&line_columns==fcline_columns)){
					list.add(line);
				}else{
					list.clear();
					list.add(skip2stnLine());
					i=0;
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}
	public List<String[]> nextStnArray(){
		List<String[]> list = new ArrayList<String[]>();

		String line = null;
		String[] array =null;
		int line_columns=0;
		try {
			for (int i = 0; i < fcline_nums+1; i++) {
				line = reader.readLine();
				if(line==null){
					return null;
				}

				array= line.trim().split("\\s+");

				line_columns=array.length;
				line_count++;

				if((i==0 &&line_columns==stnline_columns)||(i!=0 &&line_columns==fcline_columns)){
					list.add(array);
				}else{
					list.clear();
					list.add(skip2stnLine().split("\\s+"));
					i=0;
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}

	private String skip2stnLine(){
		String stnline=null;

		String line = null;
		int line_columns=0;
		try {
			while((line=reader.readLine())!=null){

				line = line.trim();
				line_columns=line.split("\\s+").length;
				line_count++;

				if(line_columns==stnline_columns){
					stnline=line;
					break;
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return stnline;
	}



	public List<Map<String,String>> nextStnMap(){
		 List<Map<String,String>> list = new ArrayList<Map<String,String>>();

		String line = null;
		int line_columns=0;
		String stn_line=null;
		try {
			for (int i = 0; i < fcline_nums+1; i++) {
				line = reader.readLine();
				if(line==null){
					return null;
				}
				line = line.trim();
				String[] array = line.split("\\s+");
				line_columns=array.length;
				line_count++;

				if(i==0 &&line_columns==stnline_columns){
					stn_line=line;
				}else if(i!=0 && line_columns==fcline_columns){
					Map<String, String> map = array2map(array);
					list.add(map);
				}else{
					list.clear();
					stn_line =skip2stnLine();
					i=0;

				}

			}
			list.get(0).put(HEAD, stn_line);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}



	private Map<String,String> array2map(String[] array){

		HashMap<String,String> map = new HashMap<String,String>();
		map.put(VTI, array[0]);
		if(ocf_type.startsWith(OCF_12H)){
			map.put(FC_TIME, array[1]);
			map.put(TEMP, array[2]);
			map.put(TMAX, array[3]);
			map.put(TMIN, array[4]);
			map.put(RAIN, array[5]);
			map.put(FF, array[6]);
			map.put(WS, array[7]);
			map.put(DD, array[8]);
			map.put(WD, array[9]);
			map.put(CLOUD, array[10]);
			map.put(WEATHER, array[11]);
			map.put(RH, array[12]);
			map.put(RH_MAX, array[13]);
			map.put(RH_MIN, array[14]);
		}else{
			map.put(VTI2, array[1]);
			map.put(FC_TIME, array[2]);
			map.put(TEMP, array[3]);
			map.put(TMAX, array[4]);
			map.put(TMIN, array[5]);
			map.put(RAIN, array[6]);
			map.put(FF, array[7]);
			map.put(WS, array[8]);
			map.put(DD, array[9]);
			map.put(WD, array[10]);
			map.put(CLOUD, array[11]);
			map.put(WEATHER, array[12]);
			map.put(RH, array[13]);
		}

		return map;
	}



	public  void close(){
		try {
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public List<String> getHeads(){
		return heads;
	}


}
