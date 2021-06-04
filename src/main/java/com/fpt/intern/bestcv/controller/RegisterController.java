package com.fpt.intern.bestcv.controller;

import com.fpt.intern.bestcv.aouth.GoogleUtils;
import com.fpt.intern.bestcv.aouth.RestFB;
import com.fpt.intern.bestcv.config.CustomUserDetailsService;
import com.fpt.intern.bestcv.dto.UserDTO;
import com.fpt.intern.bestcv.entity.AspNetUserLogins;
import com.fpt.intern.bestcv.entity.AspNetUsers;
import com.fpt.intern.bestcv.entity.Candidate;
import com.fpt.intern.bestcv.entity.Recruiter;
import com.fpt.intern.bestcv.model.GooglePojo;
import com.fpt.intern.bestcv.reposistory.*;
import com.fpt.intern.bestcv.service.AspNetUserLoginsServcie;
import com.fpt.intern.bestcv.service.AspNetUsersService;
import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Controller
public class RegisterController {

    private static String FB_PROVIDER="Facebook Provider";
    private static String GG_PROVIDER="Google Provider";
    @Autowired
    private AspNetUsersService userService;
    @Autowired
    private PriorityAddressReposistory addressReponsitory;
    @Autowired
    private IndustryReposistory industryReponsitory;
    @Autowired
    private BusinessOrganizationReposistory businessOrganizationReponsitory;
    @Autowired
    private AspNetUsersReposistory usersReponsitory;
    @Autowired
    private GoogleUtils googleUtils;
    @Autowired
    private RestFB restFb;
    @Autowired
    private AspNetUserLoginsReposistory aspNetUserLoginsReponsitory;

    @Autowired
    private AspNetUserLoginsServcie aspNetUserLoginsServcie;
    @GetMapping(value = "/registerUTV")
    public String registerUCVform(Model model){
        model.addAttribute("userdto", new UserDTO());
        model.addAttribute("priority",addressReponsitory.findAll());
        model.addAttribute("industrylist",industryReponsitory.findAll());
        return "/components/register/registerUTV";
    }
    @PostMapping(value = "registerUTV")
    public String registerUCV(UserDTO userDTO,Model model){
        if(notexistEmailandUsername(userDTO.getEmail(), userDTO.getUsername())==true){
                Candidate candidate = userService.registerUCV(userDTO,FB_PROVIDER);
                return "redirect:/login";
        }else{
            UserDTO userDTO1 = new UserDTO();
            userDTO1.setEmail(userDTO.getEmail());
            userDTO1.setFullname(userDTO.getFullname());
            userDTO1.setId(userDTO.getId());
            model.addAttribute("error","Tên tài khoản hoặc email đã tồn tại");
            model.addAttribute("userdto", userDTO1);
            model.addAttribute("priority",addressReponsitory.findAll());
            model.addAttribute("industrylist",industryReponsitory.findAll());
            return "/components/register/registerUTV";
        }
    }
    @PostMapping(value = "registerUTVBT3")
    public String registerUCVBT3(UserDTO userDTO,Model model){
        boolean fb = userDTO.getProvider().equalsIgnoreCase(FB_PROVIDER);
        boolean gg = userDTO.getProvider().equalsIgnoreCase(GG_PROVIDER);
        boolean pass = (userDTO.getPassword().equalsIgnoreCase(""));
        if ( (fb==true || gg==true) && (pass==true)){
            return registerBT3UTV(userDTO,userDTO.getProvider(),model);
        }else{
            return "/components/register/fail";
        }

    }
    @GetMapping(value = "/registerNTD")
    public String registerNTDform(Model model){
        model.addAttribute("userdto", new UserDTO());
        model.addAttribute("priority",addressReponsitory.findAll());
        model.addAttribute("industrylist",industryReponsitory.findAll());
        model.addAttribute("bussinesslist",businessOrganizationReponsitory.findAll());
        return "/components/register/registerNTD";
    }
    @PostMapping(value = "registerNTD")
    public String registerNTD(UserDTO userDTO,Model model){
            if(notexistEmailandUsername(userDTO.getEmail(), userDTO.getUsername())==true){
                    Recruiter recruiter = userService.registerNTD(userDTO,FB_PROVIDER);
                    return "redirect:/login";
            }else{
                UserDTO userDTO1 = new UserDTO();
                userDTO1.setEmail(userDTO.getEmail());
                userDTO1.setFullname(userDTO.getFullname());
                userDTO1.setId(userDTO.getId());
                model.addAttribute("error","Tên tài khoản hoặc email đã tồn tại");
                model.addAttribute("userdto", userDTO1);
                model.addAttribute("priority",addressReponsitory.findAll());
                model.addAttribute("industrylist",industryReponsitory.findAll());
                model.addAttribute("bussinesslist",businessOrganizationReponsitory.findAll());
                return "/components/register/registerNTD";
            }
    }
    @PostMapping(value = "registerNTDBT3")
    public String registerNTDBT3(UserDTO userDTO,Model model){
        boolean fb = userDTO.getProvider().equalsIgnoreCase(FB_PROVIDER);
        boolean gg = userDTO.getProvider().equalsIgnoreCase(GG_PROVIDER);
        boolean pass = (userDTO.getPassword().equalsIgnoreCase(""));
        if ( (fb==true || gg==true) && (pass==true)){
            return registerBT3NTD(userDTO,userDTO.getProvider(),model);
        }else{
            return "/components/register/fail";

        }

    }

    @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
    public String confirmUserAccount(Model model, @RequestParam("token")String confirmationToken)
    {
        AspNetUsers users = usersReponsitory.findById(confirmationToken).get();
        System.out.println("setEmailComfirmed "+ users.isEmailComfirmed());
        if(users.getSecurityStamp() != null)
        {
            users.setEmailComfirmed(true);
            usersReponsitory.save(users);
            return "redirect:/login";
        }
        return "redirect:/registerNTD";
    }
    //kiểm tra có trùng email và username hay không
    public boolean notexistEmailandUsername(String email,String username){
        if(usersReponsitory.findByUserName(username)==null){
            return true;
        }
        else if(usersReponsitory.findByEmail(email)==null){
            return true;
        }
        return false;
    }
    @RequestMapping("/login-google-utv")
    public String loginGoogle(HttpServletRequest request,Model model) throws ClientProtocolException, IOException {
        String code = request.getParameter("code");

        if (code == null || code.isEmpty()) {
            return "redirect:/login?googl=error";
        }
        String accessToken = googleUtils.getTokenUTV(code);
        GooglePojo googlePojo = googleUtils.getUserInfoUCV(accessToken);
        // get form register UCVBT3
        UserDTO userDTO = new UserDTO();
        userDTO.setId(googlePojo.getId());
        userDTO.setEmail(googlePojo.getEmail());
        userDTO.setProvider(GG_PROVIDER);
        model.addAttribute("userdto", userDTO);
        model.addAttribute("priority",addressReponsitory.findAll());
        model.addAttribute("industrylist",industryReponsitory.findAll());
        return "/components/register/registerUTVBT3";
    }
    @RequestMapping("/login-google-ntd")
    public String loginGoogleNTD(HttpServletRequest request,Model model) throws ClientProtocolException, IOException {
        String code = request.getParameter("code");
        if (code == null || code.isEmpty()) {
            return "redirect:/login?googl=error";
        }
        String accessToken = googleUtils.getTokenNTD(code);
        GooglePojo googlePojo = googleUtils.getUserInfoNTD(accessToken);
        // get form register UCVBT3
        UserDTO userDTO = new UserDTO();
        userDTO.setId(googlePojo.getId());
        userDTO.setEmail(googlePojo.getEmail());
        userDTO.setProvider(GG_PROVIDER);
        model.addAttribute("userdto", userDTO);
        model.addAttribute("priority",addressReponsitory.findAll());
        model.addAttribute("industrylist",industryReponsitory.findAll());
        model.addAttribute("bussinesslist",businessOrganizationReponsitory.findAll());
        return "/components/register/registerNTDBT3";
    }
    @RequestMapping("/login-facebook-utv")
    public String loginFacebook(HttpServletRequest request,Model model) throws ClientProtocolException, IOException {
        String code = request.getParameter("code");
        if (code == null || code.isEmpty()) {
            return "redirect:/login?facebook=error";
        }
        String accessToken = restFb.getTokenUTV(code);
        com.restfb.types.User user = restFb.getUserInfoUTV(accessToken);
        System.out.println("UserFB "+user);
            // get form register UCVBT3
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setEmail(user.getEmail());
            userDTO.setFullname(user.getName());
        userDTO.setProvider(FB_PROVIDER);
            model.addAttribute("userdto", userDTO);
            model.addAttribute("priority",addressReponsitory.findAll());
            model.addAttribute("industrylist",industryReponsitory.findAll());

        return "/components/register/registerUTVBT3";
    }
    @RequestMapping("/login-facebook-ntd")
    public String loginFacebookNTD(HttpServletRequest request,Model model) throws ClientProtocolException, IOException {
        String code = request.getParameter("code");
        if (code == null || code.isEmpty()) {
            return "redirect:/login?facebook=error";
        }
        String accessToken = restFb.getTokenNTD(code);
        com.restfb.types.User user = restFb.getUserInfoNTD(accessToken);
        // get form register UCVBT3
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setFullname(user.getName());
        userDTO.setProvider(FB_PROVIDER);
        model.addAttribute("userdto", userDTO);
        model.addAttribute("priority",addressReponsitory.findAll());
        model.addAttribute("industrylist",industryReponsitory.findAll());
        model.addAttribute("bussinesslist",businessOrganizationReponsitory.findAll());
        return "/components/register/registerNTDBT3";
    }
    public String registerBT3UTV(UserDTO userDTO,String provider,Model model){
        if(notexistEmailandUsername(userDTO.getEmail(), userDTO.getUsername())==true){
            if(aspNetUserLoginsServcie.getAspNetUserLoginsByProviderAndByKey(provider,userDTO.getId())!=null){
                UserDTO userDTO1 = new UserDTO();
                userDTO1.setEmail(userDTO.getEmail());
                userDTO1.setFullname(userDTO.getFullname());
                userDTO1.setId(userDTO.getId());
                userDTO1.setProvider(userDTO.getProvider());
                model.addAttribute("error"," Tài khoản này đã được đăng ký");
                model.addAttribute("userdto", userDTO1);
                model.addAttribute("priority",addressReponsitory.findAll());
                model.addAttribute("industrylist",industryReponsitory.findAll());
                return "/components/register/registerUTVBT3";
            }else {
                Candidate candidate = userService.registerUCV(userDTO,provider);
                return "redirect:/login";
            }
        }else{
            UserDTO userDTO1 = new UserDTO();
            userDTO1.setEmail(userDTO.getEmail());
            userDTO1.setFullname(userDTO.getFullname());
            userDTO1.setId(userDTO.getId());
            userDTO1.setProvider(userDTO.getProvider());
            model.addAttribute("error","Tên tài khoản hoặc email đã tồn tại");
            model.addAttribute("userdto", userDTO1);
            model.addAttribute("priority",addressReponsitory.findAll());
            model.addAttribute("industrylist",industryReponsitory.findAll());
            return "/components/register/registerUTVBT3";
        }
    }
    public String registerBT3NTD(UserDTO userDTO,String provider,Model model){
            if(notexistEmailandUsername(userDTO.getEmail(), userDTO.getUsername())==true){
                if(aspNetUserLoginsServcie.getAspNetUserLoginsByProviderAndByKey(provider,userDTO.getId())!=null){
                    UserDTO userDTO1 = new UserDTO();
                    userDTO1.setEmail(userDTO.getEmail());
                    userDTO1.setFullname(userDTO.getFullname());
                    userDTO1.setId(userDTO.getId());
                    userDTO1.setProvider(userDTO.getProvider());
                    model.addAttribute("error"," Tài khoản này đã được đăng ký");
                    model.addAttribute("userdto", userDTO1);
                    model.addAttribute("priority",addressReponsitory.findAll());
                    model.addAttribute("industrylist",industryReponsitory.findAll());
                    model.addAttribute("bussinesslist",businessOrganizationReponsitory.findAll());
                    return "/components/register/registerNTDBT3";
                }else {
                    Recruiter recruiter = userService.registerNTD(userDTO,provider);
                    return "redirect:/login";
                }
            }else{
                UserDTO userDTO1 = new UserDTO();
                userDTO1.setEmail(userDTO.getEmail());
                userDTO1.setFullname(userDTO.getFullname());
                userDTO1.setId(userDTO.getId());
                userDTO1.setProvider(userDTO.getProvider());
                model.addAttribute("error","Tên tài khoản hoặc email đã tồn tại");
                model.addAttribute("userdto", userDTO1);
                model.addAttribute("priority",addressReponsitory.findAll());
                model.addAttribute("industrylist",industryReponsitory.findAll());
                model.addAttribute("bussinesslist",businessOrganizationReponsitory.findAll());
                return "/components/register/registerNTDBT3";
            }
    }


}
