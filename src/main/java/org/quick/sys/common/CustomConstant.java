package org.quick.sys.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CustomConstant {
	
	public static String webTitle ;
	
	@Value("${webTitle}")
	public  void setWebTitle(String webTitle) {
		CustomConstant.webTitle = webTitle;
	}
	
	
	

}
