package com.bhagirath.mocking_frameworks;

import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static junit.framework.TestCase.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().dynamicPort());
    @Mock
    private UserRepository repository;

    private UserService userService;

    @Before
    public void setup() {
        userService = spy(new UserService(repository));
    }

    @Test
    public void sampleTest() throws IOException {
        when(userService.getHostName()).thenReturn("http://localhost:"+ wireMockRule.port());
        wireMockRule.stubFor(get(urlPathEqualTo("/getUser"))
                .willReturn(aResponse()
                            .withHeader("Content-Type", "application.json")
                            .withStatus(200)
                            .withBody("{}")));
        when(repository.getDefaultUserDetails()).thenReturn(new User());
        assertNotNull(userService.findUserById("id"));
    }
}
