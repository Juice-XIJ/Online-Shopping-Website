/**
 * Created by XIJ on 4/15/2016.
 */
function amount() {
    var th = document.form1;

    if (th.amount.value==null || isNaN( th.amount.value ))
    {
        alert("Please enter a number!");
        return;
    }

    if (th.amount.value<=0 || th.amount.value % 1 != 0)
    {
        alert("The amount must be larger than 0 and an integer!!");
        return;
    }

    alert("Processing!");
    th.action="/servlet/BuyAction";
    th.submit();
}
