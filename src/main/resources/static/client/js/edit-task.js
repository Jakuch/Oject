const taskId = window.location.hash.substr(1);
localStorage.setItem("taskId", taskId);

$.ajax({
    url: "http://localhost:8080/tasks/" + taskId,
    method: "get",
    success: function (task) {
        const $contributorsList = $("#contributors-list");
        $("#user-tmp").hide();
        if (task.isFinished) {
            $("#task-status").addClass("bg-success");
        } else
            $("#task-status").addClass("bg-warning");

        $("#task-title-submit").val(task.title);
        $("#task-description-submit").val(task.description);
        $("#task-story-points-submit").val(task.storyPoints);
        $("#task-deadline-submit").val(task.dueDate);

        const projectId = localStorage.getItem("projectId");

        $.ajax({
            url: "http://localhost:8080/projects/" + projectId,
            method: "get",
            success: function (project) {
                const $userTmp = $("#user-tmp").clone();
                const projectContributors = project.contributors;

                for (let i=0; i < projectContributors.length; i++){
                    const contributor = projectContributors[i];
                    $userTmp.html(
                        '<input type="checkbox" value="' + contributor.id + '" id="contrib-name-id-' + contributor.id + '"><label for="contrib-name-id-' + contributor.id + '">' + contributor.username +'</label>'
                    );
                    $userTmp.removeAttr("#user-tmp");
                }

             $contributorsList.append($userTmp.html())
            }
        })
    }
});

$("#back-task-btn").on("click", function () {
    const tabId = localStorage.getItem("tabId");
    window.location.href = "/client/tab.html#" + tabId;
});

$("#remove-task-btn").on("submit", function () {
    const tabId = localStorage.getItem("tabId");
    $.ajax({
        url: "http://localhost:8080/tasks/" + taskId,
        method: "delete",
        success: function () {
            window.location.href = "/client/tab.html#" + tabId;
        }
    })
});

$("#status-task-btn").on("submit", function () {
   $.ajax({
       url: "http://localhost:8080/tasks/updateStatus" + taskId,
       method: "put",
       success: function () {
           location.reload();
       }
   })
});

$("#save-task-btn").on("submit", function () {
    event.preventDefault();
    const tabId = localStorage.getItem("tabId");

    const currentTabId = localStorage.getItem("tabId")
    const taskTitle = $("#task-title-submit").val();
    const taskDescription =  $("#task-description-submit").val();
    const storyPoints = $("#task-story-points-submit").val();
    const dueDate = $("#task-deadline-submit").val();
    var contributorsList = [];
    $("[id^='contrib-name-id-']").attr('checked', true).each(function () {
        contributorsList.push(this.val());
    });

    const task = {
        tabId: currentTabId,
        title: taskTitle,
        description: taskDescription,
        storyPoints: storyPoints,
        dueDate: dueDate,
        contributors: contributorsList
    };

    $.ajax({
        url: "http://localhost:8080/tasks/",
        method: "put",
        data: JSON.stringify(task),
        contentType: "application/json",
        success: function () {
            window.location.href = "/client/tab.html#" + tabId;
        }
    })
});
