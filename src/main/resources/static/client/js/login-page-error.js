$.ajax({
    url: "http://localhost:8080/client/login.html",
    method: "get",
    success: function () {
        $("#if-login-invalid").hide();
    },
})