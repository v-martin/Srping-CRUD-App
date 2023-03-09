package lab4.lab4;

import lab4.lab4.entity.RefreshToken;
import lab4.lab4.repo.RefreshTokenRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class TokenTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private RefreshTokenRepository repo;

    // test methods go below
    @Test
    public void testCreateUser() {
        RefreshToken token = new RefreshToken();
        token.setToken("aboba");
        token.setUserId(1L);
        token.setExpiryDate(new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime().toInstant());

        RefreshToken savedUser = repo.save(token);

        RefreshToken existUser = entityManager.find(RefreshToken.class, savedUser.getId());

    }
}
