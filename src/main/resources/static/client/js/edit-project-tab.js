$("#update-card").click(function () {
    event.preventDefault();

    const tabName = $("#tab-title-submit").val();
    const projectId = window.location.hash.substr(1);

    const updatedProjectTab = {
        tabName: tabName,
        projectId: projectId
    };

    $.ajax({
        url: "http://localhost:8080/tabs",
        data: JSON.stringify(updatedProjectTab),
        method: "put",
        contentType: "application/json",
        success: function () {
            location.reload();
        }
    })
});
