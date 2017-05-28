
$(document).ready(function () {
    
    $welcomeMessage = $("#welcomeMessage");
    $searchText = $("#searchText");
    $searchButton = $("#searchButton");
    $searchResults = $("#searchResults");
    
    // check if user is logged in
   $.get("customer", function (data, status) {
       
       var customer = data.customer;
       // if the user is logged in
      if (!$.isEmptyObject(customer)) {
          // replace header part
          $welcomeMessage.html('Welcome! <a href="#welcome">' + customer.firstName + " " + customer.lastName + '</a>');
      }
   });
   
   // search
    $searchButton.on("click", function(e){
        
        // prevent default behaviour
        e.preventDefault();
        
        console.log("click");
        
        // get searchText
        var searchText = $searchText.val().trim();
                
        // if empty
        if (_.isEmpty(searchText)) {
            return alert("Please provide some keywords!");
        }
        
        // Everything's OK
        $.get("search?q=" + searchText, function(data, status) {
                       
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
        $.get("search", function (data, status) {
           _.forEach(data, function (value) {
               var book = value.book;
               $searchResults.append('<article class="item-holder"><div class="span2"><a href="book-detail.html"><img src="images/image09.jpg" alt="Image07" /></a> </div><div class="span10"><div class="title-bar"><a href="book-detail.html">'+book.title+'</a><strong style="display:block; float:right;">sold by <a href="#seller">Quy Vu</a></strong> <span>by '+book.author+'</span></div><strong>1 Reviews</strong><span class="rating-bar"><img alt="Rating Star" src="images/rating-star.png"></span><p>'+book.shortDescription+'</p><div class="cart-price"><a href="cart.html" class="cart-btn2">Add to Cart</a><span class="price">'+book.price+'₫</span></div></div></article>');
           });
        });
    }
});


