package com.traveltales.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.traveltales.entities.Videos;
import com.traveltales.repositories.VideosRepository;

@Service
public class VideosServiceImplementation implements VideosService
{
	@Autowired
	VideosRepository vidrepo;

	public VideosServiceImplementation(VideosRepository vidrepo) {
		super();
		this.vidrepo = vidrepo;
	}

	@Override
	public String addVideo(Videos vid) 
	{
		vidrepo.save(vid);
		return null;
	}

	@Override
	public boolean videoExists(String videoName) 
	{
		if(vidrepo.findByVideoName(videoName)==null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public boolean deleteVideo(int id) 
	{
		if(vidrepo.existsById(id))
		{
			vidrepo.deleteById(id);
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public List<Videos> getAllVideos() 
	{
		List<Videos> vid=vidrepo.findAll();
		return vid;
	}

}
