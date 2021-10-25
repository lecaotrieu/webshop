$('#form-checkout').submit(function (e) {
    e.preventDefault();
    showAlertBeforeCheckout(function () {
        checkout();
    });
});

function checkout() {
    var url = "/user/checkout";
    var cart = {id: $('#ip-cartId').val()}
    var data = {
        cart: cart,
        address: $('#ip-address').val(),
        phone: $('#ip-phone').val()
    }
    $.ajax({
        url: url,
        type: 'post',
        data: JSON.stringify(data),
        contentType: 'application/json',
        success: function (re) {
            showAlertAfterSuccess("Thanh toán thành công !", function () {
                window.location.href = "/";
            });
        },
        error: function (re) {
            showAlertAfterError("Thanh toán thất bại !");
        }
    });
}