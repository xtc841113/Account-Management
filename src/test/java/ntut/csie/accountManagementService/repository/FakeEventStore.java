package ntut.csie.accountManagementService.repository;

import ntut.csie.accountManagementService.entity.model.DomainEvent;
import ntut.csie.accountManagementService.entity.model.user.User;
import ntut.csie.accountManagementService.useCase.EventStore;
import ntut.csie.accountManagementService.useCase.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FakeEventStore implements EventStore {
    private List<DomainEvent> domainEvents;

    public FakeEventStore() {
        this.domainEvents = new ArrayList<>();
    }

    @Override
    public void save(DomainEvent event) {
        this.domainEvents.add(event);
    }

    @Override
    public List<DomainEvent> getByEventType(String eventType) {
        List<DomainEvent> _domainEvents = new ArrayList<>();
        for(DomainEvent domainEvent : domainEvents) {
            if(domainEvent.getClass().getSimpleName().equals(eventType)) {
                _domainEvents.add(domainEvent);
            }
        }
        return _domainEvents;
    }

    @Override
    public List<DomainEvent> getAllEvent() {
        return domainEvents;
    }
}
