/**
 * Created by XIJ on 4/9/2016.
 */
function upload(){
    var th = document.form1;

    if(th.option.value == "none"){
        alert("Please select a type of the item!");
        return;
    }

    if(th.nameOfGoods.value==""){
        alert("Please Enter the Name of Item!");
        th.nameOfGoods.focus();
        return;
    }

    if(th.price.value==""){
        alert("Please Enter the price of Item!");
        th.price.focus();
        return;
    }

    if(th.description.value==""){
        alert("Please Enter the description of Item!");
        th.description.focus();
        return;
    }

    var filepath=th.image.value;
    filepath=filepath.substring(filepath.lastIndexOf('.')+1,filepath.length);

    if(th.image.value==""){
        alert("Please select image!");
        return;
    }

    if(filepath != 'jpg' && filepath != 'gif'){
        alert("Only image allowed");
        return;
    }

    alert("saving!!!!!!");
    th.action="/servlet/SaveItemAction";
    th.submit();
}

