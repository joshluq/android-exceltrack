package pe.exceltransport.data.entity.mapper;

import pe.exceltransport.data.entity.SessionEntity;
import pe.exceltransport.domain.Session;

public class SessionEntityDataMapper {

    private SessionEntityDataMapper(){
        //empty Constructor
    }

    public static Session transform(SessionEntity entity) {
        if (entity == null) {
            return null;
        }
        Session session = new Session();
        session.setToken(entity.getToken());
        session.setUser(UserEntityDataMapper.transform(entity.getUserEntity()));
        return session;
    }

    public static SessionEntity transform(Session session) {
        if (session == null) {
            return null;
        }
        SessionEntity entity = new SessionEntity();
        entity.setToken(session.getToken());
        entity.setUserEntity(UserEntityDataMapper.transform(session.getUser()));
        return entity;
    }
}
