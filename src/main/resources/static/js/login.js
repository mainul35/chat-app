(function () {
    const loginForm = $('#login-form');
    const emailRegex = RegExp('(?:[a-z0-9!#$%&\'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&\'*+/=?^_`{|}~-]+)*|"(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21\x23-\x5b\x5d-\x7f]|\\\\[\x01-\x09\x0b\x0c\x0e-\x7f])*")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21-\x5a\x53-\x7f]|\\\\[\x01-\x09\x0b\x0c\x0e-\x7f])+)\\])');
    const passwordRegex = RegExp("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\\$%\\^&\\*])(?=.{8,15})");

    const emailInput = loginForm.find('#email');
    const passwordInput = loginForm.find('#password');

    let invalidEmail = true;
    let invalidPassword = true;

    emailInput.on('blur', function (e) {
        const emailError = loginForm.find('.email-error');
        if ($(e.target).val() === "") {
            emailError.text('Please enter your email address');
            invalidEmail = true;
        } else {
            if (emailRegex.test($(e.target).val())) {
                emailError.text('');
                invalidEmail = false;
            } else {
                emailError.text('Please enter a valid email address');
                invalidEmail = true;
            }
        }
    });

    passwordInput.on('blur', function (e) {
        const passwordError = loginForm.find('.password-error');
        if ($(e.target).val() === '') {
            passwordError.text('Please enter a strong password (E.g: 1tE$tP@sS)');
            invalidPassword = true;
        } else {
            if (passwordRegex.test($(e.target).val())) {
                passwordError.text('');
                invalidPassword = false;
            } else {
                passwordError.text('Please enter a valid password (E.g: 1tE$tP@sS)');
                invalidPassword = true;
            }
        }
    });

    loginForm.find('.form-submit').on('click', function (e) {
        e.preventDefault();
        let login = {
            'email': emailInput.val(),
            'password': passwordInput.val()
        };

        if (!invalidEmail && !invalidPassword) {
            loginForm.trigger("submit");
        } else {
            emailInput.trigger('blur');
            passwordInput.trigger('blur');
        }
    });
})();