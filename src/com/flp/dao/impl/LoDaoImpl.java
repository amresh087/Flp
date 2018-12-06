package com.flp.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.flp.dao.LODao;
import com.flp.model.LearnObjective;
import com.flp.model.Topic;
import com.flp.model.TopicGraph;
/**
 * 
 * @author Amresh
 *
 */
@Repository
public class LoDaoImpl extends GenericDaoImpl<LearnObjective, Long> implements LODao
{

	@SuppressWarnings("unchecked")
	@Override
	public List<LearnObjective> getLos(String topicIdString, Integer status)
	{
		
		
		String[] idList = topicIdString.split(",");		
		Long[] ids = new Long[idList.length];
		
		for(int i=0; i<idList.length; i++)
		{
			ids[i] = Long.parseLong(idList[i]);
		}
		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM LearnObjective WHERE status = :status AND topicId IN (:ids) ORDER BY serialOrder");
		query.setParameterList("ids", ids);
		query.setInteger("status", status);
		
		return query.list();
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public Long getlocount(Long topicId, Integer status)
	{ 
		
		 Session session = sessionFactory.getCurrentSession(); 
         Query countlo = session.createQuery("select count(*) FROM LearnObjective WHERE topicId=:topicId and status = :status ");
         countlo.setParameter("topicId", topicId); 
         countlo.setInteger("status", status);
         Long locount = (Long)countlo.uniqueResult(); 
       
		return locount;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TopicGraph> getTopicGraph(String topicIdString, String userId)
	{
		System.out.println("asfasfa");
		// TODO Auto-generated method stub
		List<TopicGraph> al=new ArrayList<TopicGraph>();
		try{
		/*String jan ="SELECT COUNT(f_correct_incorrect) AS coorect,  EXTRACT(YEAR FROM f_insertDate) AS OrderYear,"
		+" EXTRACT(MONTH FROM f_insertDate) AS OrderMonth "
		+" FROM t_user_question_answer"
		+" WHERE       EXTRACT(YEAR FROM f_insertDate)=       (SELECT YEAR(CURDATE())) "  
		+" AND   f_userId="+userId+" AND f_topicId="+topicIdString+" AND f_correct_incorrect=1 "
		+"  AND  EXTRACT(MONTH FROM f_insertDate) =1";
		
		
		String feb ="SELECT COUNT(f_correct_incorrect) AS coorect,  EXTRACT(YEAR FROM f_insertDate) AS OrderYear,"
		+" EXTRACT(MONTH FROM f_insertDate) AS OrderMonth "
		+" FROM t_user_question_answer"
		+"  WHERE       EXTRACT(YEAR FROM f_insertDate)=       (SELECT YEAR(CURDATE())) "  
		+" AND   f_userId="+userId+" AND f_topicId="+topicIdString+" AND f_correct_incorrect=1 "
		+" AND  EXTRACT(MONTH FROM f_insertDate) =2";
		
		
		String mar ="SELECT COUNT(f_correct_incorrect) AS coorect,  EXTRACT(YEAR FROM f_insertDate) AS OrderYear,"
		+" EXTRACT(MONTH FROM f_insertDate) AS OrderMonth "
		+" FROM t_user_question_answer"
		+" WHERE       EXTRACT(YEAR FROM f_insertDate)=       (SELECT YEAR(CURDATE())) "  
		+" AND   f_userId="+userId+" AND f_topicId="+topicIdString+" AND f_correct_incorrect=1 "
		+" AND  EXTRACT(MONTH FROM f_insertDate) =3";
		
		
		String apr ="SELECT COUNT(f_correct_incorrect) AS coorect,  EXTRACT(YEAR FROM f_insertDate) AS OrderYear,"
		+" EXTRACT(MONTH FROM f_insertDate) AS OrderMonth "
		+" FROM t_user_question_answer"
		+" WHERE       EXTRACT(YEAR FROM f_insertDate)=       (SELECT YEAR(CURDATE())) "  
		+" AND   f_userId="+userId+" AND f_topicId="+topicIdString+" AND f_correct_incorrect=1 "
		+" AND  EXTRACT(MONTH FROM f_insertDate) =4";
		
		
		String may ="SELECT COUNT(f_correct_incorrect) AS coorect,  EXTRACT(YEAR FROM f_insertDate) AS OrderYear,"
		+" EXTRACT(MONTH FROM f_insertDate) AS OrderMonth "
		+" FROM t_user_question_answer"
		+" WHERE       EXTRACT(YEAR FROM f_insertDate)=       (SELECT YEAR(CURDATE())) "  
		+" AND   f_userId="+userId+" AND f_topicId="+topicIdString+" AND f_correct_incorrect=1 "
		+" AND  EXTRACT(MONTH FROM f_insertDate) =5";
		
		
		
		String jun ="SELECT COUNT(f_correct_incorrect) AS coorect,  EXTRACT(YEAR FROM f_insertDate) AS OrderYear,"
		+" EXTRACT(MONTH FROM f_insertDate) AS OrderMonth "
		+" FROM t_user_question_answer"
		+" WHERE       EXTRACT(YEAR FROM f_insertDate)=       (SELECT YEAR(CURDATE())) "  
		+" AND   f_userId="+userId+" AND f_topicId="+topicIdString+" AND f_correct_incorrect=1 "
		+" AND  EXTRACT(MONTH FROM f_insertDate) =6";
		
		
		String jul ="SELECT COUNT(f_correct_incorrect) AS coorect,  EXTRACT(YEAR FROM f_insertDate) AS OrderYear,"
		+" EXTRACT(MONTH FROM f_insertDate) AS OrderMonth "
		+" FROM t_user_question_answer"
		+" WHERE       EXTRACT(YEAR FROM f_insertDate)=       (SELECT YEAR(CURDATE())) "  
		+" AND   f_userId="+userId+" AND f_topicId="+topicIdString+" AND f_correct_incorrect=1 "
		+" AND  EXTRACT(MONTH FROM f_insertDate) =7";
		
		
		String aug ="SELECT COUNT(f_correct_incorrect) AS coorect,  EXTRACT(YEAR FROM f_insertDate) AS OrderYear,"
		+" EXTRACT(MONTH FROM f_insertDate) AS OrderMonth "
		+"FROM t_user_question_answer"
		+" WHERE       EXTRACT(YEAR FROM f_insertDate)=       (SELECT YEAR(CURDATE())) "  
		+" AND   f_userId="+userId+" AND f_topicId="+topicIdString+" AND f_correct_incorrect=1 "
		+" AND  EXTRACT(MONTH FROM f_insertDate) =8";
		
		
		String sep ="SELECT COUNT(f_correct_incorrect) AS coorect,  EXTRACT(YEAR FROM f_insertDate) AS OrderYear,"
		+" EXTRACT(MONTH FROM f_insertDate) AS OrderMonth "
		+" FROM t_user_question_answer"
		+" WHERE       EXTRACT(YEAR FROM f_insertDate)=       (SELECT YEAR(CURDATE())) "  
		+" AND   f_userId="+userId+" AND f_topicId="+topicIdString+" AND f_correct_incorrect=1 "
		+" AND  EXTRACT(MONTH FROM f_insertDate) =9";
		
		String oct ="SELECT COUNT(f_correct_incorrect) AS coorect,  EXTRACT(YEAR FROM f_insertDate) AS OrderYear,"
		+" EXTRACT(MONTH FROM f_insertDate) AS OrderMonth "
		+" FROM t_user_question_answer"
		+" WHERE       EXTRACT(YEAR FROM f_insertDate)=       (SELECT YEAR(CURDATE())) "  
		+" AND   f_userId="+userId+" AND f_topicId="+topicIdString+" AND f_correct_incorrect=1 "
		+" AND  EXTRACT(MONTH FROM f_insertDate) =10";
		
		
		
		String nov ="SELECT COUNT(f_correct_incorrect) AS coorect,  EXTRACT(YEAR FROM f_insertDate) AS OrderYear,"
		+" EXTRACT(MONTH FROM f_insertDate) AS OrderMonth "
		+" FROM t_user_question_answer"
		+" WHERE       EXTRACT(YEAR FROM f_insertDate)=       (SELECT YEAR(CURDATE())) "  
		+" AND   f_userId="+userId+" AND f_topicId="+topicIdString+" AND f_correct_incorrect=1 "
		+"  AND  EXTRACT(MONTH FROM f_insertDate) =11";
		
		
		
		String dec ="SELECT COUNT(f_correct_incorrect) AS coorect,  EXTRACT(YEAR FROM f_insertDate) AS OrderYear,"
		+" EXTRACT(MONTH FROM f_insertDate) AS OrderMonth "
		+" FROM t_user_question_answer"
		+" WHERE       EXTRACT(YEAR FROM f_insertDate)=       (SELECT YEAR(CURDATE())) "  
		+" AND   f_userId="+userId+" AND f_topicId="+topicIdString+" AND f_correct_incorrect=1 "
		+" AND  EXTRACT(MONTH FROM f_insertDate) =12";
		//+"UNION ALL "+;
*/		
			
			
			
			String sql	=   "  SELECT COUNT(f_correct_incorrect) AS coorect,  EXTRACT(YEAR FROM f_insertDate) AS OrderYear, EXTRACT(MONTH FROM f_insertDate) AS OrderMonth                                              "+
			   "  FROM t_user_question_answer WHERE       EXTRACT(YEAR FROM f_insertDate)=       (SELECT YEAR(CURDATE()))  AND   f_userId=1066 AND f_topicId=18814 AND f_correct_incorrect=1               "+
			   "  AND  EXTRACT(MONTH FROM f_insertDate) =1                                                                                                                                                "+
			                                                                                                                                                                                 
			      "  UNION ALL  SELECT COUNT(f_correct_incorrect) AS coorect,  EXTRACT(YEAR FROM f_insertDate) AS OrderYear, EXTRACT(MONTH FROM f_insertDate) AS OrderMonth  FROM t_user_question_answer                 "+
			   "  WHERE       EXTRACT(YEAR FROM f_insertDate)=       (SELECT YEAR(CURDATE()))                                                                                                              "+
			   "  AND   f_userId=1066 AND f_topicId=18814 AND f_correct_incorrect=1  AND  EXTRACT(MONTH FROM f_insertDate) =2                                                                              "+
			   "  UNION ALL                                                                                                                                                                                "+
			   "  SELECT COUNT(f_correct_incorrect) AS coorect,  EXTRACT(YEAR FROM f_insertDate) AS OrderYear, EXTRACT(MONTH FROM f_insertDate) AS OrderMonth                                              "+
			   "  FROM t_user_question_answer WHERE       EXTRACT(YEAR FROM f_insertDate)=       (SELECT YEAR(CURDATE()))  AND   f_userId=1066 AND f_topicId=18814 AND f_correct_incorrect=1               "+
			   "  AND  EXTRACT(MONTH FROM f_insertDate) =3                                                                                                                                                 "+
			   "  UNION ALL                                                                                                                                                                                "+
			   "  SELECT COUNT(f_correct_incorrect) AS coorect,  EXTRACT(YEAR FROM f_insertDate) AS OrderYear, EXTRACT(MONTH FROM f_insertDate) AS OrderMonth                                              "+
			   "  FROM t_user_question_answer WHERE       EXTRACT(YEAR FROM f_insertDate)=       (SELECT YEAR(CURDATE()))                                                                                  "+
			   "  AND   f_userId=1066 AND f_topicId=18814 AND f_correct_incorrect=1  AND  EXTRACT(MONTH FROM f_insertDate) =4                                                                              "+
			   "  UNION ALL                                                                                                                                                                                "+
			   "   SELECT COUNT(f_correct_incorrect) AS coorect,  EXTRACT(YEAR FROM f_insertDate) AS OrderYear, EXTRACT(MONTH FROM f_insertDate) AS OrderMonth                                             "+
			   "   FROM t_user_question_answer WHERE       EXTRACT(YEAR FROM f_insertDate)=       (SELECT YEAR(CURDATE()))  AND                                                                            "+
			   "    f_userId=1066 AND f_topicId=18814 AND f_correct_incorrect=1  AND  EXTRACT(MONTH FROM f_insertDate) =5                                                                                  "+
			   "    UNION ALL                                                                                                                                                                              "+
			   "    SELECT COUNT(f_correct_incorrect) AS coorect,  EXTRACT(YEAR FROM f_insertDate) AS OrderYear, EXTRACT(MONTH FROM f_insertDate) AS OrderMonth                                            "+
			   "    FROM t_user_question_answer WHERE       EXTRACT(YEAR FROM f_insertDate)=       (SELECT YEAR(CURDATE()))  AND   f_userId=1066 AND f_topicId=18814                                       "+
			   "    AND f_correct_incorrect=1  AND  EXTRACT(MONTH FROM f_insertDate) =6                                                                                                                    "+
			   "    UNION ALL                                                                                                                                                                              "+
			   "    SELECT COUNT(f_correct_incorrect) AS coorect,  EXTRACT(YEAR FROM f_insertDate) AS OrderYear, EXTRACT(MONTH FROM f_insertDate) AS OrderMonth                                            "+
			   "    FROM t_user_question_answer WHERE       EXTRACT(YEAR FROM f_insertDate)=       (SELECT YEAR(CURDATE()))  AND   f_userId=1066                                                           "+
			   "    AND f_topicId=18814 AND f_correct_incorrect=1  AND  EXTRACT(MONTH FROM f_insertDate) =7                                                                                                "+
			   "    UNION ALL                                                                                                                                                                              "+
			   "    SELECT COUNT(f_correct_incorrect) AS coorect,  EXTRACT(YEAR FROM f_insertDate) AS OrderYear, EXTRACT(MONTH FROM f_insertDate)                                                          "+
			   "    AS OrderMonth FROM t_user_question_answer WHERE       EXTRACT(YEAR FROM f_insertDate)=       (SELECT YEAR(CURDATE()))  AND   f_userId=1066                                             "+
			   "    AND f_topicId=18814 AND f_correct_incorrect=1  AND  EXTRACT(MONTH FROM f_insertDate) =8                                                                                                "+
			   "    UNION ALL                                                                                                                                                                              "+
			   "    SELECT COUNT(f_correct_incorrect) AS coorect,  EXTRACT(YEAR FROM f_insertDate) AS OrderYear, EXTRACT(MONTH FROM f_insertDate) AS OrderMonth                                            "+
			   "    FROM t_user_question_answer WHERE       EXTRACT(YEAR FROM f_insertDate)=       (SELECT YEAR(CURDATE()))                                                                                "+
			   "    AND   f_userId=1066 AND f_topicId=18814 AND f_correct_incorrect=1  AND  EXTRACT(MONTH FROM f_insertDate) =9                                                                            "+
			   "    UNION ALL                                                                                                                                                                              "+
			   "    SELECT COUNT(f_correct_incorrect) AS coorect,  EXTRACT(YEAR FROM f_insertDate) AS OrderYear, EXTRACT(MONTH FROM f_insertDate) AS OrderMonth                                            "+
			   "    FROM t_user_question_answer WHERE       EXTRACT(YEAR FROM f_insertDate)=       (SELECT YEAR(CURDATE()))  AND                                                                           "+
			   "    f_userId=1066 AND f_topicId=18814 AND f_correct_incorrect=1  AND  EXTRACT(MONTH FROM f_insertDate) =10                                                                                 "+
			   "    UNION ALL                                                                                                                                                                              "+
			   "    SELECT COUNT(f_correct_incorrect) AS coorect,  EXTRACT(YEAR FROM f_insertDate) AS OrderYear,                                                                                           "+
			   "    EXTRACT(MONTH FROM f_insertDate) AS OrderMonth  FROM t_user_question_answer WHERE       EXTRACT(YEAR FROM f_insertDate)=                                                               "+
			   "     (SELECT YEAR(CURDATE()))  AND   f_userId=1066 AND f_topicId=18814 AND f_correct_incorrect=1                                                                                           "+
			   "     AND  EXTRACT(MONTH FROM f_insertDate) =11                                                                                                                                             "+
			   "    UNION ALL                                                                                                                                                                              "+
			   "    SELECT COUNT(f_correct_incorrect) AS coorect,  EXTRACT(YEAR FROM f_insertDate) AS OrderYear,                                                                                           "+
			   "    EXTRACT(MONTH FROM f_insertDate) AS OrderMonth  FROM t_user_question_answer WHERE                                                                                                      "+
			   "    EXTRACT(YEAR FROM f_insertDate)=       (SELECT YEAR(CURDATE()))  AND   f_userId=1066 AND f_topicId=18814                                                                               "+
			   "    AND f_correct_incorrect=1  AND  EXTRACT(MONTH FROM f_insertDate) =12         ";   
			
			
			
		
		// String sql=jan+" UNION ALL "+feb+" UNION ALL "+mar+" UNION ALL "+apr+" UNION ALL "+may+" UNION ALL "+jun+" UNION ALL "+jul+" UNION ALL "+aug+" UNION ALL "+sep+" UNION ALL "+oct+" UNION ALL "+nov+" UNION ALL "+dec;
		//String sql=may;
		 
		System.out.println("asfasfasqlsqlsqlsql="+sql);
		Session session = sessionFactory.getCurrentSession(); 
		SQLQuery query = session.createSQLQuery(sql);
		
		
		
		
		List<Object[]> rows = query.list();
		
		System.out.println(rows.size()+"================");
		for(Object[] row : rows){
			TopicGraph emp = new TopicGraph();
			
			if(null!=row[0]){
			emp.setCoorect(Integer.parseInt(row[0].toString()));}
			if(null!=row[1]){
			emp.setOrderYear(row[1].toString());}
			if(null!=row[2]){
			emp.setOrderMonth(row[2].toString());}
			System.out.println(emp);
			al.add(emp);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		//System.out.println("::::ll:::::::::::::"+(int)query.list().get(0));
		
		
		
		
		
		return al;
	}

}
