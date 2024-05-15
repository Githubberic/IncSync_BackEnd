package com.whiteboard.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.whiteboard.entity.Project;
import com.whiteboard.entity.Stroke;
import com.whiteboard.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@CrossOrigin
@RestController
@RequestMapping("/whiteboard")
public class WhiteboardController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/message")

    public String test() {

        return "Hello from the whiteboard service!";

    }

    //Create whiteboard
    @PostMapping("/create")
    public String saveProject(@RequestBody Project project) throws ExecutionException, InterruptedException {
        return projectService.saveProject(project);
    }

    //Get whiteboard
    @GetMapping("/get/{id}")
    public ResponseEntity<Project> getProject(@PathVariable(value = "id") String id) throws ExecutionException, InterruptedException {
        Project project = projectService.getProject(id);

        if (project != null) {
            return ResponseEntity.ok(project); // Return entity with status code 200 (OK)
        } else {
            return ResponseEntity.notFound().build(); // Return 404 (Not Found)
        }
    }

    //Get whiteboard
    @PutMapping("/updateContent/{id}")
    public List<Stroke> updateProjectContent(@PathVariable(value = "id") String id, @RequestBody Project project) throws ExecutionException, InterruptedException, JsonProcessingException {

        return projectService.updateProjectContent(id, project);
    }

    //Get whiteboard

    //Edit whiteboard

    //Delete whiteboard

    //Export whiteboard


}
