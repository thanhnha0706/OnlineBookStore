
$(document).ready(function () {

    $searchText = $(".searchText");
    $searchButton = $(".searchButton");
    $searchResults = $("#searchResults");
    $loader = $('<div class="loader"></div>');

    // search
    $searchButton.on("click", function (e) {

        // prevent default behaviour
        e.preventDefault();

        console.log("click");

        // get searchText
        var searchText = $(this).prev().val().trim();

        // if empty
        if (_.isEmpty(searchText)) {
            return alert("Please provide some keywords!");
        }

        // Everything's OK
        $.get("search?q=" + searchText, function (data, status) {

            if (data.error) {
                alert(data.error.message);
            }

            if (data.status === "OK") {
                window.location.href = "searchlist.zul";
            }
        });
    });

    // get search results
    if ($searchResults.length) {

        // show loading status
        $searchResults.html($loader);

        $.get("search", function (data, status) {
            
            console.log(data);

            // remove loader
            $loader.fadeOut(400, "swing", function() {
                // display results
                // empty results
                if (_.isEmpty(data)) {
                    $searchResults.append('<p style="text-align: center;">There are no books matching your search keywords!</p>');
                }
                // otherwise
                else {
                    _.forEach(data, function (value) {
                        var book = value.book;
                        var customer = book.customerId;
                        var category = book.categoryId;
                        $searchResults.append('<article class="item-holder"><div class="span2"><a href="book-detail.html"><img src="images/image09.jpg" alt="Image07" /></a> </div><div class="span10"><div class="title-bar"><a href="#detail">' + book.title + '</a><strong style="display:block; float:right;">sold by <a href="#seller">' + customer.firstName + " " + customer.lastName + '</a></strong> <span>by ' + book.author + '</span><span>in <b style="text-decoration: underline">' + category.name + '</b></span></div><strong>1 Reviews</strong><span class="rating-bar"><img alt="Rating Star" src="images/rating-star.png"></span><p>' + book.shortDescription + '</p><div class="cart-price"><a href="cart?action=add&bookId=' + book.bookId + '" class="cart-btn2">Add to Cart</a><span class="price">' + book.price + '₫</span></div></div></article>');
                    });
                }
            });
        });
    }
});


