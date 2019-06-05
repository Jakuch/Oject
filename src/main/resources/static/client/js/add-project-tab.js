
$("#create-card").click(function () {
    $.ajax({
        url: "http://localhost:8080/cards",
        method: "get",
        success: function () {
           const $tabTemplate = $("#tab-template").html();
            $("#tab-template").hide();

            const $cardsList = $("#tab-list")

            $cardsList.append($tabTemplate);
        }
    })
});

// function reload() {
//     $.ajax({
//         url: "http://localhost:8080/cards",
//         method: "get",
//         success: function (cards) {
//             const $cardTemplate = $("#card-template");
//             const $tCard = $("#cards-list div");
//             $tCard.children("div:not(#card-template)").remove();
//
//             for (let i = 0; i < cards.length; i++) {
//                 const card = cards[i];
//                 const $cardHtmlElement = $cardTemplate.clone();
//
//                 $tCard.append($cardHtmlElement);
//             }
//         }
//     });
// }
//
// reload();