/* Template: Corso - Free Training Course Landing Page Template
   Author: Inovatik
   Created: Nov 2019
   Description: Custom JS file
*/


(function($) {
    "use strict"; 
	
	/* Preloader */
	$(window).on('load', function() {
		var preloaderFadeOutTime = 500;
		function hidePreloader() {
			var preloader = $('.spinner-wrapper');
			setTimeout(function() {
				preloader.fadeOut(preloaderFadeOutTime);
			}, 500);
		}
		hidePreloader();
	});

	
	/* Navbar Scripts */
	// jQuery to collapse the navbar on scroll
    $(window).on('scroll load', function() {
		if ($(".navbar").offset().top > 60) {
			$(".fixed-top").addClass("top-nav-collapse");
		} else {
			$(".fixed-top").removeClass("top-nav-collapse");
		}
    });

	// jQuery for page scrolling feature - requires jQuery Easing plugin
	$(function() {
		$(document).on('click', 'a.page-scroll', function(event) {
			var $anchor = $(this);
			$('html, body').stop().animate({
				scrollTop: $($anchor.attr('href')).offset().top
			}, 600, 'easeInOutExpo');
			event.preventDefault();
		});
	});

    // closes the responsive menu on menu item click
    $(".navbar-nav li a").on("click", function(event) {
    if (!$(this).parent().hasClass('dropdown'))
        $(".navbar-collapse").collapse('hide');
    });


    /* Countdown Timer - The Final Countdown */
	$('#clock').countdown('2020/12/27 08:50:56') /* change here your "countdown to" date */
	.on('update.countdown', function(event) {
		var format = '<span class="counter-number">%D<br><span class="timer-text">Days</span></span><span class="counter-number">%H<br><span class="timer-text">Hours</span></span><span class="counter-number">%M<br><span class="timer-text">Minutes</span></span><span class="counter-number">%S<br><span class="timer-text">Seconds</span></span>';
		$(this).html(event.strftime(format));
	})
	.on('finish.countdown', function(event) {
	$(this).html('This offer has expired!')
		.parent().addClass('disabled');
    });


    /* Image Slider 2 - Swiper */
    var imageSliderOne = new Swiper('.image-slider-1', {
        autoplay: {
            delay: 3000,
            disableOnInteraction: false
		},
        loop: true,
        navigation: {
			nextEl: '.swiper-button-next',
			prevEl: '.swiper-button-prev',
		}
    });


    /* Image Slider - Swiper */
    var imageSliderTwo = new Swiper('.image-slider-2', {
        autoplay: {
            delay: 2000,
            disableOnInteraction: false
		},
        loop: true,
        spaceBetween: 30,
        slidesPerView: 5,
		breakpoints: {
            // when window is <= 580px
            580: {
                slidesPerView: 1,
                spaceBetween: 10
            },
            // when window is <= 768px
            768: {
                slidesPerView: 2,
                spaceBetween: 20
            },
            // when window is <= 992px
            992: {
                slidesPerView: 3,
                spaceBetween: 20
            },
            // when window is <= 1200px
            1200: {
                slidesPerView: 4,
                spaceBetween: 20
            },

        }
    });


    /* Text Slider - Swiper */
	var textSlider = new Swiper('.text-slider', {
        autoplay: {
            delay: 6000,
            disableOnInteraction: false
		},
        loop: true,
        navigation: {
			nextEl: '.swiper-button-next',
			prevEl: '.swiper-button-prev'
        },
        spaceBetween: 70,
        slidesPerView: 2,
		breakpoints: {
            // when window is <= 1199px
            1199: {
                slidesPerView: 1,
            },
        }
    });

    
    
    /* Move Form Fields Label When User Types */
    // for input and textarea fields
    $("input, textarea").keyup(function(){
		if ($(this).val() != '') {
			$(this).addClass('notEmpty');
		} else {
			$(this).removeClass('notEmpty');
		}
    });


    /* Registration Form */
    $("#registrationForm").validator().on("submit", function(event) {
    	if (event.isDefaultPrevented()) {
            rsubmitMSG("Please fill all fields!");
        } else {
            // everything looks good!
            event.preventDefault();
            rsubmitForm();
        }
    });

    function rsubmitForm() {
        // initiate variables with form content
        var firstname = $("#rfirstname").val();
        var lastname = $("#rlastname").val();
        var email = $("#remail").val();
        var birthdate = $("#rbirthdate").val();
        var gender = $("#rgender").val().toUpperCase();
        var username = $("#rusername").val();
        var password = $("#rpassword").val();
    
        var data = {
            firstName: firstname,
            lastName: lastname,
            email: email,
            birthdate: birthdate,
            gender: gender,
            username: username,
            password: password
        };
    
        $.ajax({
            type: "POST",
            url: "/user",
            data: JSON.stringify(data),
            
            contentType: "application/json",

            success: function(response, textStatus, xhr) {
                if (xhr.status === 201) {
                    rformSuccess(response);
                } else {
                    alert(response);
                }
            },

            error: function(xhr, status, error, response) {
                if (xhr.status === 400) {


                    var errorMessages = JSON.parse(xhr.responseText);
                    var errorMessageList = errorMessages.map(function(message) {
                        return "- " + message;
                    }).join("\n");
                    
                    alert("Validation Errors:\n" + errorMessageList);

                    rsubmitMSG("Try Again!");
                }


                var errorResponse = JSON.parse(xhr.responseText);
                var errorMessage = errorResponse.message;
                rsubmitMSG(errorMessage);
            }
        });
    }
    

    function rformSuccess(response) {
        $("#registrationForm")[0].reset();
        rsubmitMSG(response);
        $("input").removeClass('notEmpty'); // resets the field label after submission
    }

    function rsubmitMSG(msg) {

        var msgClasses = "h3 text-center";

        $("#rmsgSubmit").removeClass().addClass(msgClasses).text(msg);
    }
    

    /* Login Form */
    $("#loginForm").validator().on("submit", function(event) {
    	if (event.isDefaultPrevented()) {
            // handle the invalid form...
            lformError();
            lsubmitMSG(false, "Please fill all fields!");
        } else {
            // everything looks good!
            event.preventDefault();
            lsubmitForm();
        }
    });

    function lsubmitForm() {
        // initiate variables with form content
        var username = $("#lusername").val();
        var password = $("#lpassword").val();

        var data = {
            username: username,
            password: password
        };

        $.ajax({
            type: "POST",
            url: "/user/login",
            data: JSON.stringify(data),
            
            contentType: "application/json",

            success: function(response, textStatus, xhr) {
                if (xhr.status === 200) {
                    lformSuccess(response);
                } else {
                    alert("Unexpected Situation");
                    console.log(response);
                }
            },

            error: function(xhr, status, error, response) {
                $("#loginForm")[0].reset();
                $("input").removeClass('notEmpty');

                if (xhr.status === 400) {

                    var errorMessages = JSON.parse(xhr.responseText);
                    var errorMessageList = errorMessages.map(function(message) {
                        return "- " + message;
                    }).join("\n");
                    
                    alert("Validation Errors:\n" + errorMessageList);

                    lsubmitMSG("Try Again!");
                }


                var errorResponse = JSON.parse(xhr.responseText);
                var errorMessage = errorResponse.message;
                lsubmitMSG(errorMessage);
            }
        });
    }

    function lformSuccess(response) {
        
        // Take all info to storage
        sessionStorage.setItem('id', response.id);
        sessionStorage.setItem('username', response.username);
        sessionStorage.setItem('email', response.email);
        sessionStorage.setItem('role', response.role);
        sessionStorage.setItem('firstName', response.firstName);
        sessionStorage.setItem('lastName', response.lastName);

        $("#loginForm")[0].reset();
        $("input").removeClass('notEmpty'); // resets the field label after submission

        if (response.role === 'ROLE_MODERATOR') {
            window.location.replace('/moderatorpage.html');
        } else if (response.role === 'ROLE_QUIZZER') {
            window.location.replace('/quizzerpage.html');
        }
    }

    function lsubmitMSG(msg) {

        var msgClasses = "h3 text-center";
        
        $("#lmsgSubmit").removeClass().addClass(msgClasses).text(msg);
    }


    /* Contact Form */
    $("#contactForm").validator().on("submit", function(event) {
    	if (event.isDefaultPrevented()) {
            // handle the invalid form...
            cformError();
            csubmitMSG(false, "Please fill all fields!");
        } else {
            // everything looks good!
            event.preventDefault();
            csubmitForm();
        }
    });

    function csubmitForm() {
        // initiate variables with form content
		var name = $("#cname").val();
		var email = $("#cemail").val();
        var message = $("#cmessage").val();
        var terms = $("#cterms").val();
        $.ajax({
            type: "POST",
            url: "php/contactform-process.php",
            data: "name=" + name + "&email=" + email + "&message=" + message + "&terms=" + terms, 
            success: function(text) {
                if (text == "success") {
                    cformSuccess();
                } else {
                    cformError();
                    csubmitMSG(false, text);
                }
            }
        });
	}

    function cformSuccess() {
        $("#contactForm")[0].reset();
        csubmitMSG(true, "Message Submitted!");
        $("input").removeClass('notEmpty'); // resets the field label after submission
        $("textarea").removeClass('notEmpty'); // resets the field label after submission
    }

    function cformError() {
        $("#contactForm").removeClass().addClass('shake animated').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function() {
            $(this).removeClass();
        });
	}

    function csubmitMSG(valid, msg) {
        if (valid) {
            var msgClasses = "h3 text-center tada animated";
        } else {
            var msgClasses = "h3 text-center";
        }
        $("#cmsgSubmit").removeClass().addClass(msgClasses).text(msg);
    }


    /* Privacy Form */
    $("#privacyForm").validator().on("submit", function(event) {
    	if (event.isDefaultPrevented()) {
            // handle the invalid form...
            pformError();
            psubmitMSG(false, "Please fill all fields!");
        } else {
            // everything looks good!
            event.preventDefault();
            psubmitForm();
        }
    });

    function psubmitForm() {
        // initiate variables with form content
		var name = $("#pname").val();
		var email = $("#pemail").val();
        var select = $("#pselect").val();
        var terms = $("#pterms").val();
        
        $.ajax({
            type: "POST",
            url: "php/privacyform-process.php",
            data: "name=" + name + "&email=" + email + "&select=" + select + "&terms=" + terms, 
            success: function(text) {
                if (text == "success") {
                    pformSuccess();
                } else {
                    pformError();
                    psubmitMSG(false, text);
                }
            }
        });
	}

    function pformSuccess() {
        $("#privacyForm")[0].reset();
        psubmitMSG(true, "Request Submitted!");
        $("input").removeClass('notEmpty'); // resets the field label after submission
    }

    function pformError() {
        $("#privacyForm").removeClass().addClass('shake animated').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function() {
            $(this).removeClass();
        });
	}

    function psubmitMSG(valid, msg) {
        if (valid) {
            var msgClasses = "h3 text-center tada animated";
        } else {
            var msgClasses = "h3 text-center";
        }
        $("#pmsgSubmit").removeClass().addClass(msgClasses).text(msg);
    }


    //Bu kısım storage işlemlerini kontrol
    $(document).ready(function() {
        // Storage'dan bilgileri al
        var id = sessionStorage.getItem('id');
        var username = sessionStorage.getItem('username');
        var email = sessionStorage.getItem('email');
        var role = sessionStorage.getItem('role');
        var firstName = sessionStorage.getItem('firstName');
        var lastName = sessionStorage.getItem('lastName');
    
        // Bilgileri göster
        var userInfoDiv = $('#userInfo');
        userInfoDiv.html(`
            <p>ID: ${id}</p>
            <p>Username: ${username}</p>
            <p>Email: ${email}</p>
            <p>Role: ${role}</p>
            <p>First Name: ${firstName}</p>
            <p>Last Name: ${lastName}</p>
        `);
    });
    

    //Bu kısım storage işlemlerini kontrol


    

    /* Back To Top Button */
    // create the back to top button
    $('body').prepend('<a href="body" class="back-to-top page-scroll">Back to Top</a>');
    var amountScrolled = 700;
    $(window).scroll(function() {
        if ($(window).scrollTop() > amountScrolled) {
            $('a.back-to-top').fadeIn('500');
        } else {
            $('a.back-to-top').fadeOut('500');
        }
    });


	/* Removes Long Focus On Buttons */
	$(".button, a, button").mouseup(function() {
		$(this).blur();
	});

})(jQuery);