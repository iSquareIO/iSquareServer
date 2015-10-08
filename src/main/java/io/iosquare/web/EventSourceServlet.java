package io.iosquare.web;

import org.eclipse.jetty.servlets.EventSource;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by mva on 08/10/15.
 */
public class EventSourceServlet extends org.eclipse.jetty.servlets.EventSourceServlet {
    @Override
    protected EventSource newEventSource(HttpServletRequest request) {
        return new DataSource();
    }
}
