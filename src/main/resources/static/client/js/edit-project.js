const projectId = window.location.hash.substr(1);

$("#update-project").click(function () {

    const projTitle = $("#input-project-title").val();
    const projDesc = $("#input-project-description").val();

    const updatedProject = {
        title: projTitle,
        description: projDesc
    };

    $.ajax({
        url: "http://localhost:8080/projects/" + projectId,
        method: "put",
        data: JSON.stringify(updatedProject),
        contentType: "application/json",
        success: function () {
            window.location.href = "/client/projects.html";
        }

    })
})