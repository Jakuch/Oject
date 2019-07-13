
$("#submit-new-task").click(function () {
    event.preventDefault();

    const taskTitle = $("#task-title-submit").val();
    const taskDescription =  $("#task-description-submit").val();

    const task = {
        //TODO sending the id of tab from tab.html to create-task.html
        title: taskTitle,
        description: taskDescription
    }

    $.ajax({
        url: "http://localhost:8080/tasks",
        data: JSON.stringify(task),
        method: "post",
        contentType: "application/json",
        success: function () {
            window.location.href = "http://localhost:8080/client/tab.html";
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