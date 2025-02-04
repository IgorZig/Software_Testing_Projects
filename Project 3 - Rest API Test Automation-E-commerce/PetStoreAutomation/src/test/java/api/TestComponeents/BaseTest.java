package api.TestComponeents;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.github.javafaker.Faker;
import org.testng.asserts.SoftAssert;
import api.Data.UserDataGenerator;
import api.payload.User;

import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

public class BaseTest {
	
	protected User userPayload;
	protected SoftAssert softAssert;
    protected Logger logger;
    protected Faker faker;

    @BeforeSuite
    public void baseSetUp() {
        // Initialize logger and Faker instance
        logger = LogManager.getLogger(this.getClass());
        faker = new Faker();
        logger.info("Base test setup complete.");
    
        softAssert = new SoftAssert();
        // Create a new user payload
        userPayload = UserDataGenerator.getUserPayload();
        logger.debug("User payload created: " + userPayload.getId());
    }
}
