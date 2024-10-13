package com.traveltales.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import com.traveltales.entities.Followers;

public interface FollowersRepository extends JpaRepository<Followers, Integer>
{
	public Followers findByEmail(String email);
}
