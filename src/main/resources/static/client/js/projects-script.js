$(".list-group-item").mouseover(function () {
    $(".list-group-item").addClass(".active");
});


$("#create-project").click(function () {
    event.preventDefault();

    const title = $("#input-project-title").val();
    const description = $("#input-project-description").val();

    const newProject = {
        title: title,
        description: description,
    };


    $.ajax({
        url: "http://localhost:8080/projects",
        method: "post",
        data: JSON.stringify(newProject),
        contentType: "application/json",
        success: function () {
            window.location.href = "/client/projectView.html";
        }

    })
});