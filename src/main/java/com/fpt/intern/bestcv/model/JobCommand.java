package com.fpt.intern.bestcv.model;



import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.fpt.intern.bestcv.entity.Industry;


public class JobCommand {
	private int id;
	@NotBlank(message = "Vui lòng nhập tên nghề")
	@Pattern(regexp = "[a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèé"
			+ "êìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳ"
			+ "ẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừ"
			+ "ỬỮỰỲỴÝỶỸửữựỳỵỷỹ][a-zA-Z0-9ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèé"
			+ "êìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻ"
			+ "ẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ ]{0,60}")
	
	private String jobName;
	
	private Industry industry;
	
	public JobCommand(int id, String jobName, Industry industry) {
		super();
		this.id = id;
		this.jobName = jobName;
		this.industry = industry;
	}
	public JobCommand() {
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public Industry getIndustry() {
		return industry;
	}
	public void setIndustry(Industry industry) {
		this.industry = industry;
	}
	@Override
	public String toString() {
		return "Job [id=" + id + ", jobName=" + jobName + "]";
	}


	
}
