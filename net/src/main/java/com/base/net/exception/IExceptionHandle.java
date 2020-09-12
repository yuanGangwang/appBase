package com.base.net.exception;

public interface IExceptionHandle{
    int UNAUTHORIZED = 401;
    int FORBIDDEN = 403;
    int NOT_FOUND = 404;
    int REQUEST_TIMEOUT = 408;
    int INTERNAL_SERVER_ERROR = 500;
    int SERVICE_UNAVAILABLE = 503;
    ResponseThrowable handleException(Throwable e);
}