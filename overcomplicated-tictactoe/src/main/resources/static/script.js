// $() shorthand for $(document).ready()
$(function () {
    funfactButton();
    comicEvent();
    squareClickEvent();
});

function funfactButton() {
    $("#funFactButton").on("click", function () {
        let funfactElement = $(".funFact");
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
                let img = $("#comic");
                img.attr("src", response.uri);
                img.attr("alt", response.alt);
                img.attr("title", response.title);
            },
            error: function (response) {
                console.log(response);
            }
        });
    })
}

function squareClickEvent() {
    $(".square").on("click", function () {
        event.preventDefault();
        let position = $(this).attr("id");
        $.ajax({
            type: "GET",
            url: `/game-move?move=${position}`,
            dataType: "json",
            contentType: "application/json",
            success: function (response) {
                let board = JSON.parse(response["board"]);
                console.log(board);
                let squares = $(".square");

                squares.each(function (index) {
                    if (board[index] === "O") {
                        $(this).addClass("fa fa-circle-o inactive");
                    } else if (board[index] === "X") {
                        $(this).addClass("fa fa-times inactive");
                    }
                });

                if (response["winner"] != null || response["finished"]) {
                    squares.each(function() {
                        $(this).addClass("inactive");
                    });
                    let modal = $("#finish-modal");
                    let messageDiv = modal.find("#message");
                    if (response["winner"] != null) {
                        messageDiv.text(`The winner is ${response["winner"]}!`);
                    } else if (response["finished"]) {
                        messageDiv.text("It's a draw!");
                    }
                    modal.modal("show");

                }
            },
            error: function (response) {
                console.log(response);
            }
        });
    })
}