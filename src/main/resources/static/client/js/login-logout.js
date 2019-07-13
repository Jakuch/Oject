$.ajax({
    url: "http://localhost:8080/client/login.html",
    method: "get",
    success: function () {
        $("#if-login-invalid").hide();
    },
})

$("#login-button").click(function (event) {
    event.preventDefault();

    const username = $("#input-username").val();
    const password = $("#input-password").val();

    $.ajax({
        url: "http://localhost:8080/login",
        method: "post",
        headers: {
            "Authorization": "Basic " + btoa(username + ":" + password)
        },
        success: function () {
            window.location.href = "/client/home-page.html";
        },
        error: function () {
            $("#if-login-invalid").show();
            alert("Invalid login or password!")
        }
    });

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

