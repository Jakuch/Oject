const tabId = window.location.hash.substr(1);

$.ajax({
    url: "http://localhost:8080/tabs/" + tabId,
    method: "get",
    success: function (tab) {
        $("#task-template").hide();
        $("#task-contributor-tmp").hide();
        $("#disp-tab-title-single").text(tab.title);
        localStorage.setItem("tabId", tabId);
        reloadTasks()
    }
});

function reloadTasks(){
    $.ajax({
        url: "http://localhost:8080/tasks/tab/" + tabId,
        method: "get",
        success: function (tasks) {
            const $taskTemplate = $("#task-template");
            const $taskList = $("#tasks-list");
            const $taskContributorTmp = $("#task-contributor-tmp");

            for (let i =0; i < tasks.length; i++){

                const task = tasks[i];
                const $tabTask = $taskTemplate.clone();

                $tabTask.find(".task-title").html(task.title);
                $tabTask.find(".task-description").html(task.description);
                $tabTask.find(".task-due-date") //TODO
                $tabTask.find(".task-story-points").html(task.storyPoints);

                const taskContributors = task.contributors.length;

                for(let i = 0; i < taskContributors; i++){

                    const contributor = task.contributors[i];
                    const $taskContributor = $taskContributorTmp.clone();

                    $taskContributor.find(".currently-working-user").html(contributor.username)
                    $taskContributor.removeAttr(".currently-working-user");
                }

                $taskList.append($tabTask.html());
            }
        }
    })
}