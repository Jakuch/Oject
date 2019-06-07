
$("#create-card").click(function () {
    event.preventDefault();

    $.ajax({
        url: "http://localhost:8080/tabs",
        method: "post",
        success: function (tabs) {
           const $tabTemplate = $("#tab-template").html();
            const $tabsList = $("#tab-list")
//TODO
            $tabsList.append($tabTemplate);
        }
    })
});
