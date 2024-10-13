package com.traveltales.controllers;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.traveltales.entities.Followers;
import com.traveltales.entities.Videos;
import com.traveltales.services.FollowersService;
import com.traveltales.services.VideosService;

import jakarta.servlet.http.HttpSession;
@Controller
public class FollowersController 
{
	@Autowired
	FollowersService folser;
	@Autowired
	VideosService vidser;
	
	public FollowersController() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FollowersController(FollowersService folser, VideosService vidser) {
		super();
		this.folser = folser;
		this.vidser = vidser;
	}
	
	@PostMapping("register")
	public String addUser(@ModelAttribute Followers fol) 
	{
		boolean status=folser.emailExists(fol.getEmail());
		if(status==true)
		{
			folser.addFollower(fol);
			return "registerSuccess";
		}
		else
		{
			return "registerfail";
		}
	}
	
	@PostMapping("login")
	public String validateUser(@RequestParam String email,@RequestParam String password,HttpSession session)
	{
		boolean loginstatus=folser.checkUser(email,password);
		if(loginstatus==true)
		{
			session.setAttribute("email", email);
			
			if(email.equals("admin@gmail.com"))
			{
				return "adminhome";
			}
			else
			{
				return "home";
			}
		}
		else
		{
			return "loginfail";
		}
	}
	
	@GetMapping("view-followers")
	public String getAllFollowersInfo(Model model) 
	{
		List<Followers> fol=folser.getAllFollowersInfo();
		model.addAttribute("followers",fol);
		return "viewfollowerstable";
	}
	
	@RequestMapping("delete-follower-map")
	@DeleteMapping(value="{id}")
	public String deleteFollower(@RequestParam int id)
	{
		boolean status=folser.deleteFollower(id);
		if(status==true)
		{
			return "deletefollowersuccess";
		}
		else
		{
			return "deletefollowerfail";
		}
	}
	
	@GetMapping("explore-videos")
	public String exploreVideos(Model model, HttpSession session)
	{
		//Getting the mail from sesson
		String email=(String)session.getAttribute("email");
		//Getting the user details for email
		Followers fol=folser.getFollower(email);
		boolean status=fol.isSubscription();
		//checking user is prime or not
		if(status==true)
		{
			//Getting the movielist
			List<Videos> videoList=vidser.getAllVideos();
			//adding the movie details in the model
			model.addAttribute("videos",videoList);
			//if premium, show movies.
			return "viewvideosuser";
		}
		else
		{
			//otherwise,make payment
			return "payment";
		}
	}
	
	@PostMapping("update-profile")
	public String updateProgile(@ModelAttribute Followers fol, HttpSession session)
	{
		String email = (String)session.getAttribute("email");
		folser.updateFollowers(fol , email);
		return "profileupdatesuccess";
	}
	
	@GetMapping("follower-logout")
	public String followerLogout(HttpSession session)
	{
		session.invalidate();
		return "logoutsuccess";
	}
	
	@GetMapping("admin-logout")
	public String adminLogout(HttpSession session)
	{
		session.invalidate();
		return "logoutsuccess";
	}
}
