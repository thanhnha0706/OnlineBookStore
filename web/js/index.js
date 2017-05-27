
$(document).ready(function () {
    
    
    // check if user is logged in
   $.get("customer", function (data, status) {
       
       var customer = data.customer;
       // if the user is logged in
      if (!$.isEmptyObject(customer)) {
          // replace header part
          $("#welcomeMessage").html('Welcome! <a href="#welcome">' + customer.firstName + " " + customer.lastName + ' - </a> <a href="logout">Logout</a>');
      }
   });
    
});


