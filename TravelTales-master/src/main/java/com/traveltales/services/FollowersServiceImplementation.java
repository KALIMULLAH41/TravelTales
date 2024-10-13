package com.traveltales.services;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.traveltales.entities.Followers;
import com.traveltales.repositories.FollowersRepository;
@Service
public class FollowersServiceImplementation implements FollowersService
{
	@Autowired
	FollowersRepository folrepo;
	
	public FollowersServiceImplementation(FollowersRepository folrepo) 
	{
		super();
		this.folrepo = folrepo;
	}

	@Override
	public String addFollower(Followers fol) 
	{
		folrepo.save(fol);
		return "follower created and saved";
	}

	@Override
	public boolean emailExists(String email) 
	{
		if(folrepo.findByEmail(email)==null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public boolean checkUser(String email, String password) 
	{
		Followers fol=folrepo.findByEmail(email);
		String dbemail=fol.getEmail();
		String dbpassword=fol.getPassword();
		if(dbpassword.equals(password) && dbemail.equals(email)) 
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public List<Followers> getAllFollowersInfo() 
	{
		List<Followers> fol=folrepo.findAll();
		return fol;
	}

	@Override
	public boolean deleteFollower(int id) 
	{
		if(folrepo.existsById(id))
		{
			folrepo.deleteById(id);
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public Followers getFollower(String email) 
	{
		Followers fol=folrepo.findByEmail(email);
		return fol;
	}

	@Override
	public void updateFollower(Followers fol) 
	{
		folrepo.save(fol);
	}

	@Override
	public void updateFollowers(Followers foll, String email) 
	{
		Followers fol=folrepo.findByEmail(email);
		fol.setName(foll.getName());
		fol.setAddress(foll.getAddress());
		fol.setPassword(foll.getPassword());
		fol.setGender(foll.getGender());
		fol.setPhone(foll.getPhone());
		folrepo.save(fol);
	}
}
