(function() {
    const form = $("#emailForm");
    form.find("[name=email]").val(localStorage.getItem("email"));
    form.find(".verification-submit").on('click', function (e) {
        e.preventDefault();
        const code = form.find("#verification-code").val();
        if(code === "") {
            form.find(".code-error").html("Code is required");
        } else {
            window.localStorage.removeItem("email");
            form.trigger("submit");
        }
    });
})()