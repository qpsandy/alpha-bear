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
		url: '/bear/service/hotel/page',
		headers: {
		    'Authorization': localStorage.getItem('Authorization') || ''
		  },
		type: 'post',
		dataType: 'json',
		data: '{"pageNo":1,"pageSize":10}',
		contentType: 'application/json'
	})
	.done(function(data) {
		//console.log(data);
		arr = data.result;
		var arrList = [];
		var head = '<tr>\
						<th>酒店名称</th><th>酒店房型</th>\
						<th>酒店早餐</th><th>酒店库存</th>\
						<th>酒店价格</th><th>酒店价格变化</th>\
						<th>更新时间</th>\
					</tr>';
		arrList.push(head);
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

		var setTotalCount = data.totalCount;
		$('#box').paging({
	        initPageNo: 1, // 初始页码
	        totalPages: data.totalPages, //总页数
	        totalCount: '共' + setTotalCount + '条', // 条目总数
	        slideSpeed: 600, // 缓动速度。单位毫秒
	        jump: true, //是否支持跳转
	        callback: function(page) { // 回调函数
	            console.log("jump to page " + page);
	            $.ajax({
                    url: '/bear/service/hotel/page',
                    headers: {
                        'Authorization': localStorage.getItem('Authorization') || ''
                      },
                    type: 'post',
                    dataType: 'json',
                    data: '{"pageNo":'+ page + ',"pageSize":10}',
                    contentType: 'application/json'
                })
                .done(function(data) {
                    //console.log(data);
                    arr = data.result;
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
	        }
    	})
	})
	.fail(function() {
		//console.log("error");
	});
	
	
	// 当点击"查询"
	searchHotel = function (){
		var hotelname = $("#hotelname").val();
		

		//根据酒店名字模糊查询
		$.ajax({
			url: '/bear/service/hotel/page',
			headers: {
			    'Authorization': localStorage.getItem('Authorization') || ''
			  },
			type: 'post',
			dataType: 'json',
			data: '{"pageNo":1,"pageSize":10,"hotelname":"' + hotelname + '"}',
			contentType: 'application/json'
		})
		.done(function(data) {
			//console.log(data);
			arr = data.result;
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

			var setTotalCount = data.totalCount;
			$('#box').paging({
		        initPageNo: 1, // 初始页码
		        totalPages: data.totalPages, //总页数
		        totalCount: '共' + setTotalCount + '条', // 条目总数
		        slideSpeed: 600, // 缓动速度。单位毫秒
		        jump: true, //是否支持跳转
		        callback: function(page) { // 回调函数
		            console.log("jump to page " + page);
		            $.ajax({
	                    url: '/bear/service/hotel/page',
	                    headers: {
	                        'Authorization': localStorage.getItem('Authorization') || ''
	                      },
	                    type: 'post',
	                    dataType: 'json',
						data: '{"pageNo":' + page + ',"pageSize":10,"hotelname":"' + hotelname + '"}',
	                    contentType: 'application/json'
	                })
	                .done(function(data) {
	                    //console.log(data);
	                    arr = data.result;
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
		        }
	    	})
		})
		.fail(function() {
			//console.log("error");
		});
	}
	
	
});