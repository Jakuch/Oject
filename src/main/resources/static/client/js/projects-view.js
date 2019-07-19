function reloadView() {
    $.ajax({
        url: "http://localhost:8080/projects",
        method: "get",
        success: function (projects) {
            const $projectTemplate = $("#project-view-template");
            const $tBody = $("#projects-list tbody");
            $tBody.children("tr:not(#project-view-template)").remove();

            for (let i = 0; i < projects.length; i++) {
                const project = projects[i];
                const $trProject = $projectTemplate.clone();

                $trProject.removeAttr("id");
                $trProject.children(".disp-project-title").html('<h5>' + project.title + '</h5>');
                $trProject.children(".project-id-link").html('<a class="btn btn-primary" href="project.html#' + project.id + '">' + "Go to project" + '</a>');
                $trProject.children(".disp-project-description").text(project.description);

                $tBody.append($trProject);
            }

            $("#project-view-template").hide();
        }
    })
}

reloadView();
