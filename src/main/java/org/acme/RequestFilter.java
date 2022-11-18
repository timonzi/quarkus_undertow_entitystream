package org.acme;

import io.quarkus.arc.Priority;
import java.io.IOException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;
import org.apache.commons.io.IOUtils;

@Priority(Priorities.AUTHORIZATION)
@Provider
public class RequestFilter implements ContainerRequestFilter {


    @Override
    public void filter(final ContainerRequestContext requestContext) {
        try {
            final var result = IOUtils.toString(requestContext.getEntityStream());

            if (result == null || result.isEmpty()) {
                throw new RuntimeException("no body found");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}