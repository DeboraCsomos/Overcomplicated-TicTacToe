// $() shorthand for $(document).ready()
$(function () {
    funfactButton();
    comicEvent();
});

function funfactButton() {
    $("#funFactButton").on("click", function () {
        var funfactElement = $(".funFact");
        $.ajax({
            type: "GET",
            url: "/new_fun_fact",
            dataType: "json",
            contentType: "application/json",
            success: function (response) {
                funfactElement.html(response);
            },
            error: function (response) {
            }
        });
    })
}


function comicEvent() {
    $("#comic").on("click", function () {
        $.ajax({
            type: "GET",
            url: "/new_comic",
            dataType: "json",
            contentType: "application/json",
            success: function (response) {
                var img = $("#comic");
                img.attr("src", response.uri);
                img.attr("alt", response.alt);
                img.attr("title", response.title);
            },
            error: function (response) {
            }
        });
    })
}