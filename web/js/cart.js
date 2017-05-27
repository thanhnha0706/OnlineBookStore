/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    $.get("cart?action=get", function (data, status) {
//        console.log(data[0]);
        for (var i = 0; i < data.length; i++) {
            $('#cart').append('<tr bgcolor="#FFFFFF" class=" product-detail"><td valign="top"><img src="images/image07.jpg" /></td><td valign="top">' + data[i].book.title +'</td><td align="center" valign="top">' + data[i].book.price + '</td><td align="center" valign="top">1</td><td align="center" valign="top">' + data[i].book.price + '</td><td align="center" valign="top"><a href="#"><i class="icon-trash"></i></a></td></tr>');
        }
    });
});
