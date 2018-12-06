package com.flp.service;

import java.util.List;

import com.flp.model.PaymentHistory;
import com.flp.model.PriceList;
import com.flp.model.Subscription;
import com.flp.model.Transaction;

public interface PriceService {

	List<PriceList> getPriceList(Long subjectId, Long gradeId, Long boardId, Integer planType);

	void saveOrUpdate(Transaction transaction);

	Long save(Subscription subscription);

	Transaction getTransactionByOrderNo(String orderId);

	boolean savePaymentHistory(PaymentHistory paymentHistory);

	Subscription getSubscriptoinById(Long subscriptionId);

	void saveOrUpdateSubscription(Subscription subscription);

	Subscription getSubscriptoinWithJoinById(Long subscriptionId);

}
