$.ajax({
    url: "http://localhost:8080/tabs/" + localStorage.getItem("tabId"),
    method: "get",
    success: function (tab) {
        $("#tab-name").text(tab.tabName);
    }
})
$("#submit-new-task").click(function () {
    event.preventDefault();

    const currentTabId = localStorage.getItem("tabId")
    const taskTitle = $("#task-title-submit").val();
    const taskDescription =  $("#task-description-submit").val();
    const storyPoints = $("#task-story-points-submit").val();
    const dueDate = $("#task-deadline-submit").val();

    const task = {
        tabId: currentTabId,
        title: taskTitle,
        description: taskDescription,
        storyPoints: storyPoints,
        dueDate: dueDate
    };

    $.ajax({
        url: "http://localhost:8080/tasks",
        data: JSON.stringify(task),
        method: "post",
        contentType: "application/json",
        success: function () {
            window.location.href = "http://localhost:8080/client/tab.html#" + currentTabId;
        }

    })

});
