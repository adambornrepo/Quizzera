$(document).ready(function() {
    var username = sessionStorage.getItem('username');
    
    $.ajax({
        url: "/user/" + username, // İsteğin gönderileceği URL'yi belirtin
        method: "GET",
        success: function(response) {
            var genderval = response.gender.toLowerCase();

            $("#pfirstname").val(response.firstName);
            $("#plastname").val(response.lastName);
            $("#pemail").val(sessionStorage.getItem('email'));
            $("#pbirthdate").val(response.birthdate);
            $("#pgender").val(genderval);
            $("#pusername").val(sessionStorage.getItem('username'));
            $("#ppassword").val(response.password);
        },
        error: function(xhr, status, error) {
            // İstek hata verdiğinde çalışacak kodlar buraya yazılabilir
        }
    });
});
