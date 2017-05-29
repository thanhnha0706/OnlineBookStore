
$(document).ready(function () {

    $inputCategory = $("#inputCategory");

    $.get("category", function (data, status) {
        
        _.forEach(data, function(value) {
            var category = value.category;
            $inputCategory.append('<option value="' + category.categoryId + '">' + category.name + '</option>');
        });
    });
});


