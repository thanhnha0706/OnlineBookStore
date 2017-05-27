
$(document).ready(function () {

    var shipping = 25000;
    var subTotal = 0;
    var grandTotal = 0;

    // fill out the shipping information
    $.get("customer", function (data, status) {
        var customer = data.customer;
        $("#customerName").attr("value", customer.firstName + " " + customer.lastName);
        $("#customerEmail").attr("value", customer.email);
        $("#inputAddress").attr("value", customer.address);
        $("#inputTelephone").attr("value", customer.phone);
    });

    // Cart overview
    $.get("cart?action=get", function (data, status) {

        // check whether data is empty
        if (!_.isEmpty(data)) {

            $checkoutOverview = $("#checkoutOverview");

            // group item by bookId
            var items = _.groupBy(data, "book.bookId");
            var itemCount = _.size(items);
            var total = _.sumBy(data, "book.price");

            // if in the checkout page
            if ($checkoutOverview.length) {

                _.forEach(items, function (books, key) {
                    // these books are identical
                    var quantity = books.length;
                    var book = books[0].book;
                    var subTotal = book.price * quantity;
                    $checkoutOverview.after('<tr><td width="47%" align="left">' + book.title + '</td><td width="18%">' + book.price + '₫</td><td width="19%">' + quantity + '</td><td width="16%">' + subTotal + '₫</td></tr>');
                })

                $("#shipping").html(shipping + "₫");
                $("#subtotal").html(total + "₫");
                grandTotal = shipping + total;
                $("#grandtotal").html(grandTotal + "₫");
                $("#payment").attr('href', 'https://www.baokim.vn/payment/product/version11?business=tranquy2512%40gmail.com&id=&order_description=Payment for cart&product_name=Cart number 152&product_price=' + grandTotal + '&product_quantity=1&total_amount=' + grandTotal + '&url_cancel=http://localhost:8080/onlinebookstore/success.zul&url_detail=das&url_success=ed');
            }
        }
    });

    $(".sent-otp").click(function (event) {
        event.preventDefault();
        sentOtp();
    });

    function sentOtp() {
        $.get("otp", function (data, status) {
            console.log(data);
        });
    }

    function submitOtp() {
        $.post("otp", {
            total: grandTotal,
            otp: $("#otpCode").val(),
            inputAddress: $("#inputAddress").val(),
            inputTelephone: $("#inputTelephone").val()}
        ).done(function (data) {
            window.location = data;
        });

    }

    $("#verify").click(function (event) {
        event.preventDefault();
        submitOtp();
    });




});

