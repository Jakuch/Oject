
$("#submit-login").click(function (event) {
    event.preventDefault();

    const login = $("#input-login").val();
    const password = $("#input-password").val();

    $.ajax({
        url: "http://localhost:8080/login",
        method: "post",
        headers: {
            "Authorization": "Basic: " + btoa(login + ":" + password)
        },
        success: function () {
            window.location.href = "/client/homePage.html";
        },
        error: function () {
            $("#if-login-invalid").show();
            console.log("Invalid login or password!")
        }
    })
});

$("#submit-logout").click(function () {
    $.ajax({
        url: "http://localhost:8080/logout",
        method: "post",
        headers: {
            "Authorization": "Basic " + btoa(":")
        },
        success: function () {
            window.location.href = "/client/index.html";
        }
    })
});

