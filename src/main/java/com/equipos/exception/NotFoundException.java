package com.equipos.exception;

import java.io.Serial;

public class NotFoundException extends ServiceException {

    @Serial
    private static final long serialVersionUID = -6278485788529989704L;

    /**
     * Creates a new instance of {@link NotFoundException}
     *
     * @param message the exception message
     */
    public NotFoundException(final String message) {
        super(message);
    }

    /**
     * Creates a new instance of {@link NotFoundException}
     *
     * @param message the exception message
     * @param cause   the exception cause
     */
    public NotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
