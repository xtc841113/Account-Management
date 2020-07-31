package ntut.csie.accountManagementService.useCase.handler;

import com.google.common.eventbus.Subscribe;
import ntut.csie.accountManagementService.entity.model.DomainEvent;
import ntut.csie.accountManagementService.useCase.EventStore;

public class EventStoreHandler {
    private EventStore eventStore;
    public EventStoreHandler(EventStore eventStore) {
        this.eventStore = eventStore;
    }

    @Subscribe
    public void handleEvent(DomainEvent domainEvent) {
        eventStore.save(domainEvent);
    }
}