function generateUrl(context) {
	var keyword = $("#keyword").val().trim();
	var nganh = $("#nganh").val();
	var diadiem = $("#diadiem").val();
	url = contextPath + "search/" + context+"?keyword=" + keyword;
	if (nganh != "") {
		url = url + "&industry=" + nganh;
	}
	if (diadiem != "") {
		url = url + "&priorityAddress=" + diadiem;
	}
	return url;
}

function changePage(page, context) {
	var url = generateUrl(context);
	url += "&page=" + page;
	document.location.href = url;
}

function formSubmit(context){
	var keyword = $("#keyword").val().trim();
	var nganh = $("#nganh").val();
	var diadiem = $("#diadiem").val();
	if(keyword==""&&nganh==""&&diadiem==""){
		return fasle;
	}else{
	var url = generateUrl(context);
	document.location.href = url;
	return false;
	}
}

function redirectTo(context){
	var keyword = $("#keyword").val().trim();
	var nganh = $("#nganh").val();
	var diadiem = $("#diadiem").val();
	if(keyword!=""||nganh!=""||diadiem!=""){
		var url = generateUrl(context);
		document.location.href = url;
	}
	
}
