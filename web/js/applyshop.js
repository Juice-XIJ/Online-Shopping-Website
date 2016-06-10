/**
 * Created by XIJ on 4/7/2016.
 */
function applyshop(){
    var th = document.form1;
    if(th.shopname.value==""){
        alert("Please enter your shop name £¡£¡");
        th.shopname.focus();
        return ;
    }
    th.action="/servlet/ApplyShopAction";
    th.submit();
}