package com.traveltales.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.traveltales.entities.Videos;

public interface VideosRepository extends JpaRepository<Videos,Integer> 
{
	public Videos findByVideoName(String videoName);
}
