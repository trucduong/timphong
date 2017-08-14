function showLoader() {
	$('#pageloader').show();
}

function hideLoader() {
	$('#pageloader').hide();
}

function gotoUrl(url, params) {
	console.log({url: url, params: params});
	if (Object.keys(params).length > 0) {
		var paramStr = '';
		$.each(params, function(key, val) {
			paramStr += '&' + key + '=' + val;
		});
		paramStr = '?'+ paramStr.substring(1);
		url += paramStr;
	}

	location.href = url;
	//location.reload();
}

/**
 * success
 * info
 * warning
 * danger
 */
function showNotification(type, content, t) {
	var time = 10000;
	if (t) {
		time = t;
	}
	$('#appAlert').attr('class','alert alert-dismissable alert-' + type);
	$('#appAlertContent').html(content);
	$('#appAlert').show();
	setTimeout(function(){ $('#appAlert').hide(); }, time);
}

/**
 * HttpService
 * 
 */
function sendRequest(method, url, data, success, error, options) {
	try {
		if (!options) {
			options = {};
		}
		
		options['type'] = method;
		options['url'] = url;
		if (options.originalData) {
			options['data'] = data;
		} else {
			options['data'] = JSON.stringify(data);
		}
		
		options['contentType'] = 'application/json; charset=utf-8';
		options['success'] = function(result, status, xhr) {
			console.log(result);
			if(result.type == 'success') {
				success(result.value);
			} else {
				error(result.value, result.description);
			}
		};
		options['error'] = function(xhr, status, err) {
			console.log(err);
			error('service.error.exception', err);
		};
		
		console.log(options)
		$.ajax(options);
	} catch (e) {
		console.log(e);
		error('service.error.exception', e);
	}
}

function load(targetId, data, o) {
	var options = {showloader: true};
	if (o) {
		options = o;
	}
	
	if (options.showloader) {
		showLoader();
	}
	
	var d = {};
	if (data) {
		d = data;
	}
	$.ajax({
		url : $('#' + targetId).attr('data-url'),
		type : 'POST',
		data : d,
		async : true,
		success : function(result, status, xhr) {
			$('#' + targetId).html('');
			$('#' + targetId).html(result)
			if (options.showloader) {
				hideLoader();
			}
		},
		error: function (xhr, status, error) {
			if (options.showloader) {
				hideLoader();
			}
			console.log(error);
			alert("Không tải được dữ liệu, Xin vui lòng thử lại!");
		}
	});
}


//function showModal(modalId) {
//	showLoader();
//	$.ajax({
//		url : $('#' + modalId+'_content').attr('data-url'),
//		type : 'POST',
//		data : {},
//		async : true,
//		success : function(result, status, xhr) {
//			$('#' + modalId+'_content').html(result)
//			hideLoader();
//			$('#' + modalId).modal('show');
//		},
//		error: function (xhr, status, error) {
//			hideLoader();
//			console.log(error);
//			alert("Không tải được dữ liệu, Xin vui lòng thử lại!");
//		}
//	});
//}

$(function() {
	
});