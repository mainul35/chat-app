(function() {
    const form = $("#emailForm");
    form.find("[name=email]").val(localStorage.getItem("email"));
    form.find(".verification-submit").on('click', function (e) {
        e.preventDefault();
        console.log("triggering on click");
        $(e)[0].target.click()
    });

    function handleSubmit(e) {
        e.preventDefault();
        let login = {
            'email': emailInput.val(),
            'password': passwordInput.val()
        };

        if (!invalidEmail && !invalidPassword) {
            window.localStorage.setItem("email", emailInput.val());
            loginForm.trigger("submit");
        } else {
            emailInput.trigger('blur');
            passwordInput.trigger('blur');
        }
    }
})()