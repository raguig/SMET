package com.aseds.SMET.common.context;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserContext {
    private Long userId;
    private String username;
    private String email;

    private static final ThreadLocal<UserContext> CONTEXT = new ThreadLocal<>();

    public static void setContext(UserContext context) {
        CONTEXT.set(context);
    }

    public static UserContext getContext() {
        return CONTEXT.get();
    }

    public static void clear() {
        CONTEXT.remove();
    }

    public static boolean isAuthenticated() {
        return CONTEXT.get() != null;
    }
}
