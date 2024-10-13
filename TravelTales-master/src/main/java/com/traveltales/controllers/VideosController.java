package com.traveltales.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.traveltales.entities.Videos;
import com.traveltales.services.VideosService;

@Controller
public class VideosController 
{
	@Autowired
	VideosService vidser;

	public VideosController(VideosService vidser) {
		super();
		this.vidser = vidser;
	}
	
	@PostMapping("add-video")
	public String addmovie(Videos vid) 
	{
		boolean status=vidser.videoExists(vid.getVideoName());
		if(status==true)
		{
			vidser.addVideo(vid);
			return "videoaddedsuccess";
		}
		else
		{
			return "videoaddedfail";
		}
		
	}
	
	@RequestMapping("delete-video")
	@DeleteMapping(value="/{id}")
	public String deleteVideo(@RequestParam int id)
	{
		boolean status=vidser.deleteVideo(id);
		if(status==true)
		{
			return "deletevideosuccess";
		}
		else
		{
			return "deletevideofail";
		}
	}
	
	@GetMapping("view-videos")
	public String getAllVideos(Model model) 
	{
		List<Videos> vid=vidser.getAllVideos();
		model.addAttribute("videos",vid);
		return "viewvideosforadmin";
	}
}
