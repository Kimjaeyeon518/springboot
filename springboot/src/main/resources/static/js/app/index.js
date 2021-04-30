var main = {

    init : function () {
        var _this = this;



        $('#productToList').on('click', function () {
            _this.productToList();
        });



        $('#cart_buy').on('click', function() {
            _this.cart_buy();
        });
    },

    productToList : function () {
        var category = $('#category').val();
        window.location.href = '/products/?category=' + category;
    },

    cart_buy : function () {
        var userId = $('#userId').val();
        window.location.href = '/cart/buy/' + userId;
    }
};

main.init();

