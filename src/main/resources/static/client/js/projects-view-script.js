function reloadView(){
    $.ajax({
        url: "http://localhost:8080/projects",
        method: "get",
        success: function (projects) {
            const $projectTemplate =$("#project-view-template");
            const $tBody =$("body");

            $tBody.children("project:not(#project-view-template)").remove();

            for( let i= 0; i < projects.length; i++){
                const project = projects[i];
                const $project = $projectTemplate.clone();

                $project.removeAttr("id");
                $project.children(".displ-project-title").text(project.title);
                $project.children(".displ-project-description").text(project.description);
            }
        }
    })
}