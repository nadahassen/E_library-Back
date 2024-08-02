package tn.esprit.library.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.library.entities.*;
import tn.esprit.library.services.INotificationService;
import tn.esprit.library.services.IResourceService;
import tn.esprit.library.services.ISubjectService;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/resource")
public class ResourceController {
    @Autowired
    IResourceService resourceService;

    @Autowired
    private INotificationService notificationService;

    @Autowired
    private ISubjectService subjectService;

    @PostMapping(value = "/add/{ids}", consumes = { "multipart/form-data" })
    public ResponseEntity<?> createResource(
            @RequestPart("resource") String resourceJson,
            @RequestPart("imageFile") MultipartFile[] imageFiles,
            @RequestParam("userId") Long userId,
            @PathVariable("ids") Long id_subject) {

        ObjectMapper mapper = new ObjectMapper();
        Resource resource;
        try {
            resource = mapper.readValue(resourceJson, Resource.class);
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Invalid JSON data for resource.");
        }

        // Retrieve user by ID
        User user = resourceService.findUserById(userId);
        if (user == null) {
            return ResponseEntity.badRequest().body("Invalid user ID.");
        }

        // Set the uploader of the resource
        resource.setUpload(user);

        // Affectation subject resource (using the ids in the path)
        Subject subject = subjectService.retrieveSubject(id_subject);
        List<Resource> lr = subject.getResourceList();
        lr.add(resource);
        subject.setResourceList(lr);
        subjectService.modifySubject(subject);
        resource.setSubject(subject);

        Resource savedResource;
        try {
            savedResource = resourceService.addResourceWithImages(resource, imageFiles);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error saving files.");
        }

        // Check if the user is a student and create a notification
        if (user.getType() == Type.STUDENT) {
            Notification notification = new Notification();
            notification.setType("Resource Added");
            notification.setMessage("A new resource has been added.");
            notification.setDate(new Date());
            notification.setUser(user);

            notificationService.addNotification(notification);
        }

        return ResponseEntity.ok(savedResource);
    }

    @CrossOrigin
    @GetMapping("/getall")
    public List<Resource> retrieveAllResources() {
        return resourceService.retrieveAllResources();
    }

    @GetMapping("/getresource/{idr}")
    public Resource retrieveResource(@PathVariable("idr") Long id_resource) {
        return resourceService.retrieveResource(id_resource);
    }

    @PutMapping("/modify")
    public Resource modifyResource(@RequestBody Resource r) {
        return resourceService.modifyResource(r);
    }

    @DeleteMapping("/delete/{idr}")
    public void removeResource(@PathVariable("idr") Long id_resource) {
        resourceService.removeResource(id_resource);
    }
}
