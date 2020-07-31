package ntut.csie.accountManagementService.useCase;


import ntut.csie.accountManagementService.entity.model.DomainEvent;

import java.util.List;

public interface EventStore {
	public void save(DomainEvent event);
	
	public List<DomainEvent> getByEventType(String eventType);
	
	public List<DomainEvent> getAllEvent();
}
