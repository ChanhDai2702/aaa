package com.fpt.intern.bestcv.controller;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.fpt.intern.bestcv.entity.AspNetUsers;
import com.fpt.intern.bestcv.service.AspNetUsersService;
import com.fpt.intern.bestcv.service.impl.MailService;
import com.fpt.intern.bestcv.utility.Utility;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Controller
public class ForgotPasswordController {
	static String FORM_FORGOT_PASSWORD = "components/resetpassword/forgotPassword";
	static String FORM_RESET_PASSWORD = "components/resetpassword/resetPassword";
	static String MESSAGE = "components/resetpassword/message";
	static String STATUS_SEND_EMAIL = "components/resetpassword/statusSendEmail";
	static String STATUS_RESET_PASSWORD = "components/resetpassword/statusResetPassword";
//	@Autowired
//	private JavaMailSender mailSender;
	@Autowired
	private MailService mailSenderService;
	@Value("${bezkoder.app.jwtSecret}")
	private String jwtSecret;
	@Value("${bezkoder.app.jwtExpirationMs}")
	private int jwtExpirationMs;
	@Autowired
	private AspNetUsersService aspNetUsersService;

	@GetMapping("/forgot_password")
	public String showForgotPasswordForm() {
		return FORM_FORGOT_PASSWORD;
	}

	@PostMapping("/forgot_password")
	public String processForgotPassword(HttpServletRequest request, Model model) {
		String email = request.getParameter("email");
		AspNetUsers aspNetUsers = aspNetUsersService.getAspNetUsersByEmail(email);
		if (aspNetUsers == null) {
			model.addAttribute("message", "Email này chưa tồn tại trong hệ thống. Vui lòng kiểm tra lại.");
			return FORM_FORGOT_PASSWORD;
		} else {
			try {
				String resetPasswordLink = Utility.getSiteURL(request) + "/reset_password?email=" + email + "&token="
						+ createToken(aspNetUsers.getEmail());
				mailSenderService.sendEmail(email, resetPasswordLink);
				model.addAttribute("message", "Chúng tôi đã gửi một link đổi mật khẩu đến Email của bạn.");
				return STATUS_SEND_EMAIL;
			} catch (Exception ex) {
				model.addAttribute("error", "Đã xảy ra lỗi trong khi gửi email!");
			}
			return STATUS_SEND_EMAIL;
		}
	}

	public String createToken(String email) {
		Claims claims = Jwts.claims().setSubject(email);
		claims.put("email", email);
		Date currentTime = new Date();
		currentTime.setTime(currentTime.getTime() + jwtExpirationMs);
		return Jwts.builder().setClaims(claims).setExpiration(currentTime).signWith(SignatureAlgorithm.HS512, jwtSecret)
				.compact();
	}

	public String readMailIdFromToken(String token) {
		Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getSignature();
		Jws<Claims> parseClaimsJws = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
		return parseClaimsJws.getBody().getSubject();
	}

	@GetMapping("/reset_password")
	public String showResetPasswordForm(@Param(value = "token") String token, @Param(value = "email") String email,
			Model model) {
		try {
			model.addAttribute("token", token);
			model.addAttribute("email", email);
			String getEmailFromToken = readMailIdFromToken(token);
			if (!getEmailFromToken.equals(email)) {
				model.addAttribute("message", "Liên kết không hợp lệ!");
				return MESSAGE;
			}
			return FORM_RESET_PASSWORD;
		} catch (SignatureException e) {
			model.addAttribute("message", "Chữ kí không hợp lệ!");
			return MESSAGE;
		} catch (MalformedJwtException e) {
			model.addAttribute("message", "Token không hợp lệ!");
			return MESSAGE;
		} catch (ExpiredJwtException e) {
			model.addAttribute("message", "Liên kết đã hết hạn!");
			return MESSAGE;
		} catch (UnsupportedJwtException e) {
			model.addAttribute("message", "Token không được hỗ trợ!");
			return MESSAGE;
		} catch (IllegalArgumentException e) {
			model.addAttribute("message", "Chuỗi biểu thức rỗng!");
			return MESSAGE;
		}
	}

	@PostMapping("/reset_password")
	public String processResetPassword(HttpServletRequest request, Model model) {
		try {
			String token = request.getParameter("token");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			model.addAttribute("title", "Reset your password");
			String getEmailFromToken = readMailIdFromToken(token);
			AspNetUsers aspNetUsers = aspNetUsersService.getAspNetUsersByEmail(email);
			if (aspNetUsers == null) {
				model.addAttribute("message", "Email này chưa tồn tại trong hệ thống. Vui lòng kiểm tra lại.");
				return MESSAGE;
			} else if (!getEmailFromToken.equalsIgnoreCase(aspNetUsers.getEmail())) {
				model.addAttribute("message", "Địa chỉ email không hợp lệ!");
				return MESSAGE;
			} else {
				aspNetUsersService.updatePassword(aspNetUsers, password);
				model.addAttribute("message", "Mật khẩu của bạn đã được khôi phục. Vui lòng ");
			}
			return STATUS_RESET_PASSWORD;
		} catch (SignatureException e) {
			model.addAttribute("message", "Chữ kí không hợp lệ!");
			return MESSAGE;
		} catch (MalformedJwtException e) {
			model.addAttribute("message", "Token không hợp lệ!");
			return MESSAGE;
		} catch (ExpiredJwtException e) {
			model.addAttribute("message", "Liên kết đã hết hạn!");
			return MESSAGE;
		} catch (UnsupportedJwtException e) {
			model.addAttribute("message", "Token không được hỗ trợ!");
			return MESSAGE;
		} catch (IllegalArgumentException e) {
			model.addAttribute("message", "Chuỗi biểu thức rỗng!");
			return MESSAGE;
		} catch (Exception e) {
			model.addAttribute("message", "Đã có lỗi xảy ra!");
			return MESSAGE;
		}
	}
}
