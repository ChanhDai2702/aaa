package com.fpt.intern.bestcv.service.impl;

import java.time.Instant;
import java.time.LocalDate;
import java.util.*;

import com.fpt.intern.bestcv.dto.UserDTO;
import com.fpt.intern.bestcv.entity.*;
import com.fpt.intern.bestcv.reposistory.*;

import com.fpt.intern.bestcv.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fpt.intern.bestcv.service.AspNetUsersService;

import javax.validation.constraints.NotNull;

import static java.util.UUID.randomUUID;

@Service
public class AspNetUsersServiceImpl implements AspNetUsersService{
	static String AUTH_UTV = "ROLE_UTV";
	static String AUTH_NTD = "ROLE_NTD";
	@Autowired
	private AspNetUsersReposistory usersReponsitory;
	@Autowired
	private AspNetRolesReposistory rolesReponsitory;
	@Autowired
	private CandidateReposistory candidateReponsitory;
	@Autowired
	private RecruiterReposistory recruiterReponsitory;
	@Autowired
	private IndustryReposistory industryReponsitory;
	@Autowired
	private BusinessOrganizationReposistory organizationReponsitory;
	@Autowired
	private PriorityAddressReposistory addressReponsitory;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private EmailSenderService emailSenderService;
	@Autowired
	private AspNetUserLoginsReposistory aspNetUserLoginsReponsitory;
	@Autowired
	private AspNetUsersReposistory userRepository;
	@Override
	public AspNetUsers getAspNetUsersByEmail(String email) {
		Optional<AspNetUsers> optional = usersReponsitory.findByEmail(email);
		if(!optional.isPresent())
			return null;
		return optional.get();
	}
	
	@Override
	public void updateAspNetUsers(AspNetUsers user) {
		// TODO Auto-generated method stub

		usersReponsitory.save(user);
	}
	public void updatePassword(AspNetUsers user, String newPassword) {
		if (user != null) {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String encodedPassword = passwordEncoder.encode(newPassword);
			user.setPasswordHash(encodedPassword);
			usersReponsitory.save(user);
		}
	}
	@Override
	public void addAspNetUsers(AspNetUsers user) {
		// TODO Auto-generated method stub
		usersReponsitory.save(user);
	}

	
	@Override
	public List<AspNetUsers> getAllAspNetUsers() {
		// TODO Auto-generated method stub
		return (List<AspNetUsers>) usersReponsitory.findAll();
	}

	@Override
	public AspNetUsers getAspNetUsersByID(String id) {
		Optional<AspNetUsers> optional = usersReponsitory.findById(id);
		if(!optional.isPresent())
			return null;
		return optional.get();
	}

	@Override
	public AspNetUsers getAspNetUserByRole_Admin(String role_admin) {
		AspNetUsers optional = usersReponsitory.getAspNetUsersByRole(role_admin);
		return optional;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return usersReponsitory.getCount();
	}

	@Override
	public int getIncrease() {
		// TODO Auto-generated method stub
		return usersReponsitory.getIncrease();
	}
	@Override
	public Candidate registerUCV(UserDTO userDTO,String provider) {
		// gọi lại để truyền thêm role
		AspNetUsers users = getUser(userDTO);
		// sử dụng set để thêm role
		Set<AspNetRoles> setRoles = new HashSet<>();
		setRoles.add(getAspNetRoles(AUTH_UTV));
		System.out.println(setRoles);
		users.setRoles(setRoles);
		//lưu thông tin user xuống csdl
		usersReponsitory.save(users);
		//lưu id api vào apsnetuserlogins
		if(!userDTO.getId().equalsIgnoreCase("")){
			AspNetUserLogins aspNetUserLogins = new AspNetUserLogins();
			aspNetUserLogins.setUser(users);
			aspNetUserLogins.setLoginProvider(provider);
			aspNetUserLogins.setProviderKey(userDTO.getId());
			aspNetUserLoginsReponsitory.save(aspNetUserLogins);
		}
		//set giá trị cho UCV
		Candidate candidate = new Candidate();
		candidate.setAddress(userDTO.getAddress());
		//set thông tin user cho UCV
		candidate.setAspNetUsers(users);
		candidate.setFullName(userDTO.getFullname());
		//set giá trị lấy từ combobox
       /* candidate.setPriorityAddress(addressReponsitory.findById(1).get());
        candidate.setIndustry(industryReponsitory.findById(1).get());*/
		candidate.setPriorityAddress(addressReponsitory.findById(userDTO.getPriorityAddress()).get());
		candidate.setIndustry(industryReponsitory.findById(userDTO.getIndustry()).get());
		// set cứng ngày sình và giới tính ---- vì NOT NULL
		candidate.setDateOfBirth(Date.from(Instant.now()));
		candidate.setGender("Nam");
		//lưu UCV xuống csdl
		candidate = candidateReponsitory.save(candidate);
		return candidate;
	}

	@Override
	public Recruiter registerNTD(UserDTO userDTO,String provider) {
		// gọi lại để truyền thêm role
		AspNetUsers users = getUser(userDTO);
		// sử dụng set để thêm role
		Set<AspNetRoles> setRoles = new HashSet<>();
		setRoles.add(getAspNetRoles(AUTH_NTD));
		users.setRoles(setRoles);
		//lưu thông tin user xuống csdl
		usersReponsitory.save(users);
		System.out.println(users);
		//lưu id api vào apsnetuserlogins
		if(!userDTO.getId().equalsIgnoreCase("")){
			AspNetUserLogins aspNetUserLogins = new AspNetUserLogins();
			aspNetUserLogins.setUser(users);
			aspNetUserLogins.setLoginProvider(provider);
			aspNetUserLogins.setProviderKey(userDTO.getId());
			aspNetUserLoginsReponsitory.save(aspNetUserLogins);
		}
		//set giá trị cho NTD
		Recruiter recruiter = new Recruiter();
		recruiter.setAddress(userDTO.getAddress());
		recruiter.setCompanyName(userDTO.getFullname());
		recruiter.setIndustry(getIndustry(userDTO));
		//set thông tin user cho NTD
		recruiter.setUsers(users);
		//set giá trị lấy từ combobox
		recruiter.setBusinessOrganization(getBusinessOrganization(userDTO));
		recruiter.setPriorityAddress(getPriorityAddress(userDTO));
		//lưu NTD xuống csdl
		recruiter = recruiterReponsitory.save(recruiter);
		return recruiter;
	}

	//lấy giá trị user từ model vào chuyển qua Entity thông qua UserDTO
	public AspNetUsers getUser(UserDTO userDTO){
		AspNetUsers users = new AspNetUsers();
		String uid = randomUUID().toString();
		users.setId(uid);
		users.setCreateDate(Date.from(Instant.now()));
		users.setEmail(userDTO.getEmail());
		users.setUserName(userDTO.getUsername());
		users.setPasswordHash(passwordEncoder.encode(userDTO.getPassword()));
		users.setPhoneNumber(userDTO.getPhone());
		users.setSecurityStamp(uid);


		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(users.getEmail());
		mailMessage.setSubject("Hoàn Thành Đăng Ký!");
		mailMessage.setFrom("examplebestcv@gmail.com");
		mailMessage.setText("Vui long xác thức tài khoản, vui lòng nhấn vào link sau đây : "
				+"https://localhost:8080/confirm-account?token="+users.getSecurityStamp());
		emailSenderService.sendEmail(mailMessage);
		return users;
	}
	//lấy giá trị địa chỉ ưu tiên thông qua DTO
	public PriorityAddress getPriorityAddress(@NotNull UserDTO userDTO){
		return addressReponsitory.findById(userDTO.getPriorityAddress()).get();
	}
	//lấy giá trị tổ chức doanh nghiệp thông qua DTO
	public BusinessOrganization getBusinessOrganization(@NotNull UserDTO userDTO){
		return organizationReponsitory.findById(userDTO.getBusinessOrganization()).get();
	}
	//lấy giá trị ngành ưu tiên thông qua DTO
	public Industry getIndustry(@NotNull UserDTO userDTO){
		return industryReponsitory.findById(userDTO.getIndustry()).get();
	}
	//lấy giá trị role thông qua name
	public AspNetRoles getAspNetRoles(String name){
		return rolesReponsitory.findByName(name);
	}
	@Override
	public List<AspNetUsers> getAllAcountAspNetUsers() {
		// TODO Auto-generated method stub
		return (List<AspNetUsers>) usersReponsitory.findAll();
	}

	@Override
	public AspNetUsers findbyId(String id) {
		Optional<AspNetUsers> optional = userRepository.findById(id);
		if(!optional.isPresent())
			return null;
		return optional.get();
	}
}
