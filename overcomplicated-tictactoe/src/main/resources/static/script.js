// $() shorthand for $(document).ready()
$(function () {
    funfactButton();
});

function funfactButton() {
    $("#funFactButton").on("click", function () {
        var funfactElement = $(".funFact");
        $.ajax({
            type: "GET",
            url: "/new_fun_fact",
            dataType: "json",
            contentType:  "application/json",
            success: function (response) {
                funfactElement.html(response);
            },
            error: function (response) {
            }
        });
    })
}