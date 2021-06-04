  function readURLBanner(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                $('#blah')
                    .attr('src', e.target.result)
                    .width(200)
                    .height(150);
            };
            reader.readAsDataURL(input.files[0]);
        }
    }
    function readURLReview(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                $('#review')
                    .attr('src', e.target.result)
                    .width(250)
                    .height(150);
            };
            reader.readAsDataURL(input.files[0]);
        }
    }
	$(function() {
		  $("form[name='addDisplay']").validate({	
			  rules: {	  
				  DisplayName:{
					  required: true,
		              minlength: 5
					 },
				  FontSize:{
					  min: 3,
				   },
				   fileBanner:{
					   required: true,
		                extension: "jpg,jpeg, png"
					},
					fileReview:{
						   required: true,
			                extension: "jpg,jpeg, png"
					}
				},
				messages: {
				  DisplayName: {
					  required:"tên giao diện không được bỏ trống",
					  minlength:"Tên giao diện tối thiểu là 2"
				  },
				  FontSize: {
					  min:"kích cỡ font tối thiểu là 3px",
				  },
				   fileBanner:{
					   required: "Vui lòng chọn hình",
		                extension: "Hình không hợp lệ"
					},
					fileReview:{
						   required: "Vui lòng chọn hình",
			                extension: "Hình không hợp lệ"
					}
				},
				submitHandler: function(form) {
				  form.submit();
				}
			  });
	});