package tn.esprit.library.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.library.entities.Document;
import tn.esprit.library.entities.Resource;
import tn.esprit.library.repository.IResourceRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ResourceServiceImpl implements IResourceService {

    @Autowired
    IResourceRepository resourceRepository;
    @Override
    public List<Resource> retrieveAllResources() {
        return resourceRepository.findAll();
    }

    @Override
    public Optional<Resource> retrieveResource(Long id_resource) {
        return resourceRepository.findById(id_resource);
    }

    @Override
    public Resource addResource(Resource r) {
        return resourceRepository.save(r);
    }

    @Override
    public void removeResource(Long id_resource) {
        resourceRepository.deleteById(id_resource);

    }

    @Override
    public Resource modifyResource(Resource r) {
        return resourceRepository.save(r);
    }

    @Override
    public Resource affecterDocumentaResource(Resource r, Document d) {
        List<Document> x;
        x = r.getDocuments();
        x.add(d);
        r.setDocuments(x);
        return r ;
    }
}
