package ntut.csie.accountManagementService.entity;

import com.google.common.eventbus.EventBus;
import ntut.csie.accountManagementService.entity.model.AggregateRoot;
import ntut.csie.accountManagementService.entity.model.DomainEvent;

import java.util.ArrayList;
import java.util.List;

public class DomainEventBus extends EventBus {
    public DomainEventBus() {
        super();
    }

    public void postAll(AggregateRoot aggregateRoot) {
        List<DomainEvent> domainEvents = new ArrayList<>(aggregateRoot.getDomainEvents());
        aggregateRoot.clearDomainEvents();

        for(DomainEvent domainEvent : domainEvents)
            post(domainEvent);

        domainEvents.clear();
    }

    public void post(DomainEvent domainEvent) {
        super.post(domainEvent);
    }


}
