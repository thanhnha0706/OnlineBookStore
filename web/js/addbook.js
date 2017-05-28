
$(document).ready(function () {

    $inputCategory = $("#inputCategory");

    // check if user is logged in
    $.get("category", function (data, status) {
        
        _.forEach(data, function(value) {
            var category = value.category;
            $inputCategory.append('<option value="' + category.categoryId + '">' + category.name + '</option>');
        })
    });
});


