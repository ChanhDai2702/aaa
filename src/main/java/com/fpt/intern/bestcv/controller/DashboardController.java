package com.fpt.intern.bestcv.controller;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fpt.intern.bestcv.dto.LuotXemDTO;
import com.fpt.intern.bestcv.dto.LuotXemNganhDTO;
import com.fpt.intern.bestcv.entity.CurriculumVitae;
import com.fpt.intern.bestcv.model.Data;
import com.fpt.intern.bestcv.service.AspNetUsersService;
import com.fpt.intern.bestcv.service.CVRecruimentService;
import com.fpt.intern.bestcv.service.CandidateService;
import com.fpt.intern.bestcv.service.CurriculumVitaeService;
import com.fpt.intern.bestcv.service.DetailRegisterVipService;
import com.fpt.intern.bestcv.service.IndustryService;
import com.fpt.intern.bestcv.service.PriorityAddressService;
import com.fpt.intern.bestcv.service.RecruimentNewsService;
import com.fpt.intern.bestcv.service.RecruiterService;

@Controller
@RequestMapping(value = "/dashboard")
public class DashboardController {
	private static final Random RANDOM = new Random(System.currentTimeMillis());
	@Autowired
	private PriorityAddressService priorityAddressService;
	@Autowired
	private IndustryService industryService;
	@Autowired
	private CurriculumVitaeService cvService;
	@Autowired
	private CVRecruimentService cvRecruitService;
	@Autowired
	private AspNetUsersService userService;
	@Autowired
	private RecruiterService recruiterService;
	@Autowired
	DetailRegisterVipService detailVipSrervice;
	@Autowired
	CandidateService canService;
	@Autowired
	RecruimentNewsService newsService;

	@GetMapping
	public String listDisplayChoose(Model model) {
		DecimalFormat formatter = new DecimalFormat("###,###,###.##");

		model.addAttribute("industry", industryService.getListAllIndustry());
		model.addAttribute("priorityAddress", priorityAddressService.findAll());
		model.addAttribute("countCV", cvService.getCount());
		model.addAttribute("countUser", userService.getCount());
		model.addAttribute("countRecruited", cvRecruitService.getCountRecruited());
		model.addAttribute("countRecruiter", recruiterService.getCount());
		model.addAttribute("userIncrease", userService.getIncrease());
		model.addAttribute("percentCV", cvService.getPercentIncrease());
		model.addAttribute("percentCVRecruit", cvRecruitService.getPercentIncrease());
		model.addAttribute("percentRecruit", recruiterService.getPercentIncrease());
		model.addAttribute("sumDoanhThu", formatter.format(detailVipSrervice.getDoanhThu()));
		model.addAttribute("perDoanhThu", detailVipSrervice.getDoanhThuIncrease());

		model.addAttribute("dataDoanhThu", getLineChartData());
		model.addAttribute("dataTuongTac", getColumnChartData());
		model.addAttribute("dataTinTuyenDung", getLineChartBaiDangData());
		model.addAttribute("dataThongKe", getPieChartData());

		return "components/admin/dashboard";
	}

	public List<Data> getColumnChartData() {
		List<Data> ds = new ArrayList<Data>();
		List<LuotXemDTO> cv = canService.getTopThreeView();
		cv.forEach(x -> {
			ds.add(new Data(x.getFullName(), x.getViews()));
		});
		return ds;
	}

	public List<Data> getPieChartData() {
		List<Data> ds = new ArrayList<Data>();
		List<LuotXemNganhDTO> cv = cvService.getTopCVByIndustry();
		for (LuotXemNganhDTO data : cv) {
			ds.add(new Data(data.getIndustryName(), data.getViews()));

		}

		System.out.println(ds);
		return ds;
	}

	public List<Data> getLineChartData() {
		List<Data> ds = new ArrayList<Data>();
		Date date = new Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		YearMonth ymCurrent = YearMonth.from(localDate);
		YearMonth ymPrevious = ymCurrent.minusMonths(1);
		YearMonth ymTwoPrevious = ymCurrent.minusMonths(2);
		YearMonth ymThreePrevious = ymCurrent.minusMonths(3);
		YearMonth ymFourPrevious = ymCurrent.minusMonths(4);
		YearMonth ymFivePrevious = ymCurrent.minusMonths(5);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
		ds.add(new Data("Tháng " + formatter.format(ymFivePrevious), detailVipSrervice.getDoanhThuByMonth(5)));
		ds.add(new Data("Tháng " + formatter.format(ymFourPrevious), detailVipSrervice.getDoanhThuByMonth(4)));
		ds.add(new Data("Tháng " + formatter.format(ymThreePrevious), detailVipSrervice.getDoanhThuByMonth(3)));
		ds.add(new Data("Tháng " + formatter.format(ymTwoPrevious), detailVipSrervice.getDoanhThuByMonth(2)));
		ds.add(new Data("Tháng " + formatter.format(ymPrevious), detailVipSrervice.getDoanhThuByMonth(1)));
		ds.add(new Data("Tháng " + formatter.format(ymCurrent), detailVipSrervice.getDoanhThuByMonth(0)));

		return ds;
	}

	public List<Data> getLineChartBaiDangData() {
		List<Data> ds = new ArrayList<Data>();
		Date date = new Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		YearMonth ymCurrent = YearMonth.from(localDate);
		YearMonth ymPrevious = ymCurrent.minusMonths(1);
		YearMonth ymTwoPrevious = ymCurrent.minusMonths(2);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");

		ds.add(new Data("Tháng " + formatter.format(ymTwoPrevious), newsService.getNewsByMonth(2)));
		ds.add(new Data("Tháng " + formatter.format(ymPrevious), newsService.getNewsByMonth(1)));
		ds.add(new Data("Tháng " + formatter.format(ymCurrent), newsService.getNewsByMonth(0)));

		return ds;
	}

}
