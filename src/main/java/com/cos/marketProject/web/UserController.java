package com.cos.marketProject.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.marketProject.domain.user.User;
import com.cos.marketProject.domain.user.UserRepository;
import com.cos.marketProject.handler.ex.MyAsyncNotFoundException;
import com.cos.marketProject.util.MyAlgorithm;
import com.cos.marketProject.util.SHA;
import com.cos.marketProject.util.Script;
import com.cos.marketProject.web.dto.CMRespDto;
import com.cos.marketProject.web.dto.JoinReqDto;
import com.cos.marketProject.web.dto.LoginReqDto;
import com.cos.marketProject.web.dto.UserUpdateDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class UserController {

	private final HttpSession session;
	private final UserRepository userRepository;		
//	private int   userId;								// 추가
	
	@PostMapping("/user/{id}")
	public @ResponseBody CMRespDto<String>update(@PathVariable int id, @Valid UserUpdateDto dto, BindingResult bindingResult) {
		 
		// 유효성
	      if (bindingResult.hasErrors()) {
	         Map<String, String> errorMap = new HashMap<>();
	         for (FieldError error : bindingResult.getFieldErrors()) {
	            errorMap.put(error.getField(), error.getDefaultMessage());
	         }
	         throw new MyAsyncNotFoundException(errorMap.toString());
	      }
	      
	      // 인증
	      User principal = (User) session.getAttribute("principal");
	      if (principal == null) { // 로그인 안됨
	         throw new MyAsyncNotFoundException("인증이 되지 않았습니다");
	      }
	      
	      // 권한
	      if(principal.getId()!=id) {
	    	  throw new MyAsyncNotFoundException("회원정보를 수정할 권한이 없습니다.");
	      }
	      
	      // 핵심로직
	      principal.setContact(dto.getContact());
	      session.setAttribute("principal", principal);
	      userRepository.save(principal);
	      return new CMRespDto<>(1, "성공", null);
	}    
	
	@GetMapping("/user/{id}")
	public String userinfo(@PathVariable int id) {
		return "user/updateForm";
	}

	@GetMapping("/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/";
	}

	@GetMapping("/loginForm")
	public String loginForm() {
		return "user/loginForm";
	}

	@GetMapping("/joinForm")
	public String joinForm() {
		return "user/joinForm";
	}

	@PostMapping("/login")
	public @ResponseBody String login(@Valid LoginReqDto dto, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			Map<String, String> errorMap = new HashMap<>();
			for (FieldError error : bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			return Script.back(errorMap.toString());
		}

		// 1. username, password 받기
		System.out.println(dto.getUsername());
		System.out.println(dto.getPassword());

		// 2. DB -> 조회
		User userEntity = userRepository.mLogin(dto.getUsername(), SHA.encrypt(dto.getPassword(), MyAlgorithm.SHA256));

		if (userEntity == null) { // username,password 잘못 기입
			return Script.back("아이디 혹은 비밀번호를 잘못 입력하였습니다.");
		} else {
			// 세션 날라가는 조건: 1. session.invailidate() - 로그아웃, 2. 브라우저를 닫으면 날라감
			session.setAttribute("principal", userEntity);
			return Script.href("/", "로그인 성공");
		}
	}

	@PostMapping("/join")
	public @ResponseBody String join(@Valid JoinReqDto dto, BindingResult bindingResult) { // username=love&password=1234&email=love@nate.com

		if (bindingResult.hasErrors()) {
			Map<String, String> errorMap = new HashMap<>();
			for (FieldError error : bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			return Script.back(errorMap.toString());
		}

		String encPassword = SHA.encrypt(dto.getPassword(), MyAlgorithm.SHA256);

		dto.setPassword(encPassword);
		userRepository.save(dto.toEntity());
		return Script.href("/loginForm");
	}
	
//	// id찾기 기능------------------------------------------- 추가
//		@PostMapping("/user/findId")
//		public @ResponseBody  CMRespDto<String> userFindId(@RequestBody FindIdReqDto dto, Model model) {
//			
//			System.out.println(dto.getName());
//			System.out.println(dto.getBirth());
//			System.out.println(dto.getContact());
//		
//			User userEntity = userRepository.mIdFind(dto.getName(), dto.getBirth(), dto.getContact());
//		//	User userEntity = userRepository.mIdFind(name, birth, email);
//			System.out.println("=======================");
//			System.out.println(userEntity);
//			System.out.println("=======================");
//			
//			
//			model.addAttribute("userEntity", userEntity);
//			
//			return new CMRespDto<>(1, "성공", userEntity.getUsername());
//		}
//		
//		// 비밀번호 변경 modal 띄우기 ------------------------------------
//		@PostMapping("/user/findPw")
//		public @ResponseBody CMRespDto<String> pwFind(@RequestBody FindPwReqDto dto) {
//			
//			System.out.println("진행중");
//			
//			User userEntity = userRepository.mPWFind(dto.getUsername(), dto.getName(), dto.getBirth(), dto.getContact());
//			userId = userEntity.getId();
//			
//			System.out.println(userId);
//			if(userEntity == null ) {
//				return new CMRespDto<>(0, "없는 회원임", null);
//			} else {
//				return new CMRespDto<>(1, "성공", null);
//			}
//					
//
//		}
//		
//		// 비밀번호 변경 ------------------------------------
//		@PutMapping("/user/changePw")
//		public @ResponseBody CMRespDto<String> pwFind(@RequestBody ChangePwDto dto) {
//			
//			User userEntity = userRepository.mChangePw(userId, dto.getPassword());
//			
//			return new CMRespDto<>(1, "비번 변경 완료", null);
//
//		}
}

