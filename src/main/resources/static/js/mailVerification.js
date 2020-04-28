(function() {
    const form = $("#emailForm");
    const email = localStorage.getItem("email");
    form.find("[name=email]").val(email);
})()