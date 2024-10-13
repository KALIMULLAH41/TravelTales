package com.traveltales.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class NavController 
{
	@GetMapping("signup")
	public String register()
	{
		return "register";
	}
	@GetMapping("signin")
	public String login()
	{
		return "login";
	}
	@GetMapping("delete-follower")
	public String deleteFollower()
	{
		return "deletefollower";
	}
	@GetMapping("adminhome")
	public String adminHome()
	{
		return "adminhome";
	}
	@GetMapping("add-video")
	public String addVideo()
	{
		return "addvideo";
	}
	@GetMapping("delete-video")
	public String deleteVideo()
	{
		return "deletevideo";
	}
	@GetMapping("update-profile")
	public String updateProfile()
	{
		return "updateprofile";
	}
	@GetMapping("home")
	public String home()
	{
		return "home";
	}
	@GetMapping("index")
	public String index()
	{
		return "index";
	}
}
