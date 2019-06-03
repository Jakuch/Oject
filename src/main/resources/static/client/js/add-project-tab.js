
$("#create-card").click(function () {
    $.ajax({
        url: "http://localhost:8080/cards",
        method: "get",
        success: function () {
            const $cardTemplate = $("#card-template");
            const $tCard = $("#cards-list");

            // $tCard.children("div:not(#card-template)").remove();

            const $cardHtmlElement = $cardTemplate.clone();

            // const $cardBody = $cardHtmlElement.children(".card-body");
            // const $cardTask = $cardBody.children(".col");
            //
            // $cardHtmlElement.removeAttr("id");
            // $cardBody.children(".card-title");
            // $cardTask.children( "p .card-header");
            //
            // $cardBody.append($cardTask);
            // $cardHtmlElement.append($cardBody);
            // $tCard.append($cardHtmlElement);
        }
    })

    // $.ajax({
    //     url: "http://localhost:8080/cards",
    //     method: "post",
    //     success: function () {
    //
    //     }
    // })
});

function reload() {
    $.ajax({
        url: "http://localhost:8080/cards",
        method: "get",
        success: function (cards) {
            const $cardTemplate = $("#card-template");
            const $tCard = $("#cards-list div");
            $tCard.children("div:not(#card-template)").remove();

            for (let i = 0; i < cards.length; i++) {
                const card = cards[i];
                const $cardHtmlElement = $cardTemplate.clone();

                $tCard.append($cardHtmlElement);
            }
        }
    });
}

reload();