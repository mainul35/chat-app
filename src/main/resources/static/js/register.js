(function () {
    const regForm = $('#register-form');
    const emailRegex = RegExp('(?:[a-z0-9!#$%&\'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&\'*+/=?^_`{|}~-]+)*|"(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21\x23-\x5b\x5d-\x7f]|\\\\[\x01-\x09\x0b\x0c\x0e-\x7f])*")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21-\x5a\x53-\x7f]|\\\\[\x01-\x09\x0b\x0c\x0e-\x7f])+)\\])');
    const passwordRegex = RegExp("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\\$%\\^&\\*])(?=.{8,15})");

    const nameInput = regForm.find('#name');
    const emailInput = regForm.find('#email');
    const passwordInput = regForm.find('#password');
    const rePasswordInput = regForm.find('#re_pass');

    let invalidName = true;
    let invalidEmail = true;
    let invalidPassword = true;
    let invalidRePassword = true;


    nameInput.on('blur', function (e) {
        const nameError = regForm.find('.name-error');
        const _nameInput = $(e.target);
        if (_nameInput.val() === "") {
            nameError.text('Please enter your name');
            invalidName = true;
        } else {
            nameError.find('.name-error').text('');
            invalidName = false;
        }
    });

    emailInput.on('blur', function (e) {
        const emailError = regForm.find('.email-error');
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
        const passwordError = regForm.find('.password-error');
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

    rePasswordInput.on('blur', function (e) {
        const rePasswordError = regForm.find('.rePassword-error');
        if ($(e.target).val() === '') {
            rePasswordError.text('Please enter the password again');
            invalidRePassword = true;
        } else {
            if (passwordRegex.test($(e.target).val())) {
                rePasswordError.text('');
                invalidRePassword = false;
            } else {
                if (rePasswordError.val() !== rePasswordInput.val()) {
                    rePasswordError.text('Passwords did not match');
                    invalidRePassword = true;
                } else {
                    rePasswordError.text('Please enter a valid password (E.g: 1tE$tP@sS)');
                    invalidRePassword = true;
                }
            }
        }
    });

    regForm.find('.form-submit').on('click', function (e) {
        e.preventDefault();
        let registration = {
            'name': nameInput.val(),
            'email': emailInput.val(),
            'password': passwordInput.val()
        };

        if (!invalidName && !invalidEmail && !invalidPassword && !invalidRePassword) {
            submitForm(JSON.stringify(registration));
        } else {
            nameInput.trigger('blur');
            emailInput.trigger('blur');
            passwordInput.trigger('blur');
            rePasswordInput.trigger('blur');
        }
    });

    function submitForm (data) {

        fetch(regForm.attr('action'), {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: data
        })
            .then((resp) => resp.json())
            .then(success).catch(error);
    }

    function success(response) {
        window.location.href = '/public/email-sent';
        console.log(response);
    }

    function error(err) {
        console.log(err);
    }
})();