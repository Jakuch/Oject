$.ajax({
    url: "http://localhost:8080/client/home-page.html",
    method: "get",
    success: function () {
        $.ajax({
            url: "http://localhost:8080/projects",
            method: "get",
            success: function (projects) {
                //TODO display recent on homepage
            }
        })
    },
    error: function () {
        window.location.href = "/client/login.html";
    }
});