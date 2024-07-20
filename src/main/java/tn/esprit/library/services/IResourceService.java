package tn.esprit.library.services;

import tn.esprit.library.entities.Document;
import tn.esprit.library.entities.Resource;
import tn.esprit.library.entities.Subject;

import java.util.List;
import java.util.Optional;

public interface IResourceService {
    public List<Resource> retrieveAllResources();

    public Optional<Resource> retrieveResource(Long id_resource);

    public Resource addResource(Resource r);

    public void removeResource(Long id_resource);

    public Resource modifyResource(Resource r);

    public Resource affecterDocumentaResource(Resource r, Long id_document);

    public Resource affecterSubjectResource(Resource r, Long id_sujet);
}
