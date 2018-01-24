package pe.exceltransport.data.entity.mapper;

import pe.exceltransport.data.entity.UserEntity;
import pe.exceltransport.domain.User;

class UserEntityDataMapper {

    private UserEntityDataMapper(){
        //empty constructor
    }

    static User transform(UserEntity entity) {
        if (entity == null) {
            return null;
        }
        User user = new User();
        user.setId(entity.getId());
        user.setEmail(entity.getEmail());
        user.setName(entity.getName());
        user.setLastName(entity.getLastName());
        return user;
    }

    static UserEntity transform(User user){
        if (user == null) {
            return null;
        }
        UserEntity entity = new UserEntity();
        entity.setId(user.getId());
        entity.setName(user.getName());
        entity.setLastName(user.getLastName());
        entity.setEmail(user.getEmail());
        return entity;
    }

}
