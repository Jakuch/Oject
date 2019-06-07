const id = window.location.hash.substr(1);
console.log(id);

$.ajax({
    url: "http://localhost:8080/projects/" + id,
    method: "get",
    success: function (project) {
        $("#tab-template").hide();
        $("#disp-project-title-single").text(project.title);
        $("#disp-project-description-single").text(project.description);
    }
});