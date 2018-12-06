package com.flp.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.flp.dao.PriceDao;
import com.flp.model.PaymentHistory;
import com.flp.model.PriceList;
import com.flp.model.Subscription;
import com.flp.model.Transaction;
import com.flp.model.User;
/**
 * 
 * @author Amresh
 *
 */
@Repository
public class PriceDaoImpl  implements PriceDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<PriceList> getPriceList(Long subjectId, Long gradeId, Long boardId, Integer planType) {
	Session session = sessionFactory.getCurrentSession();
	String str = "FROM PriceList WHERE boardId = :boardId AND subjectId = :subjectId AND gradeId = :gradeId";
	if(planType != null){
		str = str+" AND planType = :planType";
	}
	Query query = session.createQuery(str);
	query.setParameter("boardId", boardId);
	query.setParameter("subjectId", subjectId);
	query.setParameter("gradeId", gradeId);
	if(planType != null){
		query.setParameter("planType", planType);
	}
	
	return query.list();
	}

	@Override
	public void saveOrUpdate(Transaction transaction) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(transaction);
		
	}

	@Override
	public Long save(Subscription subscription) {
		Session session = sessionFactory.getCurrentSession();
		return (Long) session.save(subscription);
	}

	@Override
	public Transaction getTransactionByOrderNo(String orderNo) {
		Session session = sessionFactory.getCurrentSession();
		String str = "FROM Transaction WHERE orderNo = :orderNo";	
		Query query = session.createQuery(str);
		query.setParameter("orderNo", orderNo);
		return (Transaction) query.uniqueResult();
	}

	@Override
	public boolean savePaymentHistory(PaymentHistory paymentHistory) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(paymentHistory);
		return true;
	}

	@Override
	public Subscription getSubscriptoinById(Long subscriptionId) {
		Session session = sessionFactory.getCurrentSession();
		String str = "FROM Subscription WHERE id = :subscriptionId";	
		Query query = session.createQuery(str);
		query.setParameter("subscriptionId", subscriptionId);
		return (Subscription) query.uniqueResult();
	}

	@Override
	public Subscription getSubscriptoinWithJoinById(Long subscriptionId) {
		Session session = sessionFactory.getCurrentSession();
		String str = "select distinct(s) FROM Subscription s inner join fetch s.user inner join fetch s.grade inner join fetch s.board left join fetch s.subject WHERE s.id = :subscriptionId";	
		Query query = session.createQuery(str);
		query.setParameter("subscriptionId", subscriptionId);
		return (Subscription) query.uniqueResult();
	}
	
	@Override
	public void saveOrUpdateSubscription(Subscription subscription) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(subscription);
		
	}

}
