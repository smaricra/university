
function integerValidation() {
    var e = event || window.event;  // get event object
    var key = e.keyCode || e.which; // get key cross-browser

    if ((key < 48 || key > 57) && key!=8 && key!=46) { //if it is not a number ascii code
        //Prevent default action, which is inserting character
        if (e.preventDefault) e.preventDefault(); //normal browsers
            e.returnValue = false; //IE
    }
}