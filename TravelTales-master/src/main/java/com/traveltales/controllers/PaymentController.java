package com.traveltales.controllers;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;
import com.traveltales.entities.Followers;
import com.traveltales.services.FollowersService;

import jakarta.servlet.http.HttpSession;
@Controller
public class PaymentController 
{
	@Autowired
	FollowersService folser;
	
	@PostMapping("/createOrder")
	@ResponseBody
	public String createOrder() {
		Order order = null;
		try {
			RazorpayClient razorpay = new RazorpayClient("rzp_test_dFDrEDzu3G5ymy", 
					"odAI0pOdCMMHHIM08JqVoiaB");

			JSONObject orderRequest = new JSONObject();
			orderRequest.put("amount",100);
			orderRequest.put("currency","INR");
			orderRequest.put("receipt", "receipt#1");
			JSONObject notes = new JSONObject();
			notes.put("notes_key_1","Tea, Earl Grey, Hot");
			orderRequest.put("notes",notes);
		
			order = razorpay.orders.create(orderRequest);
		}
		catch(Exception e) {
			System.out.println("exception while creating order");
		}
		return order.toString();
	}
	
	@PostMapping("/verify")
	@ResponseBody
	public boolean verifyPayment(@RequestParam  String orderId, @RequestParam String paymentId,
											@RequestParam String signature) {
	    try {
	        // Initialize Razorpay client with your API key and secret
	        @SuppressWarnings("unused")
			RazorpayClient razorpayClient = new RazorpayClient("rzp_test_dFDrEDzu3G5ymy", 
	        								"odAI0pOdCMMHHIM08JqVoiaB");
	        // Create a signature verification data string
	        String verificationData = orderId + "|" + paymentId;

	        // Use Razorpay's utility function to verify the signature
	        boolean isValidSignature = Utils.verifySignature(verificationData, signature, "odAI0pOdCMMHHIM08JqVoiaB");

	        return isValidSignature;
	    } catch (RazorpayException e) {
	        e.printStackTrace();
	        return false;
	    }
	}	
	
	//payment-success -> update to premium user
	@GetMapping("payment-success")
	public String paymentSuccess(HttpSession session)
	{	
		//Getting the email from the session
		String email = (String) session.getAttribute("email");
		//Getting the user details
		Followers user = folser.getFollower(email);
		//change the prme status of the user
		user.setSubscription(true);
		//updating the user
		folser.updateFollower(user);
		//redirecting the control to login
		return "login";
	}
	//payment-failure -> redirect to login 
	@GetMapping("payment-failure")
	public String paymentFailure()
	{
		//payment-error page
		return "login";
	}
}
