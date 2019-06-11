$("#create-card").click(function () {
    event.preventDefault();

    const tabTitle = $("#tab-title-submit");

    const projectTab = {
        title: tabTitle,
    }
    $.ajax({
        url: "http://localhost:8080/tabs",
        data: JSON.stringify(projectTab),
        method: "post",
        contentType: "application/json",
        success: function () {
            reloadView()
        }
    })
});

function reloadView() {
    $.ajax({
            url: "http://localhost:8080/tabs",
            method: "get",
            success: function (tabs) {
                const $tabTemplate = $("#tab-template").html();
                const $tabsList = $("#tab-list")

                for (let i = 0; i < tabs.length; i++) {
                    const tab = tabs[i];


                    $tabsList.append($tabTemplate);
                }
            }
        }
    )
}