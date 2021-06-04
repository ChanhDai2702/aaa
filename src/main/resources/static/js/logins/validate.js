function kiemtrasdt()
{
    var numberphone = document.getElementById('numberphone').value;
    var pattnumberphone = /(0)\d{9}$/;
    var check = true;
    if (numberphone == null || numberphone == "") {
        document.getElementById("numberphoneerror").innerHTML = "* Số điện thoại không được để trống";
        check = false;
    }
     else if (pattnumberphone.test(numberphone) == false) {
        document.getElementById("numberphoneerror").innerHTML = "* Số điện thoại phải 10 số và có số đầu là 0";
        check = false;
    } else {
        document.getElementById("numberphoneerror").innerHTML = "";
    }
    return check;
}

function kiemtraten()
{
    var name = document.getElementById('name').value;
    var check = true;


    if (name == null || name == "") {
        document.getElementById("nameerror").innerHTML = "* Họ và tên không được để trống";
        check = false;
    }  else {
        document.getElementById("nameerror").innerHTML = "*";
    }

    return check;

}
function kiemtrausername()
{
    var username = document.getElementById('username').value;
    var check = true;
    var pattusername = /^[a-z\d]+$/;

    if (pattusername.test(username) == false) {
        document.getElementById("usernameerror").innerHTML = "* Tên tài khoản không có ký tự đặc biệt";
        check = false;
    }  else {
        document.getElementById("usernameerror").innerHTML = "*";
    }

    return check;

}
function kiemtraemail()
{
    var email = document.getElementById('email').value;
    var check = true;
    var pattemail = /[a-z0-9]+(@)[a-z]+(.)[a-z]+/;
    if (email == null || email == "") {
        document.getElementById("emailerror").innerHTML = "* Email không được để trống";
        check = false;
    }
    else if (pattemail.test(email) == false) {
        document.getElementById("emailerror").innerHTML = "* Email không đúng định đạng";
        check = false;
    }  else {
        document.getElementById("emailerror").innerHTML = "*";
    }

    return check;

}
function kiemtradiachi()
{
    var address = document.getElementById('address').value;
    var check = true;


    if (address == null || address == "") {
        document.getElementById("addresserror").innerHTML = "* Địa chỉ không được để trống";
        check = false;
    }  else {
        document.getElementById("addresserror").innerHTML = "*";
    }

    return check;

}


function kiemtrapassword()
{

    var password = document.getElementById('password').value;
    var repassword = document.getElementById('repassword').value;
    var check = true;
    if (password == null || password == "") {
        document.getElementById("messloipassword").innerHTML = "* Mật khẩu không được để trống";
        check = false;
    }  else {
        document.getElementById("messloipassword").innerHTML = "*";
    }
    if(repassword !== password){
        document.getElementById("messloirepassword").innerHTML = "* Nhập lại password không chính xác";
        check= false;
    }else{
        document.getElementById("messloirepassword").innerHTML = "";
    }

    return check;
}


function dangky() {

    if( kiemtraten()==true && kiemtrausername()==true && kiemtraemail()==true && kiemtrasdt()==true && kiemtradiachi()==true && kiemtrapassword()==true ){
        return true;
    }    else {
        return false;
    }
}


