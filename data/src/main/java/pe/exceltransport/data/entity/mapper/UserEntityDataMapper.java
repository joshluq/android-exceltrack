package pe.exceltransport.data.entity.mapper;

import pe.exceltransport.data.entity.UserEntity;
import pe.exceltransport.domain.User;

public class UserEntityDataMapper {

    public static User transform(UserEntity entity) {
        if (entity != null) {
            User user = new User();
            user.setEmail(entity.getEmail());
            user.setName(entity.getName());
            user.setLastName(entity.getLastName());
            return user;
        }
        return null;
    }

}
