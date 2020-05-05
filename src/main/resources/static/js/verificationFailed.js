(function () {
    const verificationForm = $('#verification-failed');
    const emailRegex = RegExp('(?:[a-z0-9!#$%&\'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&\'*+/=?^_`{|}~-]+)*|"(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21\x23-\x5b\x5d-\x7f]|\\\\[\x01-\x09\x0b\x0c\x0e-\x7f])*")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21-\x5a\x53-\x7f]|\\\\[\x01-\x09\x0b\x0c\x0e-\x7f])+)\\])');

    const emailInput = verificationForm.find('#email');

    let invalidEmail = true;

    emailInput.on('blur', function (e) {
        const emailError = verificationForm.find('.email-error');
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

    verificationForm.find('.form-submit').on('click', function (e) {
        e.preventDefault();

        if (!invalidEmail) {
            window.localStorage.setItem("email", emailInput.val());
            verificationForm.trigger("submit");
        } else {
            emailInput.trigger('blur');
        }
    });
})();