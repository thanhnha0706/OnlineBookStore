/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {

    var shipping = 25000;
    var subTotal = 0;
    var grandTotal = 0;

    $.get("customers", function (data, status) {
        $("#cus-name").html("Full Name: " + data.customer.firstName + " " + data.customer.lastName);
        $("#cus-email").html("Emai: " + data.customer.email);
    });

    $.get("cart?action=get", function (data, status) {
        for (var i = 0; i < data.length; i++) {
            subTotal = subTotal + data[i].book.price;
            $("#t-head").after('<tr><td width="47%" align="left">' + data[i].book.title + '</td><td width="18%">' + data[i].book.price + '</td><td width="19%">1</td><td width="16%">' + data[i].book.price + '</td></tr>');
        }
        $("#shipping").html(shipping + " VND");
        $("#subtotal").html(subTotal + " VND");
        grandTotal = shipping + subTotal;
        $("#grandtotal").html(grandTotal + " VND");
        $("#payment").attr('href', 'https://www.baokim.vn/payment/product/version11?business=tranquy2512%40gmail.com&id=&order_description=Payment for cart&product_name=Cart number 152&product_price=' + grandTotal + '&product_quantity=1&total_amount=' + grandTotal + '&url_cancel=http://localhost:8080/onlinebookstore/success.zul&url_detail=das&url_success=ed');
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

