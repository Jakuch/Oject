const projectId = window.location.hash.substr(1);


$.ajax({
    url: "http://localhost:8080/projects/" + projectId,
    method: "get",
    success: function (project) {
        $("#tab-template").hide();
        $("#disp-project-title-single").text(project.title);
        $("#disp-project-description-single").text(project.description);
        localStorage.setItem("projectId", projectId);
        reloadTabs();
    }
});

function reloadTabs() {
    $.ajax({
            url: "http://localhost:8080/tabs/project/" + projectId,
            method: "get",
            success: function (tabs) {
                const $tabTemplate = $("#tab-template");
                const $tabsList = $("#tab-list");

                for (let i = 0; i < tabs.length; i++) {

                    const tab = tabs[i];
                    const $projectTab = $tabTemplate.clone();

                    $projectTab.find(".tab-title").html(tab.tabName);
                    $projectTab.find(".tab-id-link").html('<a class="btn btn-primary" href="tab.html#' + tab.id + '">' + "Go to card" +'</a>');

                    const tasks = tab.task;

                    for(let i = 0; i < tasks.length; i++){

                        const task = tasks[i];
                        const $tabTaskTmp = $("#tab-task-tmp").clone();

                        $tabTaskTmp.find(".tab-task").html(task.title);
                        $tabTaskTmp.removeAttr("#tab-task-tmp");
                        $projectTab.find(".tab-view-tasks-list").append($tabTaskTmp.html());
                    }
                    $tabsList.append($projectTab.html());
                }
            }
        }
    )
}

$("#delete-project-btn").click(function () {
    $.ajax({
        url: "http://localhost:8080/projects/" + projectId,
        method: "delete",
        success: function(){
             window.location = "/client/projects.html";
        }
    })
});

$("#edit-project-btn").on("click",function () {
    window.location.href = "/client/edit-project.html#" + projectId;
});

$("#add-contributor-to-project-btn").on("submit", function () {
    const username = $("#username-to-add").val();

    $.ajax({
        url: "http://localhost:8080/projects/contributors/" + projectId,
        method: "post",
        data: JSON.stringify(username),
        contentType: "application/json",
        success: function () {
            location.reload();
        }
    })
});