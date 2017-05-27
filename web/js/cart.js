
$(document).ready(function () {
    $.get("cart?action=get", function (data, status) {

        // check whether data is empty
        if (!_.isEmpty(data)) {

            $cartHeaderButton = $("#cartHeaderButton");
            $cart = $("#cart");
            $cartGrandTotal = $("#cartGrandTotal");

            // group item by bookId
            var items = _.groupBy(data, "book.bookId");
            var itemCount = _.size(items);
            var total = _.sumBy(data, "book.price");

            // update the cart information in header
            // check whether it exists
            if ($cartHeaderButton.length) {
                $cartHeaderButton.html(itemCount + ' item(s) - ' + total + '₫<span class="caret"></span>');
            }

            // if in the cart page
            if ($cart.length) {

                // update the cart detail
                _.forEach(items, function (books, key) {

                    // these books are identical
                    var quantity = books.length;
                    var book = books[0].book;
                    var subTotal = book.price * quantity;
                    var deleteUrl = "cart?action=delete&bookId=" + book.bookId;
                    $cart.append('<tr bgcolor="#FFFFFF" class=" product-detail"><td valign="top"><img src="images/image07.jpg" /></td><td valign="top">' + book.title + '</td><td align="center" valign="top">' + book.price + '₫</td><td align="center" valign="top">' + quantity + '</td><td align="center" valign="top">' + subTotal + '₫</td><td align="center" valign="top"><a href="' + deleteUrl + '"><i class="icon-trash"></i></a></td></tr>');

                });

                // update the cart grand total
                $cartGrandTotal.html(total + "₫");
            }

        }

    });
});
