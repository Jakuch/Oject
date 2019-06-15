const id = window.location.hash.substr(1);

$.ajax({
    url: "http://localhost:8080/projects/" + id,
    method: "get",
    success: function (project) {
        $("#tab-template").hide();
        $("#disp-project-title-single").text(project.title);
        $("#disp-project-description-single").text(project.description);
        reloadTabs(project);
    }
});

function reloadTabs(project) {
    $.ajax({
            url: "http://localhost:8080/tabs",
            method: "get",
            success: function (tabs) {
                const $tabTemplate = $("#tab-template");
                const $tabsList = $("#tab-list")

                for (let i = 0; i < tabs.length; i++) {
                    if (tabs.projectId == project.id) {

                        const tab = tabs[i];
                        const $projectTab = $tabTemplate.clone();

                        $projectTab.find(".tab-title").html(tab.tabName);
                        $tabsList.append($projectTab.html());
                    }
                }
            }
        }
    )
}