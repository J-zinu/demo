package com.example.demo.config;

import org.springframework.stereotype.Component;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@WebListener
@Component
public class SessionConfig implements HttpSessionListener {

    private static final Map<String, HttpSession> sessions = new ConcurrentHashMap<>();

    // 중복로그인 지우기
    public synchronized static String getSessionidCheck(String type, String compareId, HttpSession currentSession) {
        String existingSessionId = null;

        for (Map.Entry<String, HttpSession> entry : sessions.entrySet()) {
            HttpSession hs = entry.getValue();

            if (hs != null && hs.getAttribute(type) != null && hs.getAttribute(type).toString().equals(compareId)) {
                if (!hs.getId().equals(currentSession.getId())) {  // 로그인 시도 중인 세션과 기존 로그인된 세션의 ID가 다를 경우
                    existingSessionId = hs.getId();
                    break;
                }
            }
        }

        if (existingSessionId != null) {
            removeSessionForDoubleLogin(existingSessionId);
        }

        return existingSessionId;
    }

    private static void removeSessionForDoubleLogin(String sessionId) {
        System.out.println("remove sessionId : " + sessionId);

        if (sessionId != null && !sessionId.isEmpty()) {
            sessions.get(sessionId).invalidate();
            sessions.remove(sessionId);
        }
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println(se);
        sessions.put(se.getSession().getId(), se.getSession());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        if (sessions.get(se.getSession().getId()) != null) {
            sessions.get(se.getSession().getId()).invalidate();
            sessions.remove(se.getSession().getId());
        }
    }
}
