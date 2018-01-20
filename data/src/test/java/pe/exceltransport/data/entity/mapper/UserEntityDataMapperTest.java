package pe.exceltransport.data.entity.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import pe.exceltransport.data.entity.UserEntity;

import pe.exceltransport.domain.User;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class UserEntityDataMapperTest {

    private static final String FAKE_NAME = "name";
    private static final String FAKE_LAST_NAME = "last name";
    private static final String FAKE_EMAIL = "email@test.com";

    @Test
    public void testTransformUserEntity() {
        UserEntity userEntity = buildUserEntity();
        User user = UserEntityDataMapper.transform(userEntity);

        assertThat(user, is(instanceOf(User.class)));
        assertThat(user.getName(), is(FAKE_NAME));
        assertThat(user.getLastName(), is(FAKE_LAST_NAME));
        assertThat(user.getEmail(), is(FAKE_EMAIL));
    }

    @Test
    public void testTransformUserEntityNull() {
        UserEntityDataMapper.transform(null);
    }

    private UserEntity buildUserEntity(){
        UserEntity entity =  new UserEntity();
        entity.setEmail(FAKE_EMAIL);
        entity.setName(FAKE_NAME);
        entity.setLastName(FAKE_LAST_NAME);
        return  entity;
    }
}
