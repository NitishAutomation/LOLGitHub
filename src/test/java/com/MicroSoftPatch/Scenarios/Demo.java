package com.MicroSoftPatch.Scenarios;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class Demo {
	
	/*private static final DateFormat sdf = new SimpleDateFormat("mm-dd-yyyy HH:mm:ss");
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");

	public static void main(String args[]){
		
		 Date date = new Date();
	        System.out.println(sdf.format(date));

	       // Calendar cal = Calendar.getInstance();
	       // System.out.println(sdf.format(cal.getTime()));

	        LocalDateTime now = LocalDateTime.now();
	       System.out.println(dtf.format(now));

	        //LocalDate localDate = LocalDate.now();
	       // String date=DateTimeFormatter.ofPattern("MM-dd-yyyy").format(localDate).toString();
	       //System.out.println("-->"+date);
	        
	        // System.out.println(DateTimeFormatter.ofPattern("yyy/MM/dd").format(localDate));
		
		//System.out.println(System.getProperty("user.dir"));
		File f=new File("./pdfScreenShot");
		System.out.println(f.exists());*/
	
	public static void main(String args[]){
		
		Demo obj=new Demo();
		if(obj.isFileDownloaded("c:/Users/"+System.getProperty("user.name")+"/Downloads/","USERMANUAL_internal.docx")){
			
			System.out.println("Present.");
			
		}else{
			
			System.out.println("Not present..");
		}
	}
		
		
	public boolean isFileDownloaded(String downloadPath, String fileName) {
		boolean flag = false;
	    File dir = new File(downloadPath);
	    File[] dir_contents = dir.listFiles();
	    
	     for (int i = 0; i < dir_contents.length; i++) {
	        if (dir_contents[i].getName().equals(fileName))
	            return flag=true;
	            }

	    return flag;
	}





}
	
	
	
	


