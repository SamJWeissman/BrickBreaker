package com.games.events;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by SJWeissman on 3/4/14.
 */

public final class EventAggregator
{
	private static final EventAggregator _eventAggregator = new EventAggregator();

	public static EventAggregator getEventAggregator()
	{
			return _eventAggregator;
	}

	private ArrayList<EventHandler> _eventHandlers;

	public void register(EventHandler eventHandler)
	{
		if(_eventHandlers == null) _eventHandlers = new ArrayList<EventHandler>();
		_eventHandlers.add(eventHandler);
	}

	public void publish(Event event)
	{
        for(Iterator<EventHandler> i = _eventHandlers.iterator(); i.hasNext(); )
        {
            EventHandler eventHandler = i.next();
            eventHandler.handleEvent(event);
        }
	}
}

