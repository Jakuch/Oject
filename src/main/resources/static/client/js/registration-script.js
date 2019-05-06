$("#submit-register").click(function () {

    var forms = document.getElementsByClassName('needs-validation');
    Array.prototype.filter.call(forms, function (form) {
        form.addEventListener('submit', function (event) {
            if (form.checkValidity() === false) {
                event.preventDefault();
                event.stopPropagation();
            }
            form.classList.add('was-validated');
        }, false);
    });

    const login = $("#register-login").val();
    const password = $("#register-password").val();
    const confirmPassword = $("#register-confirm-pass").val();
    const email = $("#register-email").val();

    if (password == confirmPassword) {

        const newUser = {
            username: login,
            password: password,
            email: email,
            role: "USER"
        };

        $.ajax({
            url: "http://localhost:8080/users",
            method: "post",
            data: JSON.stringify(newUser),
            contentType: "application/json",
            success: function () {
                event.preventDefault();

                $.ajax({
                    url: "http://localhost:8080/login",
                    method: "post",
                    headers: {
                        "Authorization": "Basic: " + btoa(login + ":" + password)
                    },
                    success: function () {
                        window.location.href = "/client/homePage.html";
                    },
                })
            },
            error: function () {
                $(".is-invalid").removeClass("is-invalid");
                const propertyValidationErrors = response.responseJSON;
                for (let i = 0; i < propertyValidationErrors.length; i++) {
                    const propertyValidationError = propertyValidationErrors[i];
                    $("#" + propertyValidationError.property).addClass(("is-invalid"));
                }
            }

        })
    }
});