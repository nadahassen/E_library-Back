package tn.esprit.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.library.entities.Resource;
import tn.esprit.library.services.IResourceService;

import java.util.List;

@RestController
@RequestMapping("/resource")
public class ResourceController {
    @Autowired
    IResourceService resourceService;


    @PostMapping("/add")
    public Resource addResource(@RequestBody Resource r){return resourceService.addResource(r); }

    @GetMapping("/getall")
    public List<Resource> retrieveallResources(){return resourceService.retrieveAllResources(); }

    @GetMapping("/getresource/{idr}")
    public Resource retrieveResourse(@PathVariable("idr")Long id_resource){return resourceService.retrieveResource(id_resource); }

    @PutMapping("/modify")
    public  Resource modifyResource(@RequestBody Resource r){return resourceService.modifyResource(r); }


    @DeleteMapping("/delete/{idr}")
    public  void removeResource(@PathVariable("idr")Long id_resource){ resourceService.removeResource(id_resource); }

    @PutMapping("/affectresourcedocument/{idd}")
    public  Resource affectResourceDocument(@RequestBody Resource r,@PathVariable("idd")Long id_document){
        return resourceService.affecterDocumentaResource(r,id_document);}

    @PutMapping("/affectresourcedocument/{ids}")
    public  Resource affectsubjectresource(@RequestBody Resource r,@PathVariable("ids")Long id_subject){
        return resourceService.affecterSubjectResource(r,id_subject);}


}
