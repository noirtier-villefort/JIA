package io.github.taz03.jia;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public final class LoginTest {
    @BeforeAll
    public static void initTestClientUser() throws Exception {
        TestProxy.user = new ObjectMapper().readValue(Files.readString(Path.of("src/integrationTest/resources/user.json")), TestProxy.User.class);
    }

    @Test
    public void loginTest() throws Exception {
        InstagramClient client = new InstagramClient(TestProxy.user.username(), TestProxy.user.password());
        client.login(() -> System.getProperty("verificationCode"));

        assertNotNull(client.getAuthorization());
        TestProxy.client = client;
    }
}
