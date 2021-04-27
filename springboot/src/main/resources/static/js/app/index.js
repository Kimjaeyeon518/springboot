var main = {
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });
        $('#btn-update').on('click', function () {
            _this.update();
        });

        $('#btn-delete').on('click', function () {
            _this.delete();
        });

        $('#btn_insertComment').on('click', function () {
            _this.insertComment();
        });

        $('#btn-addProduct').on('click', function () {
            _this.addProduct();
        });

        $('#btn-deleteProduct').on('click', function () {
            _this.deleteProduct();
        });

        $('#productToList').on('click', function () {
            _this.productToList();
        });

        $('#btn_addCart').on('click', function() {
            _this.addCart();
        });

        $('#product_buy').on('click', function() {
            _this.product_buy();
        });

        $('#cart_buy').on('click', function() {
            _this.cart_buy();
        });
    },
    save : function () {
        if($('#title').val() == null || $('#title').val() =="") {
            alert("제목을 입력해주세요");
            return;
        }

        if($('#content').val() == null || $('#content').val() =="") {
            alert("내용을 입력해주세요");
            return;
        }

        var userId =$('#userId').val();
        var title =$('#title').val();
        var content =$('#content').val();

        var data = {
            userId : userId,
            title: title,
            content: content
        }

        $.ajax({
            type: 'POST',
            url: '/board',
            dataType: 'json',
            data: JSON.stringify(data),
            contentType:'application/json; charset=utf-8'
        }).done(function() {
            alert('글이 등록되었습니다.');
            window.location.href = '/boardList';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    update : function () {

        if($('#title').val() == null || $('#title').val() =="") {
            alert("제목을 입력해주세요");
            return;
        }

        if($('#content').val() == null || $('#content').val() =="") {
            alert("내용을 입력해주세요");
            return;
        }

        var boardId =$('#boardId').val();
        var title =$('#title').val();
        var content =$('#content').val();

        var data = {
            title: title,
            content: content
        }

        $.ajax({
            type: 'PUT',
            url: '/board/' + boardId,
            dataType: 'json',
            data: JSON.stringify(data),
            contentType:'application/json; charset=utf-8'
        }).done(function() {
            alert('글이 수정되었습니다.');
            window.location.href = '/boardList';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    delete : function () {
        var id = $('#boardId').val();

        $.ajax({
            type: 'DELETE',
            url: '/board/'+ id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8'
        }).done(function() {
            alert('글이 삭제되었습니다.');
            window.location.href = '/boardList';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    insertComment : function() {					// 댓글 생성 함수

        var userId = $('#commentUserId').val();
        var boardId = $('#boardId').val();
        var content = $('#comment_content').val();

        if($('#comment_content').val() == null || $('#comment_content').val() =="") {
            alert("내용을 입력해주세요");
            return;
        }

        var data = {
            userId: userId,
            boardId: boardId,
            content: content
        }

        $.ajax({
            type: 'POST',
            url: '/comment',
            data: JSON.stringify(data),
            dataType: 'json',
            contentType:'application/json; charset=utf-8'
        }).done(function() {
            window.location.href = '/board/' + boardId;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
	},

    addProduct : function () {
        if($('#name').val() == null || $('#name').val() =="") {
            alert("상품명을 입력해주세요");
            return;
        }

        if($('#price').val() == null || $('#price').val() =="") {
            alert("가격을 입력해주세요");
            return;
        }

        if($('#discount').val() == null || $('#discount').val() =="") {
            alert("할인율을 입력해주세요");
            return;
        }
        if($('#description').val() == null || $('#description').val() =="") {
            alert("상품 설명을 입력해주세요");
            return;
        }

        var name = $('#name').val();
        var category = $('#category').val();
        var description = $('#description').val();
        var price = $('#price').val();
        var discount = $('#discount').val();

        var data = {
            name: name,
            category: category,
            description: description,
            price: price,
            discount: discount
        }

        $.ajax({
            type: 'POST',
            url: '/product',
            data: JSON.stringify(data),
            dataType: 'json',
            contentType: 'application/json; charset=utf-8'
        }).done(function() {
            alert('상품이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    deleteProduct : function () {
        var productId = $('#productId').val();
        var category = $('#category').val();

        $.ajax({
            type: 'DELETE',
            url: '/product/'+ productId,
            dataType: 'json',
            contentType:'application/json; charset=utf-8'
        }).done(function() {
            alert('상품이 삭제되었습니다.');
            window.location.href = '/productList/?category=' + category;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    productToList : function () {
        var category = $('#category').val();
        window.location.href = '/productList/?category=' + category;
    },

    addCart : function () {

        var userId = $('#userId').val();
        var productId = $('#productId').val();

        var data = {
            userId: userId,
            productId: productId
        }

        $.ajax({
                    type: 'POST',
                    url: '/product/cart',
                    data: JSON.stringify(data),
                    dataType: 'json',
                    contentType:'application/json; charset=utf-8'
                }).done(function() {
                    alert("상품이 장바구니에 추가되었습니다!");
                }).fail(function (error) {
                    alert(JSON.stringify(error));
                });
    },

    product_buy : function () {
        var productId = $("#productId").val();

        window.location.href = '/product/buy/' + productId;
    },

    cart_buy : function () {
        window.location.href = '/cart/buy';
    }
};

main.init();

