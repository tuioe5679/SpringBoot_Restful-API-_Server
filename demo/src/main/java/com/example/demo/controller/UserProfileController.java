package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.UserProfile;

@RestController
public class UserProfileController {
	
	private Map<String,UserProfile> userMap;
	
	@PostConstruct
	public void init() {
		userMap = new HashMap<String,UserProfile>();
		userMap.put("1",new UserProfile("1","유저1","111-1111","지역1"));
		userMap.put("2",new UserProfile("2","유저2","222-2222","지역2"));
		userMap.put("3",new UserProfile("3","유저3","333-3333","지역3"));
	}
	
	@GetMapping("/user/{id}") //GetMapping = 데이터 조회
	public UserProfile getUserProfile(@PathVariable("id") String id) {
		return userMap.get(id);
	}
	
	@GetMapping("/user/all")
	public List<UserProfile> getUserProfileList(){
		return new ArrayList<UserProfile>(userMap.values());
	}
	
	@PutMapping("/user/{id}")
	public void putUserProfile(@PathVariable("id") String id,@RequestParam("name") String name, 
							   @RequestParam("phone") String phone,@RequestParam("address") String address ) {
		UserProfile userProfile = new UserProfile(id,name,phone,address);
		userMap.put(id, userProfile);
	}
	
	@PostMapping("/user/{id}")
	public void postUserProfile(@PathVariable("id") String id,@RequestParam("name") String name, 
			   @RequestParam("phone") String phone,@RequestParam("address") String address ) {
		UserProfile userProfile = userMap.get(id);
		userProfile.setName(name);
		userProfile.setPhone(phone);
		userProfile.setAddress(address);
	}
	
	@DeleteMapping("/user/{id}")
	public void deleteUserProfile(@PathVariable("id") String id) {
		userMap.remove(id);
	}
}
