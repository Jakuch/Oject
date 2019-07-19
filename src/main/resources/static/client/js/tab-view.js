const tabId = window.location.hash.substr(1);

$("#back-tab-btn").click(function () {

    const projectId = localStorage.getItem("projectId");

    $.ajax({
        url: "http://localhost:8080/projects/" + projectId,
        method: "get",
        success: function (project) {
            $("#tab-template").hide();
            $("#disp-project-title-single").text(project.title);
            $("#disp-project-description-single").text(project.description);
            window.location.href = "/client/project.html#" + projectId;
        }
    });
});

$.ajax({
    url: "http://localhost:8080/tabs/" + tabId,
    method: "get",
    success: function (tab) {
        $("#task-template").hide();
        $("#task-contributor-tmp").hide();
        $("#disp-tab-title-single").text(tab.tabName);
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
                $tabTask.find(".task-due-date").html(task.dueDate);
                $tabTask.find(".task-story-points").html(task.storyPoints);

                const taskContributors = task.contributors;

                for(let i = 0; i < taskContributors.length; i++){

                    const contributor = taskContributors[i];
                    const $taskContributor = $taskContributorTmp.clone();

                    $taskContributor.find(".currently-working-user").html(contributor.username)
                    $taskContributor.removeAttr(".currently-working-user");
                }

                $taskList.append($tabTask.html());
            }
        }
    })
}

$("#delete-tab-btn").click(function () {
    $.ajax({
        url: "http://localhost:8080/tabs/" + tabId,
        method: "delete",
        success: function(){
            window.location.href = "/client/project.html#" + projectId;
        }
    })
});

$("#edit-tab-btn").click(function () {
    $.ajax({
        url: "http://localhost:8080/tabs/" + tabId,
        method: "get",
        success: function () {
            $("#disp-tab-title-single").html(
                '<form><input type="text" value="' + tab.title + '"><button type="submit" name="Update" id="update-card"></button> </form>')
        //    TODO

        }
    })
})