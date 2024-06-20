package dev.yocca.fleeca.exceptions;

import java.sql.SQLException;

public class DAOException extends Exception {
    public DAOException() {
        super();
    }

    public DAOException(String message) {
        super(message);
    }

    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public DAOException(Throwable cause) {
        super(cause);
    }

    public DAOException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public String getViolatedConstraint() {
        if (!(getCause() instanceof SQLException)) {
            return null;
        }

        String constraintName = null;
        String errorMessage = getCause().getMessage();

        // Check for different constraint violation patterns in the error message
        if (errorMessage.contains("violates unique constraint")) {
            constraintName = errorMessage.split("violates unique constraint \"")[1].split("\"")[0];
        } else if (errorMessage.contains("violates foreign key constraint")) {
            constraintName = errorMessage.split("violates foreign key constraint \"")[1].split("\"")[0];
        } else if (errorMessage.contains("violates check constraint")) {
            constraintName = errorMessage.split("violates check constraint \"")[1].split("\"")[0];
        } else if (errorMessage.contains("null value in column")) {
            constraintName = "NOT NULL constraint";
        }

        return constraintName;
    }
}
