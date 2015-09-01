package com.iamghf.web.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.ghf.bean.Dict;
import com.ghf.core.dao.interfaces.DbDao;
import com.ghf.utils.common.ServiceFactory;

public class PutTest {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		createDicts();
	}
	
	public static void createDicts()throws Exception{
		String fileName = "F:\\BaiduYunDownload\\www.csdn.net.sql";
		//DbInterFace db = (DbInterFace)ServiceFactory.getInstance().getService(DbInterFace.class);
		DbDao db = (DbDao)ServiceFactory.getInstance().getService(DbDao.class);
		
		File file = new File(fileName); 
		//BufferedReader input = new BufferedReader (new FileReader(file));
		InputStreamReader read = new InputStreamReader (new FileInputStream(file),"UTF-8");
		BufferedReader input = new BufferedReader (read);  
		String text ;
		List<Dict> list = new ArrayList<Dict>();
		long count = 0L;
		long total = 0L;
		while((text = input.readLine()) != null){
			total++;
			count++;
			Dict dt = new Dict();
			String[] strs = text.split("#");
			dt.setName(filteSQLStr(strs[0].trim()));
			dt.setPassword(filteSQLStr(strs[1].trim()));
			dt.setEmail(filteSQLStr(strs[2].trim()));
			dt.setRemark("csdn");
			list.add(dt);
			
			if(count>9999){
				db.insert(list.toArray(new Dict[]{}));
				list.clear();
				count=0;
				total = total-count;
			}
			//db.insert(cityInfo);
        }
		if(total>0){
			db.insert(list.toArray(new Dict[]{}));
		}
	}
	
	public static String filteSQLStr(String Str)
    {

        Str = Str.replace("'", "\'");

        return Str;
    }
}
