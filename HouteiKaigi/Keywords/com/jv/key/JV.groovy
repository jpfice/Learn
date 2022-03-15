package com.jv.key;
import internal.GlobalVariable;
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint;
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase;
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData;
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject;
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.jv.constants.S_Object;
import com.kms.katalon.core.annotation.Keyword;
import com.kms.katalon.core.checkpoint.Checkpoint;
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords;
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords;
import com.kms.katalon.core.model.FailureHandling;
import com.kms.katalon.core.testcase.TestCase;
import com.kms.katalon.core.testdata.TestData;
import com.kms.katalon.core.testobject.TestObject;
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords;
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords;
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords;
import com.kms.katalon.core.context.TestCaseContext

import groovy.lang.Binding;

public class JV {

	Binding binding = null;

	public Map<String, String> getSData() {
		Map<String, String> sData = new HashMap<String, String>();
		try {
			S_Object so = new S_Object();
			Class clazz = so.getClass();
			Field[]  fields = clazz.getDeclaredFields();
			for (Field f : fields) {
				sData.put(f.getName(), f.get(so).toString());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return sData;
	}

	public JV() {
		binding = new Binding(getSData());
	}

	public void click(String sObject) {
		WebUiBuiltInKeywords.click(findTestObject(binding.getVariable(sObject).toString()));
	}
}