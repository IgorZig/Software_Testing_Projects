package api.Data;

import com.github.javafaker.Faker;
import api.payload.User;

public class UserDataGenerator {
	private static final Faker faker = new Faker();

    public static User getUserPayload() {
        User user = new User();
        user.setId(faker.idNumber().hashCode());
        user.setUsername(faker.name().username());
        user.setFirstName(faker.name().firstName());
        user.setLastName(faker.name().lastName());
        user.setEmail(faker.internet().safeEmailAddress());
        user.setPassword(faker.internet().password(5, 10));
        user.setPhone(faker.phoneNumber().cellPhone());
        return user;
    }
}


