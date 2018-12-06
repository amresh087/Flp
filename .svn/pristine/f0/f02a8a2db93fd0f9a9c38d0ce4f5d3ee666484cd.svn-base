
package com.flp.util;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.TimeZone;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.opc.internal.FileHelper;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

import com.flp.model.School;
import com.flp.model.Session;

@Component
@PropertySource("classpath:application.properties")
public class Utility implements ServletContextAware
{
	private static org.apache.log4j.Logger log = Logger.getLogger(Utility.class);
	public static String resourcesFolderPath = null;
	public String rootPath = null;
	public static String profilePath = null;
	public static String staticContentPath=null;
	private volatile static boolean isListAlreadyInitialized = false;
	private static List<School> schoolList = null;
	private volatile static boolean isSessionListAlreadyInitialized = false;
	private static List<Session> sessionList = null;
	
	private Date date=null;
	
	
	static TimeZone istTimeZone = TimeZone.getTimeZone("Asia/Calcutta");
	
	@Resource
	private Environment env; 
	
	public static String convertDateToStringWithZone(Date date )
	{        //  System.out.println("Pramod.........................");  
		String currentDate = null;
		try
		{ 
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd h:mm:ss a Z"); 
			sdf.setTimeZone(istTimeZone); 
			currentDate = sdf.format(date); 
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return currentDate;
	} 
	public static Date convertStringToDateWithZone(String dateFormate)
	{
		Date currentDate = null;
		
		
		
		try
		{ 
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd h:mm:ss a Z"); 
			sdf.setTimeZone(istTimeZone); 
			currentDate = sdf.parse(dateFormate);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return currentDate;
	}
	public static String convertDateToString(Date date, String format)
	{
		String currentDate = null;
		try
		{
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			currentDate = sdf.format(date);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return currentDate;
	}

	public static Date convertStringToDate(String dateFormate, String date)
	{
		Date currentDate = null;
		try
		{
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormate);
			currentDate = sdf.parse(date);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return currentDate;
	}

	public static Double getDecimalFormat(Object obj, Integer decimalDigit)
	{
		StringBuilder format = new StringBuilder("##.00");
		for (int i = 0; i < decimalDigit; i++)
		{
			format.append("0");
		}
		Double val = Double.parseDouble(obj.toString());
		DecimalFormat df = new DecimalFormat(format.toString());
		val = Double.parseDouble(df.format(val));
		return val;
	}

	@Override
	public void setServletContext(ServletContext servletContext)
	{
		resourcesFolderPath = servletContext.getRealPath("/resources");
		rootPath = servletContext.getRealPath("/");
		staticContentPath= env.getRequiredProperty("flp.static.rootPath");
		profilePath=env.getRequiredProperty("flp.pImage");
	}

	public static void saveImageInResources(MultipartFile file, File dir, String fileName)
	{
		if (!file.isEmpty())
		{
			try
			{
				byte[] bytes = file.getBytes();

				// Creating the directory to store file

				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath() + File.separator + fileName);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

			}
			catch (Exception e)
			{

			}
		}
	}

	public static String getUserIPAddress(HttpServletRequest request) throws IOException
	{
		String ipAddress = request.getHeader("Remote_Addr");
		if (null == ipAddress || ipAddress.trim().length() == 0)
		{
			ipAddress = request.getHeader("HTTP_X_FORWARDED_FOR");
			if (ipAddress == null)
			{
				ipAddress = request.getRemoteAddr();
			}
		}
		// String ip=InetAddress.getLocalHost().toString();

		return ipAddress;
	}

	public static void warn(String msg)
	{
		log.warn(msg);
	}

	public static void info(String msg)
	{
		log.info(msg);
	}

	public static void error(String msg)
	{
		log.error(msg);
	}

	public static void debug(String msg)
	{
		log.debug(msg);
	}

	public static String encrypt(String str) throws Exception
	{
		return DatatypeConverter.printBase64Binary(str.getBytes());
	}

	public static String decrypt(String encryptedData) throws Exception
	{
		return new String(DatatypeConverter.parseBase64Binary(encryptedData));
	}

	public static void main(String agrs[]) throws Exception
	{
		
		
		DecimalFormat df = new DecimalFormat("##.00");
		Double val = Double.parseDouble(df.format(2.6666));
		System.out.println(val);
	}

	public static synchronized void initializeSchoolList(List<School> schoolList1)
	{

		if (isListAlreadyInitialized == false)
		{
			schoolList = new ArrayList<School>();
			schoolList.addAll(schoolList1);
			isListAlreadyInitialized = true;
		}

	}

	public static void addSchoolInList(School school)
	{
		schoolList.add(school);
	}

	public static List<School> getAllSchoolList()
	{
		return schoolList;
	}

	public static synchronized void initializeSessionList(List<Session> sessionList1)
	{

		if (isSessionListAlreadyInitialized == false)
		{
			sessionList = new ArrayList<Session>();
			sessionList.addAll(sessionList1);
			isSessionListAlreadyInitialized = true;
		}

	}

	public static void addSessionInList(Session session)
	{
		sessionList.add(session);
	}

	public static List<Session> getAllSessionList()
	{
		return sessionList;
	}

	public static Byte[] convertStringToByteUTF8(String string)
	{
		try
		{
			byte[] byteArray = string.getBytes("UTF-8");
			return ArrayUtils.toObject(byteArray);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return null;
	}

	public static String convertByteUTF8ToString(Byte[] byteArray)
	{
		return new String(ArrayUtils.toPrimitive(byteArray	), Charset.forName("UTF-8"));
	}

	public static String saveImageToDisk(MultipartFile file, Long questionId, Long answerId, Long userId, String questionAnswerTag) throws IOException
	{
		if(userId == null)
			throw new IOException("Unable to create image name: userId missing");		// Throwing checked exception
		
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyhhmmssSSS");
		String date = sdf.format(new Date());

		String originalFileName = file.getOriginalFilename();
		String fileFormat = originalFileName.substring(originalFileName.lastIndexOf(".") + 1, originalFileName.length());

		String directoryString = getValueFromPropertyFile("flp.qa.images");
		//String directoryString = (propertyDirectoryString.charAt(propertyDirectoryString.length()-1) != '/' ? propertyDirectoryString+"/" : propertyDirectoryString);		//"D:/FLP_RESOURCES/QA_IMAGES"
		File directory = new File(directoryString);

		if (!directory.exists())
			directory.mkdirs();				// using mkdirs() instead of mkdir()

		String fileName = null;
		String fileFullName = null;

		if (questionAnswerTag.equalsIgnoreCase("Q"))				// Q17_U256_21082015034501270.jpg
		{
			
			fileName = questionAnswerTag + String.valueOf(questionId) + "_U" + userId + "_" + date + "." + fileFormat;
			fileFullName = directoryString + "/"+fileName;
		}
		else if (questionAnswerTag.equalsIgnoreCase("A"))			// Q17_A61_U256_21082015034501363.jpg
		{
			fileName = "Q" + questionId + "_" + questionAnswerTag + String.valueOf(answerId) + "_U" + userId + "_" + date + "." + fileFormat;
			fileFullName = directoryString + "/"+fileName;
		}else if (questionAnswerTag.equalsIgnoreCase("H"))			// Q17_A61_H256_21082015034501363.jpg
		{
			fileName = "Q" + questionId + "_" + questionAnswerTag + String.valueOf(answerId) + "_H" + userId + "_" + date + "." + fileFormat;
			fileFullName = directoryString + "/"+fileName;
		}else if (questionAnswerTag.equalsIgnoreCase("E"))			// Q17_A61_E256_21082015034501363.jpg
		{
			fileName = "Q" + questionId + "_" + questionAnswerTag + String.valueOf(answerId) + "_E" + userId + "_" + date + "." + fileFormat;
			fileFullName = directoryString + "/"+fileName;
		}
		System.out.println("path=="+fileFullName);
		InputStream is = new ByteArrayInputStream(file.getBytes());
		BufferedImage bi = ImageIO.read(is);

		ImageIO.write(bi, fileFormat, new File(fileFullName));
		is.close();
		
		return fileName;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static List<String> readFromZipAndSaveToDisk(MultipartFile zipFile, Long schoolId, Long moduleId, Long boardId) throws IOException
	{		
		List<String> fileNameList = new ArrayList<String>();

		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyhhmmssSSS");
		String date = sdf.format(new Date());
		
		ZipInputStream zis = new ZipInputStream(zipFile.getInputStream());
		byte[] bufferSize = new byte[1024];
		
		ZipEntry ze;
		
		String resourceDirectoryName = getValueFromPropertyFile("flp.modules");				//"D:/FLP_RESOURCES/MODULES";
		//String resourceDirectoryName = propertyDirectoryString; /*(propertyDirectoryString.charAt(propertyDirectoryString.length()-1) != '/' ? propertyDirectoryString+"/" : propertyDirectoryString);			//"D:/FLP_RESOURCES/MODULES/";
		String moduleFolderName;
		
		if(schoolId != null)																	// For school
			moduleFolderName = "M"+String.valueOf(moduleId)+"_S"+String.valueOf(schoolId)+"_"+date;
		else if(schoolId == null && boardId != null)											// For admin
			moduleFolderName = "M"+String.valueOf(moduleId)+"_B"+String.valueOf(boardId)+"_"+date;
		else
			throw new IOException("Unable to create module folder: either schoolId or boardId missing");		// Throwing checked exception
		
		String resourceAndModuleFolderDirectoryName = resourceDirectoryName+"/"+moduleFolderName;			
		String originalFileName = zipFile.getOriginalFilename();
		File fullDirectory;
		String directoryWithoutFileName;
		ByteArrayOutputStream baos;
		FileOutputStream fos;
		
		while((ze = zis.getNextEntry()) != null)
		{
			if(ze.isDirectory())											// For directory
			{
				System.out.println("Directory Name: "+ze.getName());
				
				fullDirectory = new File(resourceAndModuleFolderDirectoryName+"/"+ze.getName());
				
				if(!fullDirectory.exists())
					fullDirectory.mkdirs();		// using mkdirs() instead of mkdir()
				
				System.out.println("Created Directory: "+fullDirectory.getName());
			}
			else															// For files
			{
				System.out.println("Directory with file name: "+ze.getName());
				
				if(StringUtils.countMatches(ze.getName(), "/") > 0)			// If file is inside directory(s)
					directoryWithoutFileName = resourceAndModuleFolderDirectoryName + "/" + ze.getName().substring(0, ze.getName().lastIndexOf("/"));
				else														// If file is not inside any directory
					directoryWithoutFileName = resourceAndModuleFolderDirectoryName;
				
				fullDirectory = new File(directoryWithoutFileName);
				
				if(!fullDirectory.exists())					
					fullDirectory.mkdirs();		// using mkdirs() instead of mkdir()
				
				System.out.println("Directory Name: "+directoryWithoutFileName);
				
				baos = new ByteArrayOutputStream(1024);
				int length;
				while((length = zis.read(bufferSize)) != -1)
				{
					baos.write(bufferSize, 0, length);
				}
				
				fos = new FileOutputStream(resourceAndModuleFolderDirectoryName+"/"+ze.getName());
				baos.writeTo(fos); 																					// Saving file in directory
				
				fos.close();
				baos.close();
				
				fileNameList.add(moduleFolderName+"/"+ze.getName());							// Adding file name in array
			}
		}
		return fileNameList;
	}

	public static String convertLongListToString(List<Long> subjectIds)
	{
		String str = "";
		for (Long id : subjectIds)
		{
			str = str + id.longValue() + ",";
		}
		return str.substring(0, str.length() - 1);
	}

	public static Date getCurrentDate()
	{
		Properties prop = new Properties();
		try
		{
			InputStream inputStream = FileHelper.class.getClassLoader().getResourceAsStream("application.properties");
			prop.load(inputStream);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		Calendar cal = Calendar.getInstance();
		/*Integer hour = cal.get(Calendar.HOUR_OF_DAY) + Integer.parseInt(prop.getProperty("hours.title"));
		Integer minute = cal.get(Calendar.MINUTE) + Integer.parseInt(prop.getProperty("minute.title"));*/
		/*cal.set(Calendar.HOUR, hour);
		cal.set(Calendar.MINUTE, minute);*/
		cal.add(Calendar.HOUR, Integer.parseInt(prop.getProperty("hours.title")));
		cal.add(Calendar.MINUTE, Integer.parseInt(prop.getProperty("minute.title")));
		return cal.getTime();
	}
	
	public static String getValueFromPropertyFile(String key)
	{
		Properties properties = new Properties();
		InputStream inputStream = Utility.class.getClassLoader().getResourceAsStream("application.properties");
		try
		{
			properties.load(inputStream);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return properties.getProperty(key).trim();
	}
	
}
