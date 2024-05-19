package org.example.librarymanagement.business.concretes;

import org.example.librarymanagement.business.abstracts.PublisherService;
import org.example.librarymanagement.core.exception.NotFoundException;
import org.example.librarymanagement.core.utils.Msg;
import org.example.librarymanagement.dao.PublisherRepo;
import org.example.librarymanagement.entities.Publisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class PublisherManager implements PublisherService {

    private final PublisherRepo publisherRepo;

    public PublisherManager(PublisherRepo publisherRepo) {
        this.publisherRepo = publisherRepo;
    }

    @Override
    public Publisher save(Publisher publisher) {
        return publisherRepo.save(publisher);
    }

    @Override
    public Publisher update(Publisher publisher) {
        get(publisher.getId());
        return publisherRepo.save(publisher);
    }

    @Override
    public Publisher get(long id) {
        return publisherRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Page<Publisher> cursor(int page, int pageSize) {
        return publisherRepo.findAll(PageRequest.of(page,pageSize));
    }

    @Override
    public boolean delete(long id) {
        publisherRepo.delete(get(id));
        return true;
    }
}
