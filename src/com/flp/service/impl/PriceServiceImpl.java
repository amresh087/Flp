package com.flp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flp.dao.PriceDao;
import com.flp.model.PaymentHistory;
import com.flp.model.PriceList;
import com.flp.model.Subscription;
import com.flp.model.Transaction;
import com.flp.service.PriceService;

@Service
public class PriceServiceImpl implements PriceService {
	@Autowired
	private PriceDao priceDao;

	@Transactional
	@Override
	public List<PriceList> getPriceList(Long subjectId, Long gradeId, Long boardId, Integer planType) {
		try{
			return priceDao.getPriceList(subjectId, gradeId, boardId, planType);
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
		return null;
	}

	@Transactional
	@Override
	public void saveOrUpdate(Transaction transaction) {
		try{
			priceDao.saveOrUpdate(transaction);
		}catch(Exception e){
			
		}
		
	}

	@Transactional
	@Override
	public Long save(Subscription subscription) {
		return priceDao.save(subscription);
	}

	@Transactional
	@Override
	public Transaction getTransactionByOrderNo(String orderNo) {
		return priceDao.getTransactionByOrderNo(orderNo);
	}

	@Transactional
	@Override
	public boolean savePaymentHistory(PaymentHistory paymentHistory) {
		return priceDao.savePaymentHistory(paymentHistory);
	}

	@Transactional
	@Override
	public Subscription getSubscriptoinById(Long subscriptionId) {
		return priceDao.getSubscriptoinById(subscriptionId);
	}

	@Transactional
	@Override
	public void saveOrUpdateSubscription(Subscription subscription) {
		priceDao.saveOrUpdateSubscription(subscription);
		
	}

	@Override
	@Transactional
	public Subscription getSubscriptoinWithJoinById(Long subscriptionId)
	{
		return priceDao.getSubscriptoinWithJoinById(subscriptionId);
	}

}
