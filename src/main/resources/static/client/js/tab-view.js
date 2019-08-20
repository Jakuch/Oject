const tabId = window.location.hash.substr(1);

$("#back-tab-btn").on("click",function () {
    const projectId = localStorage.getItem("projectId");
    window.location.href = "/client/project.html#" + projectId;
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

            for (let i =0; i < tasks.length; i++){

                const task = tasks[i];
                const $tabTask = $taskTemplate.clone();
                $tabTask.find(".task-title").html(task.title);
                $tabTask.find(".task-description").html(task.description);
                $tabTask.find(".task-due-date").html(task.dueDate);
                $tabTask.find(".task-story-points").html(task.storyPoints);
                $tabTask.find(".task-btn-row").html(
                    '<a href="edit-task.html#' + task.id + '" id="btn-set-contributors" class="btn btn-primary col text-wrap" style="font-size: small">Task settings</a>' +
                    '<button title="Not implemented" id="btn-add-comment' + task.id + '" class="btn btn-primary col text-wrap" style="margin-left: 5px; font-size: small;">Add Comment</button>'
                );
                const taskContrib = task.contributors;

                for(let j = 0; j < taskContrib.length; j++){
                    const $userTmp = $("#contributor-tmp").clone();
                    const contributor = taskContrib[j];
                    $userTmp.html('<div>' + contributor.username + '</div>');
                    $tabTask.find("#task-contributors-list").append($userTmp.html());
                }
                $taskList.append($tabTask.html());
            }
        }
    })
}

$("#delete-tab-btn").click(function (){
    const projectId = localStorage.getItem("projectId");

    $.ajax({
        url: "http://localhost:8080/tabs/" + tabId,
        method: "delete",
        success: function(){
            window.location.href = "/client/project.html#" + projectId;
        }
    })
});


$("#edit-tab-btn").on("click",function () {
    $.ajax({
        url: "http://localhost:8080/tabs/" + tabId,
        method: "get",
        success: function(tab){
            $("#tab-title-form").show();
            $("#disp-tab-title-single").hide();
            $("#updated-tab-title").val(tab.tabName);
        }
    })
});

$("#btn-update-tab-title").on("click", function () {
    const projectId = localStorage.getItem("projectId");

    const updatedTabTitle = $("#updated-tab-title").val();
    const updatedTab = {
        tabName: updatedTabTitle,
        projectId: projectId
    };

   $.ajax({
       url: "http://localhost:8080/tabs/" + tabId,
       data: JSON.stringify(updatedTab),
       contentType: "application/json",
       method: "put",
       success: function () {
           $("#tab-title-form").hide();
           $("#disp-tab-title-single").show();
           location.reload();
       }
   })
});

