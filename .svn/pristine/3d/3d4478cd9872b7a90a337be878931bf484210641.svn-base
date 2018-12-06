package com.flp.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.flp.annotations.LoginAuthentication;
import com.flp.model.Board;
import com.flp.model.PaymentHistory;
import com.flp.model.PriceList;
import com.flp.model.Subject;
import com.flp.model.Subscription;
import com.flp.model.Transaction;
import com.flp.model.User;
import com.flp.model.UserForm;
import com.flp.service.PriceService;
import com.flp.service.StudentCommonService;
import com.flp.service.UserService;
import com.flp.util.Utility;

/** @author Amresh */
@Controller
public class PaymentController
{

	@Autowired
	private UserService userService;

	@Autowired
	private PriceService priceService;

	@Autowired
	private StudentCommonService studentCommonService;

	/** @param map
	 * @param userId
	 * @param subjectId
	 * @param planType
	 * @param boardId
	 * @param payable_amount
	 * @param gradeId
	 * @param request
	 * @param response
	 * @return */
	@RequestMapping(value = "/payment/upgrade.htm")
	public String makePayment(Map<String, Object> map, @RequestParam(value = "userId", required = true) Long userId, @RequestParam(value = "subject", required = true) Long subjectId, @RequestParam(value = "planType", required = true) Integer planType, @RequestParam(value = "board", required = true) Long boardId, @RequestParam(value = "payable_amount", required = true) Double payable_amount, @RequestParam(value = "grade", required = true) Long gradeId, HttpServletRequest request, HttpServletResponse response)
	{

		Integer discount_amount = 0;
		String promocode = "";

		User user = userService.getUserById(userId);
		if (user != null)
		{
			String orderNo = String.valueOf(System.currentTimeMillis());
			List<PriceList> priceList = priceService.getPriceList(subjectId, gradeId, boardId, planType);
			if (priceList != null)
			{
				Double amount = priceList.get(0).getPrice();

				if (discount_amount > 0)
				{
					payable_amount = amount - discount_amount;
				}

				// saving in subscription

				Subscription subscription = new Subscription();
				subscription.setBoardId(boardId);
				subscription.setGradeId(gradeId);
				subscription.setSubjectId(subjectId);
				subscription.setUserId(user.getId());
				subscription.setPlanType(planType);
				Calendar cal = Calendar.getInstance();
				cal.setTime(new Date());
				if (planType == Subscription.PLAN_TYPE_ANNUAL)
				{
					cal.add(Calendar.DATE, 365); // add 120 days
				}
				else if (planType == Subscription.PLAN_TYPE_HALF_YEARLY)
				{
					cal.add(Calendar.DATE, 180); // add 365 days
				}
				else if (planType == Subscription.PLAN_TYPE_QUATERLY)
				{
					cal.add(Calendar.DATE, 90);
				}

				subscription.setExpiryDate(cal.getTime());
				subscription.setStartDate(new Date());
				subscription.setStatus(Subscription.STATUS_INACTIVE);
				Long subscriptionId = priceService.save(subscription);

				// saving in transaction
				if (subscriptionId != null)
				{
					Transaction transaction = new Transaction();
					transaction.setUseId(user.getId());
					transaction.setAmount(amount);
					transaction.setDiscount(0d);
					transaction.setSubscriptionId(subscriptionId);
					transaction.setOrderNo(orderNo);
					transaction.setPayableAmount(payable_amount);
					transaction.setPromoCode("NA");
					transaction.setStatus(Transaction.STATUS_PENDING);
					transaction.setInsertedDate(new Date());
					priceService.saveOrUpdate(transaction);

					// saving user subscription id

					user.setSubscriptionid(subscriptionId);
					userService.SaveOrUpdate(user);

					map.put("sid", subscriptionId);
					map.put("orderNo", orderNo);
					map.put("firstName", user.getFirstName());
					map.put("lastName", user.getLastName());
					map.put("email", user.getEmailId());
					map.put("mobile", user.getMobileNo());
					int price = payable_amount.intValue();
					map.put("orderPrice", price + "");

					map.put("userId", user.getId());
					map.put("promocode", promocode);
					String appUrl = Utility.getValueFromPropertyFile("appUrl");
					// String appURL = servletUt.getAppURL(request);
					map.put("returnURL", appUrl + "/ccavenue/payment/txn/return");
				}

			}
		}

		return "upgradeAccount/paymentProcess";

	}

	/** @param map
	 * @param request
	 * @return */
	@RequestMapping(value = "/ccavenue/payment/txn/return")
	public String processUpgradeCCAvenueReturnURL(Map<String, Object> map, HttpServletRequest request)
	{
		Long userId = null;
		String promoCode = "";

		// String authDesc = "Y";
		String authDesc = request.getParameter("AuthDesc");
		String OrderId = request.getParameter("Order_Id");
		Transaction transaction = priceService.getTransactionByOrderNo(OrderId);

		PaymentHistory paymentHistory = new PaymentHistory();
		paymentHistory.setBankAuthDesc(authDesc);
		paymentHistory.setBankRespMsg(request.getParameter("bankRespMsg"));
		paymentHistory.setChecksum(request.getParameter("Checksum"));
		paymentHistory.setOrderNo(transaction.getOrderNo());
		paymentHistory.setAmount(Double.parseDouble(request.getParameter("Amount")));
		paymentHistory.setUserId(transaction.getUseId());
		paymentHistory.setInsertedDate(new Date());
		if (authDesc.equals("N"))
		{
			boolean isSave = priceService.savePaymentHistory(paymentHistory);
			transaction.setStatus(Transaction.STATUS_FAILED);
			priceService.saveOrUpdate(transaction);
			return "redirect:/payment/invoice.htm";
		}
		if (authDesc.equals("Y"))
		{
			Subscription subscription = priceService.getSubscriptoinById(transaction.getSubscriptionId());

			paymentHistory.setBankRespCode(request.getParameter("bankRespCode"));
			paymentHistory.setNbBID(request.getParameter("nb_bid"));
			paymentHistory.setNbOrderNo(request.getParameter("nb_order_no"));
			paymentHistory.setBankName(request.getParameter("bank_name"));
			paymentHistory.setCardCategory(request.getParameter("card_category"));
			String dtls = request.getParameter("Merchant_Param");

			if (dtls != null)
			{
				StringTokenizer st = new StringTokenizer(dtls, "-");
				if (st.hasMoreTokens())
				{
					userId = Long.parseLong(st.nextToken());
					try
					{
						promoCode = st.nextToken();
					}
					catch (NoSuchElementException ex)
					{

						promoCode = "";
					}
				}
			}

			boolean isSave = priceService.savePaymentHistory(paymentHistory);
			transaction.setStatus(Transaction.STATUS_PAID);
			priceService.saveOrUpdate(transaction);
			subscription.setStatus(Subscription.STATUS_ACTIVE);
			priceService.saveOrUpdateSubscription(subscription);
			User user = userService.getUserById(transaction.getUseId());
			Long sectionId = userService.getSectionByGradeAndBoardId(subscription.getGradeId(), subscription.getBoardId());
			user.setGradeId(subscription.getGradeId());
			user.setBoardId(subscription.getBoardId());
			user.setSectionId(sectionId);
			user.setStatus(User.STATUS_ACTIVE);
			user.setUserType(User.USERTYPE_USER);
			userService.SaveOrUpdate(user);

			return "redirect:/payment/invoice.htm?orderId=" + OrderId;
			/* if (!promocode.equals("") || promocode != null) { pricingService.markCouponUsedForUpgradation(promocode, userId); m_log.info(">>>>>>>>>>>>Coupon entry done successfully>>>>>>>>>>>"); } */

		}

		return "redirect:/payment/invoice.htm";
	}

	/** @param request
	 * @param response
	 * @return */
	@RequestMapping(value = "/payment/invoice.htm")
	public ModelAndView invoice(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView("upgradeAccount/paymentSuccess");
		String OrderId = request.getParameter("orderId");
		Transaction transaction = priceService.getTransactionByOrderNo(OrderId);
		if (transaction != null)
		{
			Subscription subscription = priceService.getSubscriptoinWithJoinById(transaction.getSubscriptionId());
			if (subscription != null)
			{
				mav.addObject("expirydate", Utility.convertDateToString(subscription.getExpiryDate(), "dd-MMM-yyy"));
				if (subscription.getPlanType() == Subscription.PLAN_TYPE_ANNUAL)
				{
					mav.addObject("planType", "Annualy");
				}
				if (subscription.getPlanType() == Subscription.PLAN_TYPE_HALF_YEARLY)
				{
					mav.addObject("planType", "Half Yearly");
				}
				if (subscription.getPlanType() == Subscription.PLAN_TYPE_QUATERLY)
				{
					mav.addObject("planType", "Quaterly");
				}
				// get grade name
				if (subscription.getSubjectId() == -1)
				{
					mav.addObject("subjectName", "All Subjects");
				}
				else
				{
					mav.addObject("subjectName", subscription.getSubject().getName());
				}
			}
			mav.addObject("userObj", subscription.getUser());
			mav.addObject("gradeName", subscription.getGrade().getName());
			mav.addObject("boardName", subscription.getBoard().getName());
			mav.addObject("invoiceDate", Utility.convertDateToString(new Date(), "dd-MMM-yyyy"));
			mav.addObject("orderNo", OrderId);
			mav.addObject("transaction", transaction);
		}

		return mav;
	}

	/** @param request
	 * @param response
	 * @return */
	@RequestMapping(value = "/payment/upgradeAccount.htm")
	@LoginAuthentication
	public ModelAndView upgradeAccount(HttpServletRequest request, HttpServletResponse response)
	{
		UserForm loginUser = (UserForm) request.getSession().getAttribute("user");

		ModelAndView mav = new ModelAndView("retailUser/upgradeAccount");
		List<Board> boardList = studentCommonService.getAllBoardList();
		mav.addObject("boardList", boardList);
		mav.addObject("userId", loginUser.getId());

		return mav;
	}

	/** @param boardId
	 * @param gradeId
	 * @param subjectId
	 * @param boardName
	 * @param gradeName
	 * @param request
	 * @param response
	 * @return */
	@RequestMapping(value = "/ajax/payment/priceList/{boardId}/{gradeId}/{subjectId}/{boardName}/{gradeName}")
	public ModelAndView getSubjectPrice(@PathVariable("boardId") Long boardId, @PathVariable("gradeId") Long gradeId, @PathVariable("subjectId") Long subjectId, @PathVariable("boardName") String boardName, @PathVariable("gradeName") String gradeName, HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView("priceDetails");
		// List<BoardGradeSubject> subjectList = studentCommonService.getSubjectByBoardAndGrade(boardId, gradeId);
		List<Subject> subjects = new ArrayList<Subject>();
		if (subjectId.equals(-1l))
		{
			subjects = studentCommonService.getSubjectsByGradeAndBoard(boardId, gradeId);
			mav.addObject("subjectName", "All Subjects");
		}
		else
		{
			Subject subjectObj = studentCommonService.getSubjectDetailsById(subjectId);
			mav.addObject("subjectName", subjectObj.getName());
			subjects.add(subjectObj);
		}

		mav.addObject("gradeName", gradeName);
		mav.addObject("boardName", boardName);
		mav.addObject("subjectList", subjects);
		// getting price

		List<PriceList> priceList = priceService.getPriceList(subjectId, gradeId, boardId, null);
		if (priceList != null & priceList.size() > 0)
		{
			for (PriceList price : priceList)
			{
				if (price.getPlanType() == Subscription.PLAN_TYPE_ANNUAL)
				{
					mav.addObject("annualPrice", Utility.getDecimalFormat(price.getPrice(), 2));
				}
				if (price.getPlanType() == Subscription.PLAN_TYPE_HALF_YEARLY)
				{
					mav.addObject("halfYearlyPrice", Utility.getDecimalFormat(price.getPrice(), 2));
				}
				if (price.getPlanType() == Subscription.PLAN_TYPE_QUATERLY)
				{
					mav.addObject("quaterlyPrice", Utility.getDecimalFormat(price.getPrice(), 2));
				}

			}
		}
		mav.addObject("priceList", priceList);
		mav.addObject("subjectId", subjectId);

		return mav;
	}

}
