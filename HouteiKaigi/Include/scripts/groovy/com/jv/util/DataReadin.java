package com.jv.util;
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData;

import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.jv.bean.MeetInfo;
import com.kms.katalon.core.testdata.TestData;

public class DataReadin {
	
	private static final String JV_NAME = "JVName";
	
	private static final String JV_FIELD = "JVField";

//	private Object invoke;
//
//	private Object invoke2;
	
	public void add() {
		
		try {
			String name = findTestData("JvTestData").getValue(3, 1);
			System.out.println(name);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	public void dataReadin(String JvTestData, String JvPatternName) throws Exception {
		
		int JV_Field_index = 0;
		
		int Jv_Pattern_index = 0;
		
		//TestData取得
		TestData td = findTestData(JvTestData);
		//AllColumnNameを取得
		String[] colNames = td.getColumnNames();
		for (int i=0; i<colNames.length; i++) {
			
			//JV項目の漢字名
			if(JV_NAME.equals(colNames[i])) 
				continue;
			//JV項目の英字名
			if(JV_FIELD.equals(colNames[i])) {
				JV_Field_index = i;
				continue;
			}
			//JVテストのパターン名
			if(JvPatternName.equals(colNames[i])) {
				Jv_Pattern_index = i;
				continue;
			}
		}
		
		Map<String, String> dataMap = new HashMap<String, String>();
		
		for(int r=1; r <= td.getRowNumbers(); r++) {
			dataMap.put(td.getValue(JV_Field_index, r), td.getValue(Jv_Pattern_index, r));
		}
		
		Class<MeetInfo> meetInfo = MeetInfo.class;
		Field[] fields = meetInfo.getDeclaredFields();
		for(Field field : fields) {
			PropertyDescriptor pd = new PropertyDescriptor(field.getName(), meetInfo);
			Method wM = pd.getWriteMethod();
			wM.invoke(meetInfo, td.getValue(Jv_Pattern_index, 1));
			System.out.println(field.getName());
		}
		
	}
	

}