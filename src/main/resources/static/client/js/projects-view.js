function reloadView(){
    $.ajax({
        url: "http://localhost:8080/projects",
        method: "get",
        success: function (projects) {
            const $projectTemplate =$("#project-view-template");
            const $tBody =$("#projects-list tbody");

            $tBody.children("div:not(#project-view-template)").remove();

            for( let i= 0; i < projects.length; i++){
                const project = projects[i];
                const $project = $projectTemplate.clone();

                $project.removeAttr("id");
                $project.children(".project-id-link").html('<a href="project.html#"' + project.id + '></a>');
                $project.children(".displ-project-title").text(project.title);
                $project.children(".displ-project-description").text(project.description);
            }
        }
    })
}

reloadView();