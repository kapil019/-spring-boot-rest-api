package com.example.fl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.fl.Entity.User;
import com.example.fl.Repository.UserRepository;
import com.example.fl.Translator.UserTranslator;
import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Empty;
import com.example.fl.Response.BaseListResp;
import com.example.fl.Response.BaseResp;
import com.example.fl.Response.Error;
import com.example.fl.Response.SRO.UserResp;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.example.fl.Request.User.CreateUser;
import com.example.fl.Request.User.UpdateUser;

import java.util.ArrayList;

@Controller
@RequestMapping(path = "/user")
public class UserController {
	@Autowired
	private UserRepository userRepository;

	@PostMapping(path = "/add", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public @ResponseBody ResponseEntity addNewUser(@RequestBody CreateUser createUserRequest) {
		BaseResp apiResp = new BaseResp();
		try {
			User newUser = new User();
			newUser.setName(createUserRequest.getName());
			newUser.setEmail(createUserRequest.getEmail());
			newUser.setPhone(createUserRequest.getPhone());
			userRepository.save(newUser);
			apiResp.setMessage("Data Saved Successfully");
			return ResponseEntity.accepted().body(apiResp);
		} catch (Exception e) {
			apiResp.setError(new Error("Something went wrong", 400));
			return ResponseEntity.badRequest().body(apiResp);
		}
	}

	@GetMapping(path = "/all")
	public @ResponseBody ResponseEntity getAllUsers() {
		BaseListResp apiResp = new BaseListResp();
		try {
			ArrayList<UserResp> users = new ArrayList<UserResp>();
			UserTranslator userTranslator = new UserTranslator();

			for (User u : userRepository.findAll()) {
				users.add(userTranslator.translateuserRespFromModel(u));
			}
			apiResp.setData(users);
			return ResponseEntity.accepted().body(apiResp);
		} catch (Exception e) {
			apiResp.setError(new Error("Something went wrong", 400));
			return ResponseEntity.badRequest().body(apiResp);
		}
	}

	@GetMapping(path = "/{userId}")
	public @ResponseBody ResponseEntity findById(
			@PathVariable(value = "userId") Integer userId) {
		BaseResp apiResp = new BaseResp();
		UserTranslator userTranslator = new UserTranslator();
		try {
			User user = userRepository.findById(userId).get();
			if (user != null) {
				UserResp userResp;
				userResp = userTranslator.translateuserRespFromModel(user);
				apiResp.setData(userResp);
				return ResponseEntity.accepted().body(apiResp);
			} else {
				apiResp.setError(new Error("No data found", 404));
				return ResponseEntity.badRequest().body(apiResp);
			}
		} catch (java.util.NoSuchElementException e) {
			apiResp.setError(new Error("No data found", 404));
			return ResponseEntity.badRequest().body(apiResp);
		} catch (Exception e) {
			apiResp.setError(new Error("Something went wrong", 400));
			return ResponseEntity.badRequest().body(apiResp);
		}
	}

	@PutMapping(path = "/{userId}", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public @ResponseBody ResponseEntity updateUser(
			@RequestBody UpdateUser updateUserRequest,
			@PathVariable(value = "userId") Integer userId) {
		BaseResp apiResp = new BaseResp();
		try {
			User user = userRepository.findById(userId).get();
			if (user != null) {
				if (updateUserRequest.getName() != null) {
					user.setName(updateUserRequest.getName());
				}
				if (updateUserRequest.getEmail() != null) {
					user.setEmail(updateUserRequest.getEmail());
				}
				if (updateUserRequest.getPhone() != null) {
					user.setPhone(updateUserRequest.getPhone());
				}
				userRepository.save(user);
				apiResp.setMessage("User details updated Successfully");
				return ResponseEntity.accepted().body(apiResp);
			} else {
				apiResp.setError(new Error("User dose not exist", 400));
				return ResponseEntity.badRequest().body(apiResp);
			}
		} catch (Exception e) {
			apiResp.setError(new Error("Something went wrong", 400));
			return ResponseEntity.badRequest().body(apiResp);
		}
	}

	@DeleteMapping(path = "/{userId}")
	public @ResponseBody ResponseEntity deleteUser(
			@PathVariable(value = "userId") Integer userId) {
		BaseResp apiResp = new BaseResp();
		try {
			userRepository.deleteById(userId);
			apiResp.setMessage("User deleted Successfully");
			return ResponseEntity.accepted().body(apiResp);
		} catch (Exception e) {
			apiResp.setError(new Error("Something went wrong", 400));
			return ResponseEntity.badRequest().body(apiResp);
		}
	}
}
