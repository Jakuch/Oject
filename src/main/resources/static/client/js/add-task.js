
$("#submit-new-task").click(function () {
    event.preventDefault();

    const currentTabId = localStorage.getItem("tabId")
    const taskTitle = $("#task-title-submit").val();
    const taskDescription =  $("#task-description-submit").val();
    const storyPoints = $("#task-story-points-submit").val();

    const task = {
        tabId: currentTabId,
        title: taskTitle,
        description: taskDescription,
        storyPoints: storyPoints
    }

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


// $.ajax({
//     url: "http://localhost:8080/client/create-task.html",
//     method: "get",
//     success: function () {
//         $.ajax({
//             url: "http://localhost:8080/projects"
//         })
//     }
// })