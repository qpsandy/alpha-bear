function encryptByDES(message, key) {
    var keyHex = CryptoJS.enc.Utf8.parse(key);
    var encrypted = CryptoJS.DES.encrypt(message, keyHex, {
        mode: CryptoJS.mode.ECB,
        padding: CryptoJS.pad.Pkcs7
    });
    return encrypted.toString();
}
$(function() {
	var arr = [];
	$.ajax({
		url: '/bear/hotel/page',
		type: 'post',
		dataType: 'json',
		data: '{"pageNo":1,"pageSize":10}',
		contentType: 'application/json'
	})
	.done(function(data) {
		//console.log(data);
		arr = data;
		var arrList = [];
		var head = '<tr>\
						<th>酒店名称</th><th>酒店房型</th>\
						<th>酒店早餐</th><th>酒店库存</th>\
						<th>酒店价格</th><th>酒店价格变化</th>\
						<th>更新时间</th>\
					</tr>';
		arrList.push(head);
		//有多个应用时，必须列出这些 应用
		for(var i=0; i<arr.length; i++){
			//str += '<li><a data-href="'+arr[i].bizUrl+'" data-guid="'+arr[i].guid+'" href="javascript:;">'+arr[i].name+'</a></li>';
			var str = '<tr>\
				        <td>'+arr[i].hotelname+'</td><td>'+arr[i].fx+'</td>\
				        <td>'+arr[i].zc+'</td><td>'+arr[i].kc+'</td>\
				        <td>'+arr[i].price+'</td><td>'+arr[i].price+'</td>\
				        <td>'+arr[i].updatetime+'</td>\
				       </tr>'
			arrList.push(str);
		}
		$('#BMOMSWEB').html(arrList.join(''));
	})
	.fail(function() {
		//console.log("error");
	});
	
	
	// 当"点击进入"
	function productEntry(target){
		var dataGuid = $(target).attr('data-guid'),
        dataHref = $(target).attr('data-href'),
        dataEkeyOnly = $(target).attr('data-ekeyOnly');
		var dataPrond = {};
		dataPrond.guid = dataGuid;
		dataPrond.bizUrl = dataHref;
		dataPrond.ekeyOnly = dataEkeyOnly;
		
		//该应用还需要输入用户名密码
		if(+dataEkeyOnly == 1){
			showUserPaswordDialog(dataPrond);
		}else{
			//无用户名密码登录
			directLogin(dataPrond, true);
		}
	}
	// 当"点击进入"
	$('#BMOMSWEB').on('click', 'a', function(){
		productEntry(this);
	});
	
	
	//解决ie referrer为空的问题 模拟A标签跳转
	function createAlabelReferrer(url,bool){
		if(window.VBArray){
			var eleA = document.createElement('a');
			eleA.href = url;
			if(!bool){
				eleA.target = '_blank';
			}
			document.body.appendChild(eleA);
			eleA.click();
		} else {
			bool ? window.location.replace(url):window.open(url);
		}
	}
	
	//不用再输入用户名密码，覆盖或新开tab方式跳转
	function directLogin(dataObj,boolean){
		$.ajax({
			url: '/bond/api/uums/userinfo/token',
			type: 'post',
			dataType: 'json',
			data: JSON.stringify({"guid" : dataObj.guid,"username":"",password:""}),
			async: false,
			contentType: 'application/json'
		})
		.done(function(data) {
			//console.log(data);
			//window.open(dataHref+'_token='+data.token);
			//解决ie referrer为空的问题
			var url_prond = dataObj.bizUrl+'?_token='+data.token;
			createAlabelReferrer(url_prond, boolean);
		})
		.fail(function() {
			//console.log("error");
		});
	}
	
	
	//显示用户名密码界面
	function showUserPaswordDialog(arr){
		//内部用户不存在这个情况，该逻辑后台会保证
		//外部用户 ,如果需要输入用户密码，直接显示用户名密码输入界面
		$('body').css({'overflow':'hidden'});
		//特殊处理,如果是fms，还要添加下忘记 密码功能
		if(arr.guid == 'BM_FMS_WEB'){
			var arryFp = arr.bizUrl.split('/');
			var forgePw = '<a href="'+arryFp[0]+'//'+arryFp[2]+'/fms/login/resetPas">忘记密码?</a>';
			$('#forgetPassword').addClass('forget-password').html(forgePw);
		}
		//显示输入用户密码的对话框
		$('#div2').show();$('#div1').hide();

		//点击登录按钮后
		$('#btnSubmit1').on('click',function(){
			var username1=$("#username1").val();
			var password1=$("#password1").val();

			if(username1==""){
				$('#errorEmpty1').css({'visibility': 'visible'}).html("!用户名不能为空");
				return;
			}
			
			if(password1==""){
				$('#errorEmpty1').css({'visibility': 'visible'}).html("!密码不能为空");
				return;
			}
			
		    localStorage.setItem('username', username1)

			$.ajax({
				url: '/bond/api/uums/userinfo/token',
				type: 'post',
				dataType: 'json',
				data: JSON.stringify({"guid" : arr.guid,"username":username1,"password":password1}),
				contentType: 'application/json'
			})
			.done(function(data) {
				//console.log(data);
				if(+data.valid==1){
					$('#errorEmpty1').css({'visibility': 'visible'}).html('!'+data.reason);
					return;
				}
				//window.location.replace(arr[0].bizUrl+'_token='+data.token);
				//alert(window.VBArray);
				//解决ie referrer为空的问题
				var url_prond = arr.bizUrl+'?_token='+data.token;
				createAlabelReferrer(url_prond, true);
			})
			.fail(function() {
				//console.log("error");
			});
		})
	}
});