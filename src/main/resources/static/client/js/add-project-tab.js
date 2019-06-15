$("#create-card").click(function () {
    event.preventDefault();

    const tabName = $("#tab-title-submit").val();
    const projectId = window.location.hash.substr(1);

    const projectTab = {
        tabName: tabName,
        projectId: projectId
    };

    $.ajax({
        url: "http://localhost:8080/tabs",
        data: JSON.stringify(projectTab),
        method: "post",
        contentType: "application/json",
        success: function () {
            location.reload()
            // window.location.href = "/client/project.html#" + projectId;
        }
    })
});
