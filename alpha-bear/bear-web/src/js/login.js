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
			url: '/bear/user/login',
			type: 'post',
			dataType: 'json',
			data: {"username":username,"password":password}
		})
		.done(function(data) {
			//console.log(data);
			if(data.results == "true"){
				$('#errorEmpty').css({'visibility': 'hidden'});
				window.location.href = './bondSystemsList.html';
			} else if(data.results == "false"){
				$('#errorEmpty').css({'visibility': 'visible'}).html("!用户或者密码错误，请重新输入");
			}
		})
		.fail(function() {
			//console.log("error");
		});
	})
});