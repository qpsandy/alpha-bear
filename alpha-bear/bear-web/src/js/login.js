function encryptByDES(message, key) {
    var keyHex = CryptoJS.enc.Utf8.parse(key);
    var encrypted = CryptoJS.DES.encrypt(message, keyHex, {
        mode: CryptoJS.mode.ECB,
        padding: CryptoJS.pad.Pkcs7
    });
    return encrypted.toString();
}
$(function() {
	$('#btnSubmit').on('click', function(){
		var username = $("#username").val();
		var password = $("#password").val();
		
		if(username==""){
			$('#errorEmpty').css({'visibility': 'visible'}).html("!用户名不能为空");
			return false;
		}
		
		if(password==""){
			$('#errorEmpty').css({'visibility': 'visible'}).html("!密码不能为空");
			return false;
		}
		$('#errorEmpty').css({'visibility': 'hidden'});

		password = encryptByDES(password,"uums_unknow");
		localStorage.setItem('username', username)

		//登录
		$.ajax({
			url: '/bear/login',
			type: 'post',
			dataType: 'json',
			data: JSON.stringify({"username":username,"password":password}),
			contentType: 'application/json'
		})
		.done(function(data,textStatus,request) {
			console.log(data);
			if(data.succeed == true){
				$('#errorEmpty').css({'visibility': 'hidden'});
				localStorage.setItem('Authorization',request.getResponseHeader('Authorization'));
				window.location.href = './hotelList.html';
			} else {
				$('#errorEmpty').css({'visibility': 'visible'}).html("!用户或者密码错误，请重新输入");
			}
		})
		.fail(function() {
			console.log("error");
		});
	})
});