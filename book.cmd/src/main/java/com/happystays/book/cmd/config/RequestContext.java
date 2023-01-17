package com.happystays.book.cmd.config;

public class RequestContext {

    private static final ThreadLocal<RequestContext> CONTEXT = new ThreadLocal<>();
    private String token;

    public static RequestContext getContext() {
        RequestContext result = CONTEXT.get();
        if (result == null) {
            result = new RequestContext();
            CONTEXT.set(result);
        }
        return result;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void unload() { CONTEXT.remove(); }

}
