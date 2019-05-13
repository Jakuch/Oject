const id = window.location.hash.substr(1);
$.ajax({
    url: "http://localhost:8080/projects/" + id,
    method: "get",
    success: function (project) {
        $("#disp-project-title-single").text(project.title);
        $("#disp-project-description-single").text(project.description);
    }
});