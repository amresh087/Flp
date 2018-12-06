
<%@ page import="java.sql.*" %>
<%
 /* final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	    final String DB_URL = "jdbc:mysql://localhost:3306/flp_new?useUnicode=true&characterEncoding=UTF-8";

	   //  Database credentials
	   final String USER = "root";
	   final String PASS = "bedrok"; 
	   Connection conn = null;
	   Statement stmt = null;
	   Statement stmt1 = null;
	   PreparedStatement pstmt = null;
	   try{
	     
	      Class.forName("com.mysql.jdbc.Driver");

	     
	     
	      conn = DriverManager.getConnection(DB_URL,USER,PASS);

	      
	      stmt = conn.createStatement();
	      stmt1 = conn.createStatement();
	  
	      
	      String sql,sql1;
	      sql = "SELECT * from t_question";
	     
	      
	      ResultSet rs = stmt.executeQuery(sql);

	    
	      while(rs.next()){
	         //Retrieve by column name
	    	  int id  = rs.getInt("f_id");
		        
		         String desci  = rs.getString("f_description");
		   
		         
		         
		         if(desci!=null && desci.contains("http://iperformkonnect.classteacher.com/data"))
		         {
		        
		        
		         String update_description= desci.replace("http://iperformkonnect.classteacher.com/data", "http://iperformkonnect.classteacher.com/DATA-STATIC/data");
		       
		         sql1 = "UPDATE t_question SET f_description=? WHERE f_id=?";
		         
		    
		         pstmt = conn.prepareStatement(sql1);
		         
		         
		         pstmt.setString(1,update_description);
		         pstmt.setInt(2,id);
		         int p=pstmt.executeUpdate();
		        out.println("--------"+p);
		         
		         
		         
		         
		         
		         }
	         
	  
	         

	      }
	      //STEP 6: Clean-up environment
	      rs.close();
	      stmt1.close();
	      stmt.close();
	      conn.close();
	   }catch(SQLException se){
	      //Handle errors for JDBC
	      se.printStackTrace();
	   }catch(Exception e){
	      //Handle errors for Class.forName
	      e.printStackTrace();
	   }finally{
	      //finally block used to close resources
	      try{
	         if(stmt!=null)
	            stmt.close();
	      }catch(SQLException se2){
	      }// nothing we can do
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }//end finally try
	   }//end try
	out.println("Goodbye!");
	 */
	
	
	
	%>