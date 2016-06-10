/**
 * Created by XIJ on 3/31/2016.
 */

function login(){
    var th = document.form2;
    if(th.email.value==""){
        alert("Email could not be EMPTY!!");
        th.email.focus();
        return ;
    }
    if(th.password.value==""){
        alert("Password could not be EMPTY!! ");
        th.password.focus();
        return ;
    }
    th.action="/servlet/LoginAction";
    th.submit();
}

function search1(){
    var th = document.form4;

    if (th.search.value == "") {
        alert("EMPTY!");
        return;
    }
    th.action = "/servlet/SearchAction";
    th.submit();


}


