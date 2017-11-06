package yahoo;

import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.net.URL;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import atu.testng.reports.listeners.ATUReportsListener;
import atu.testng.reports.listeners.ConfigurationListener;
import atu.testng.reports.listeners.MethodListener;

@Listeners({ATUReportsListener.class,MethodListener.class,ConfigurationListener.class})
public class ReTest extends MainClass
{
  	
	//DesiredCapabilities ds;
	
	{
		System.setProperty("atu.reporter.config", "e:/10am_oct/atu.properties");
	}
  @Test
  @Parameters({"browser"})
  public void retesting(String br) throws Exception
  {
	  if(br.matches("firefox"))
	  {
		driver=new FirefoxDriver();
		 // ds=DesiredCapabilities.firefox();
		 // ds.setPlatform(Platform.WINDOWS);
				  
	  }
	  if(br.matches("chrome"))
	  {
		 System.setProperty("webdriver.chrome.driver","d:\\chromedriver.exe");
		  driver=new ChromeDriver();
		 // ds=DesiredCapabilities.chrome();
		 // ds.setPlatform(Platform.WINDOWS);
	  }
	                           //address of hub, desiredcap obj
	  //driver=new RemoteWebDriver(new URL("http://192.168.1.156:1234/wd/hub"), ds);
	  FileInputStream fin=new FileInputStream("e:\\10am_oct\\testdata.xlsx");
	  XSSFWorkbook wb=new XSSFWorkbook(fin);
	  XSSFSheet ws=wb.getSheet("retest");
	  Row row;
	  String torun,classname,methodname;
	  for(int r=1;r<=ws.getLastRowNum();r++)
	  {
		  row=ws.getRow(r);
		  torun=row.getCell(4).getStringCellValue();
		  if(torun.matches("yes"))
		  {
			  classname=row.getCell(2).getStringCellValue();
			  methodname=row.getCell(3).getStringCellValue();
			  
			  Class c=Class.forName(classname);
			  Method m=c.getMethod(methodname,null);
			  Object obj=c.newInstance();
			  m.invoke(obj,null);
		  }
	  }
	
  }
}



	 /* Home h=new Home(driver);	 
	  h.createacc();
	  h.login();
	  
	  Inbox i=new Inbox(driver);
	  i.deletemail();
	  
	  Compose c=new Compose(driver);
	  c.signout();*/











