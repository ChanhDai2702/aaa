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
			model.addAttribute("message", "Email n??y ch??a t???n t???i trong h??? th???ng. Vui l??ng ki???m tra l???i.");
			return FORM_FORGOT_PASSWORD;
		} else {
			try {
				String resetPasswordLink = Utility.getSiteURL(request) + "/reset_password?email=" + email + "&token="
						+ createToken(aspNetUsers.getEmail());
				mailSenderService.sendEmail(email, resetPasswordLink);
				model.addAttribute("message", "Ch??ng t??i ???? g???i m???t link ?????i m???t kh???u ?????n Email c???a b???n.");
				return STATUS_SEND_EMAIL;
			} catch (Exception ex) {
				model.addAttribute("error", "???? x???y ra l???i trong khi g???i email!");
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
				model.addAttribute("message", "Li??n k???t kh??ng h???p l???!");
				return MESSAGE;
			}
			return FORM_RESET_PASSWORD;
		} catch (SignatureException e) {
			model.addAttribute("message", "Ch??? k?? kh??ng h???p l???!");
			return MESSAGE;
		} catch (MalformedJwtException e) {
			model.addAttribute("message", "Token kh??ng h???p l???!");
			return MESSAGE;
		} catch (ExpiredJwtException e) {
			model.addAttribute("message", "Li??n k???t ???? h???t h???n!");
			return MESSAGE;
		} catch (UnsupportedJwtException e) {
			model.addAttribute("message", "Token kh??ng ???????c h??? tr???!");
			return MESSAGE;
		} catch (IllegalArgumentException e) {
			model.addAttribute("message", "Chu???i bi???u th???c r???ng!");
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
				model.addAttribute("message", "Email n??y ch??a t???n t???i trong h??? th???ng. Vui l??ng ki???m tra l???i.");
				return MESSAGE;
			} else if (!getEmailFromToken.equalsIgnoreCase(aspNetUsers.getEmail())) {
				model.addAttribute("message", "?????a ch??? email kh??ng h???p l???!");
				return MESSAGE;
			} else {
				aspNetUsersService.updatePassword(aspNetUsers, password);
				model.addAttribute("message", "M???t kh???u c???a b???n ???? ???????c kh??i ph???c. Vui l??ng ");
			}
			return STATUS_RESET_PASSWORD;
		} catch (SignatureException e) {
			model.addAttribute("message", "Ch??? k?? kh??ng h???p l???!");
			return MESSAGE;
		} catch (MalformedJwtException e) {
			model.addAttribute("message", "Token kh??ng h???p l???!");
			return MESSAGE;
		} catch (ExpiredJwtException e) {
			model.addAttribute("message", "Li??n k???t ???? h???t h???n!");
			return MESSAGE;
		} catch (UnsupportedJwtException e) {
			model.addAttribute("message", "Token kh??ng ???????c h??? tr???!");
			return MESSAGE;
		} catch (IllegalArgumentException e) {
			model.addAttribute("message", "Chu???i bi???u th???c r???ng!");
			return MESSAGE;
		} catch (Exception e) {
			model.addAttribute("message", "???? c?? l???i x???y ra!");
			return MESSAGE;
		}
	}
}
