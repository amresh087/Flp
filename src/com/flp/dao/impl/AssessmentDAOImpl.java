package com.flp.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.flp.dao.AssessmentDAO;
import com.flp.model.Answer;
import com.flp.model.Question;
import com.flp.model.UserForm;
/**
 * 
 * @author Amresh
 *
 */
@Repository
public class AssessmentDAOImpl implements AssessmentDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public short getSmartScore(Long objectId, String objectType, long id,
			Long gradeId) {
		short ss = 0;
		try {
			Session session = sessionFactory.getCurrentSession();
			SQLQuery query;
			String q;
			switch (objectType.toUpperCase()) {
			case "ALL":
				q = "SELECT ROUND(AVG(ss.f_smartScore)) FROM t_chapter c JOIN t_topic t ON t.`f_chapter_id`=c.`f_id` JOIN t_topic_smart_score ss ON ss.f_topicId=t.`f_id` WHERE  ss.f_userId="+id+" AND c.`f_grade_id`=1 AND c.`f_board_id`=1";
				break;
			case "SUBJECT":
				q = "SELECT ROUND(AVG(ss.f_smartScore)) FROM t_chapter c JOIN t_topic t ON t.`f_chapter_id`=c.`f_id` JOIN t_topic_smart_score ss ON ss.f_topicId=t.`f_id` WHERE c.`f_subject_id`=1 AND ss.f_userId="+id+" AND c.`f_grade_id`=1 AND c.`f_board_id`=1";
				break;

			case "CHAPTER":
				q = "SELECT ROUND(AVG(ss.f_smartScore)) FROM t_chapter c JOIN t_topic t ON t.`f_chapter_id`=c.`f_id` JOIN t_topic_smart_score ss ON ss.f_topicId=t.`f_id` WHERE c.`f_id`=1 AND ss.f_userId="+id+" AND c.`f_grade_id`=1 AND c.`f_board_id`=1";

				break;

			case "TOPIC":
				q = "0";
				break;
			default:
				throw new Exception(
						"Invalid Argument Exception. only 'ALL', 'SUBJECT', 'CHAPTER' or 'TOPIC' is allowed as objectType");
			}

			query = session.createSQLQuery(q);
			ss = Short.parseShort(query.list().get(0).toString());

		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ss;
	}

	@Override
	public Question getNextQuestion(int ss, long topicId, long id) {
		Question question = null;

		try {
			Session session = sessionFactory.getCurrentSession();
			Query q = session.createSQLQuery("call getNextQuestion( :ss,:id , :topicId )").addEntity(Question.class);
			q.setParameter("ss", ss);
			q.setParameter("id",id);
			q.setParameter("topicId", topicId);
			
			question=(Question) q.list().get(0);
			//question.setQuestionAnswer(getAnswerSet(question.getId()));
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return question;
	}
	
	@Override
	public List<Answer> getAnswerSet(long questionId)
	{
		List<Answer> answerSet=null;
		try {
			Session session=sessionFactory.getCurrentSession();
			Query q=session.createQuery("from Answer where questionId=:questionId");
			q.setParameter("questionId", questionId);
			
			answerSet=q.list();
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		return answerSet;
	}

	/* (non-Javadoc)
	 * @param questionType//  values must be 0:Fresh, 1:Correct, 2:Wrong, 3:UnAttempted Questions
	 * @see com.flp.dao.AssessmentDAO#getQuestion(long, int, long[])
	 */
	@Override
	public Question getQuestion(long userId, int questionType, long[] topicIds) {
		Question question=null;
		
		try {
			Session session=sessionFactory.getCurrentSession();
			StringBuilder sb=new StringBuilder();
			for (int i = 0; i < topicIds.length; i++) {
				if(i<topicIds.length-1)
				sb.append(topicIds[i]+",");
				else
					sb.append(topicIds[i]);
			}
			Query query=null;
			String q=null;
			switch (questionType) {
			case 0:
				q="SELECT q.`f_id` FROM t_question q JOIN `t_question_topic` qt ON q.`f_id`=qt.`f_question_id` JOIN t_user_question_answer uq ON uq.`f_question_id`=qt.`f_question_id` WHERE uq.`f_user_id`="+userId+" AND uq.`f_topic_id` IN ("+sb+") ORDER BY RAND() LIMIT 0,1";
				break;
			case 1:
				q="SELECT q.`f_id` FROM t_question q JOIN `t_question_topic` qt ON q.`f_id`=qt.`f_question_id` JOIN t_user_question_answer uq ON uq.`f_question_id`=qt.`f_question_id` WHERE uq.`f_user_id`="+userId+"  AND uq.`f_topic_id` IN ("+sb+") AND uq.`f_is_attempted`=1 AND uq.`f_correct_incorrect`=1";
				break;
			case 2:
				q="SELECT q.`f_id` FROM t_question q JOIN `t_question_topic` qt ON q.`f_id`=qt.`f_question_id` JOIN t_user_question_answer uq ON uq.`f_question_id`=qt.`f_question_id` WHERE uq.`f_user_id`="+userId+"  AND uq.`f_topic_id` IN ("+sb+") AND uq.`f_is_attempted`=1 AND uq.`f_correct_incorrect`=0 ORDER BY RAND() LIMIT 0,1";
				break;
			case 3:
				q="SELECT q.`f_id` FROM t_question q JOIN `t_question_topic` qt ON q.`f_id`=qt.`f_question_id` JOIN t_user_question_answer uq ON uq.`f_question_id`=qt.`f_question_id` WHERE uq.`f_user_id`="+userId+"  AND uq.`f_topic_id` IN ("+sb+") AND uq.`f_is_attempted`=0 ORDER BY RAND() LIMIT 0,1";
				break;
			default:
				throw new Exception("Illegul argument for question type. Valid once are 0, 1, 2 or 3");
			}
			
			query=session.createSQLQuery(q);
			question=(Question) session.get(Question.class, Long.parseLong(query.list().get(0).toString()));
			question.setAnswers(getAnswerSet((long) question.getId()));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return question;
	}

	@Override
	public int saveQuestion(String topicId, String questionId,
			String isAttempted, String userAnswer, String isCorrect,
			String timeTaken, int ss, UserForm user) {
		try {
			Session session=sessionFactory.getCurrentSession();
			SQLQuery q=session.createSQLQuery("select saveQuestion("+ss+","+topicId+","+user.getId()+","+user.getGradeId()+","+questionId+",'"+userAnswer+"',"+isAttempted+","+isCorrect+","+timeTaken+")");
			
			return Integer.parseInt(q.list().get(0).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	

}
