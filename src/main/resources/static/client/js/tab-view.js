const tabId = window.location.hash.substr(1);

$.ajax({
    url: "http://localhost:8080/tabs/" + tabId,
    method: "get",
    success: function (tab) {
        $("#task-template").hide();
        $("#disp-tab-title-single").text(tab.title);
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

                $taskList.append($tabTask.html());
            }
        }
    })
}