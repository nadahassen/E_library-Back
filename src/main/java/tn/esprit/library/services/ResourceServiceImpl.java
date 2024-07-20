package tn.esprit.library.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.library.entities.Document;
import tn.esprit.library.entities.Resource;
import tn.esprit.library.entities.Subject;
import tn.esprit.library.repository.IDocumentRepository;
import tn.esprit.library.repository.IResourceRepository;
import tn.esprit.library.repository.ISubjectRepository;

import javax.print.Doc;
import java.util.List;
import java.util.Optional;

@Service
public class ResourceServiceImpl implements IResourceService {

    @Autowired
    IResourceRepository resourceRepository;
    @Autowired
    IDocumentRepository documentRepository;
    
    @Autowired
    ISubjectRepository subjectRepository;
    @Override
    public List<Resource> retrieveAllResources() {
        return resourceRepository.findAll();
    }

    @Override
    public Resource retrieveResource(Long id_resource) {
        return resourceRepository.findById(id_resource).get();
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
    public Resource affecterDocumentaResource(Resource r, Long id_document) {
        Document docu = new Document();
        Optional<Document> doc = documentRepository.findById(id_document);
        if (doc.isPresent()){
            docu = doc.get();
        }
        List<Document> x;
        x = r.getDocuments();
        x.add(docu);
        r.setDocuments(x);
        return r ;
    }

    @Override
    public Resource affecterSubjectResource(Resource r, Long id_subject) {
        Subject suj = new Subject();
        Optional<Subject> sub = subjectRepository.findById(id_subject);
        if(sub.isPresent()){
            suj = sub.get();

        }
        r.setSubject(suj);
        return r;
    }
}
