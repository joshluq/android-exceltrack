package pe.exceltransport.data.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import io.reactivex.Observable;
import pe.exceltransport.data.entity.UserEntity;
import pe.exceltransport.data.network.RestApi;
import pe.exceltransport.data.network.body.SignInBody;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserDataRepositoryTest {

    private static final String FAKE_EMAIL = "email@test.com";
    private static final String FAKE_PASSWORD = "secret";
    private static final String FAKE_FIREBASE_TOKEN = "token";

    private UserDataRepository userDataRepository;
    @Mock
    private RestApi mockRestApi;

    @Before
    public void setUp() {
        userDataRepository = new UserDataRepository(mockRestApi);
    }

    @Test
    public void testSignInHappyCase() {
        UserEntity userEntity = new UserEntity();
        given(mockRestApi.signIn(buildSignInBody())).willReturn(Observable.just(userEntity));
        userDataRepository.signIn(FAKE_EMAIL,FAKE_PASSWORD,FAKE_FIREBASE_TOKEN);

        verify(mockRestApi).signIn(buildSignInBody());
    }

    private SignInBody buildSignInBody(){
        SignInBody body =  new SignInBody();
        body.setEmail(FAKE_EMAIL);
        body.setPassword(FAKE_PASSWORD);
        body.setFirebaseToken(FAKE_FIREBASE_TOKEN);
        return  body;
    }
}
