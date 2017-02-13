package com.coderscampus;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coderscampus.domain.User;
import com.coderscampus.repository.UserRepository;

@Controller
public class HibernateController
{
  @Autowired
  private UserRepository userRepo;
  
  @RequestMapping(value="users", method=RequestMethod.GET)
  public @ResponseBody List<User> getUsers ()
  {
    return userRepo.findAll();
  }
  
  @RequestMapping(value="users", method=RequestMethod.POST)
  public @ResponseBody User createUser (@RequestParam String username, @RequestParam String password)
  {
    User user = new User();
    user.setUsername(username);
    user.setPassword(password);
    
    user = userRepo.save(user);
    return user;
  }
  
  @RequestMapping(value="users/{userId}", method=RequestMethod.POST)
  public @ResponseBody User updateUser (@PathVariable Long userId, @RequestParam String username, @RequestParam String password)
  {
    User user = userRepo.findOne(userId);
    
    user.setUsername(username);
    user.setPassword(password);
    
    user = userRepo.save(user);
    return user;
  }
  
  @RequestMapping(value="users/{userId}", method=RequestMethod.DELETE)
  public @ResponseBody void deleteUser (@PathVariable Long userId)
  {
    userRepo.delete(userId);
  }
  
}
