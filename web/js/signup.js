/**
 * Created by XIJ on 3/30/2016.
 */
function dosubmit() {
    var th = document.form1;
    var reMail =/^(?:[a-zA-Z0-9]+[_\-\+\.]?)*[a-zA-Z0-9]+@(?:([a-zA-Z0-9]+[_\-]?)*[a-zA-Z0-9]+\.)+([a-zA-Z]{2,})+$/;
    var s=new RegExp(reMail);
    if (!s.test(th.email.value)) {
        alert("Invalid Email");
        th.username.focus();
        return;
    }

    if (th.username.value == "") {
        th.username.focus();
        return;
    }

    if (th.password.value == "") {
        th.password.focus();
        return;
    }

    if (th.passwordAgain.value != th.password.value) {
        alert("The passwords should be the same!");
        th.passwordAgain.focus();
        return;
    }

    if (th.street.value == "") {
        th.street.focus();
        return;
    }

    if (th.city.value == "") {
        th.city.focus();
        return;
    }

    if (th.state.value == "") {
        th.state.focus();
        return;
    }

    if (th.zipcode.value == "") {
        th.zipcode.focus();
        return;
    }

    th.action="/servlet/RegisterAction"
    th.submit();
}
