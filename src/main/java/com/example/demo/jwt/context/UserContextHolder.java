package com.example.demo.jwt.context;

public class UserContextHolder {

    private static final ThreadLocal<UserContext> contextHolder = new ThreadLocal<>();

    public static void setUserContext(UserContext userContext) {
        contextHolder.set(userContext);
    }

    public static UserContext getUserContext() {
        UserContext context = contextHolder.get();
        if (context == null) {
            return new UserContext();
        }
        return context;
    }

    public static Long getUserId() {
        return getUserContext().getId();
    }

    public static String getUserEmail() {
        return getUserContext().getEmail();
    }

    public static String getFullName() {
        return getUserContext().getFullName();
    }

    public static void clear() {
        contextHolder.remove();
    }
}