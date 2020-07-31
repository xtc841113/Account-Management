package ntut.csie.accountManagementService.entity.model;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class AggregateRoot {
    private final List<DomainEvent> domainEvents;

    public AggregateRoot() {
        this.domainEvents = new CopyOnWriteArrayList<>();
    }

    public void addDomainEvent(DomainEvent domainEvent) {
        domainEvents.add(domainEvent);
    }

    public List<DomainEvent> getDomainEvents() {
        return Collections.unmodifiableList(domainEvents);
    }

    public void clearDomainEvents(){
        domainEvents.clear();
    }

}
