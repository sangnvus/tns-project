var isCate = false;
var isArticle = false;
var isHome = false;
var userAgent = navigator.userAgent.toLowerCase();
var ie = false;
var ie6 = false;
var ie7 = false;
var firefox = false;
var safari = false;
jQuery.fn.exists = function(){return this.length>0;};

if (userAgent.indexOf('msie') > -1) {
	ie = true;
	if (userAgent.indexOf('msie 6.0') > -1) {
		ie6 = true;
	}
	if (userAgent.indexOf('msie 7.0') > -1) {
		ie7 = true;
	}
}else if (userAgent.indexOf('firefox') > -1) {
	firefox = true;
}
else if (userAgent.indexOf('safari') > -1) {
	safari = true;
}


// Hiển thị thời gian
Date.prototype.format = function(config) {
	this.formatString = config.format;
	this.lang = (config.lang) ? config.lang : 'vi';

	this.day = this.getDay();

	this.langText = {
		vi: [
			'Chủ nhật',
			'Thứ Hai',
			'Thứ Ba',
			'Thứ Tư',
			'Thứ Năm',
			'Thứ Sáu',
			'Thứ Bẩy'
		],
		en: [
			'Sunday',
			'Monday',
			'Tuesday',
			'Wednesday',
			'Thursday',
			'Friday',
			'Saturday'
		]
	};

	switch (this.lang) {
		case 'vi':
		case 'en':
		break;

		default:
			this.lang = 'vi';
	}

	this.day = this.langText[this.lang][this.day];

	return this.formatString
		.replace(/DAY/, this.day)
		.replace(/DD/, this.getDate())
		.replace(/MM/, this.getMonth() + 1)
		.replace(/YYYY/, this.getFullYear())
		.replace(/HH/, this.getHours())
		.replace(/MI/, this.getMinutes())
		.replace(/SS/, this.getSeconds())
};

var now = new Date().format({
	format: 'DAY, DD/MM/YYYY, HH:MI [GMT + 7]'
});

if ($('#now').exists() > 0) {
	$('#now').html(now);
}

function get_url_params(name) {
	name = name.replace(/[\[]/,"\\\[").replace(/[\]]/,"\\\]");
	var regexS = "[\\?&]" + name + "=([^#&]*)";
	var regex = new RegExp( regexS );
	var results = regex.exec(window.location.href);
	if( results == null ) {
		return "";
	}
	else {
		return decodeURI(results[1].split('+').join(' '));
	}
}

try {
	if ($('.menu #button').exists()){
		$('.menu #button').click(function () {
			if ($('#menu-vertical').css('display') == 'none') {
				$('#menu-vertical').hide();
				$('#menu-vertical').show();
				$('#main').css('height', $( window ).height());
				$("#menu-vertical").css('float', 'left');
				$("#main").css('margin-left', '285px');
				$("body").addClass('noverflow');
			} else {
				$('#menu-vertical').hide('fast');
				$("#menu-vertical").css('float', 'none');
				$('#main').css('height', 'auto');
				$("#main").css('margin-left', '0');
				$("body").removeClass('noverflow');
			}
		})

	}
}catch (e) {}


$(document).ready(function(){

		$.ajax({
		url: "common/v1/jquery/jquery.carouFredSel-6.2.1-packed.js"
		}).done(function() {

			$('#car1-slider').carouFredSel({
				pagination: '#car1-pager',
				auto: true,
				scroll : {
					items: 1,
					fx: "crossfade",
					duration: 800
				}
			});

	});


});


/*form dang ky*/
function validate_email(email) {
	var objRegExp = /\b[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}\b/i;
	return objRegExp.test(email);
}

function validFeedbackForm(obj) {
	if (obj.register_name.value == "") {
		alert( "Họ và tên không được bỏ trống" );
		obj.register_name.focus();
		return false ;
	}

	if (obj.email.value == "") {
		alert( "Email không được bỏ trống" );
		obj.email.focus();
		return false ;
	}

	if (obj.phone.value == "") {
		alert( "Số điện thoại không được bỏ trống" );
		obj.phone.focus();
		return false ;
	}

	if (obj.creditcode.value == "") {
		alert( "Số thẻ tín dụng không được bỏ trống" );
		obj.title.focus();
		return false ;
	}

	if (!validate_email(obj.email.value)) {
		alert( "Email không đúng định dạng" );
		obj.email.focus();
		return false ;
	}

	return true;
}


if ($('#register_acc').exists()) {
	$("#register_acc" ).submit(function( event ) {
		if (validFeedbackForm(this)) {
			alert("viet code dang ky tai khoan")
		}
		return false;
	});
}


