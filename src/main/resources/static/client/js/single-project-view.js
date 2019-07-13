const id = window.location.hash.substr(1);

$.ajax({
    url: "http://localhost:8080/projects/" + id,
    method: "get",
    success: function (project) {
        $("#tab-template").hide();
        $("#disp-project-title-single").text(project.title);
        $("#disp-project-description-single").text(project.description);
        reloadTabs();
    }
});

function reloadTabs() {
    $.ajax({
            url: "http://localhost:8080/tabs/project/" + id,
            method: "get",
            success: function (tabs) {
                const $tabTemplate = $("#tab-template");
                const $tabsList = $("#tab-list");

                for (let i = 0; i < tabs.length; i++) {

                    const tab = tabs[i];
                    const $projectTab = $tabTemplate.clone();

                    $projectTab.find(".tab-title").html(tab.tabName);

                    $tabsList.append($projectTab.html());

                }
            }
        }
    )
}