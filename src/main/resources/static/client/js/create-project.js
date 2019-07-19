$(".list-group-item").mouseover(function () {
    $(".list-group-item").addClass(".active");
});

$.ajax({
    url: "http://localhost:8080/client/create-project.html",
    method: "get",
    success: function () {
        $.ajax({
            url: "http://localhost:8080/projects/" + localStorage.getItem("projectId"),
            method: "get",
            success: function (project) {
                $("#input-project-title").text(project.title);
                $("#input-project-description").text(project.description);
            }
        });
    }
})

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
            window.location.href = "/client/projects.html";
        }

    })
});