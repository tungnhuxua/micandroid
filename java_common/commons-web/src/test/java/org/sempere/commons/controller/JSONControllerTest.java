package org.sempere.commons.controller;

import static junit.framework.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import static org.mockito.Mockito.*;

import flexjson.JSONSerializer;
import org.sempere.commons.Pair;
import org.sempere.commons.encryption.Encrypter;
import org.sempere.commons.encryption.PassPhraseProvider;

public class JSONControllerTest {

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @Before
    public void before() throws Exception {
        this.request = new MockHttpServletRequest();
        this.response = new MockHttpServletResponse();
    }

    @Test
    public void handleRequestInternal() throws Exception {
        JSONController controller = mock(JSONController.class);
        when(controller.getObjectToSerialize()).thenReturn(new Pair<Long, String>(1L, "value1"));
        when(controller.createSerializer()).thenCallRealMethod();
        when(controller.handleRequestInternal(this.request, this.response)).thenCallRealMethod();

        controller.handleRequestInternal(this.request, this.response);
        assertEquals("application/json", this.response.getContentType());
        assertEquals("{\"class\":\"org.sempere.commons.Pair\",\"pair1\":1,\"pair2\":\"value1\"}", this.response.getContentAsString());
    }

    @Test
    public void getCurrentToken() throws Exception {
        JSONController controller = mock(JSONController.class);
        when(controller.getCurrentToken(this.request)).thenCallRealMethod();

        this.request.addParameter("token", "123456789");
        assertEquals("123456789", controller.getCurrentToken(this.request));
    }

    @Test
    public void getExpectedToken() throws Exception {
        PassPhraseProvider passPhraseProvider = mock(PassPhraseProvider.class);
        when(passPhraseProvider.getPassPhrase()).thenReturn("MyPassPhrase");

        Encrypter encrypter = mock(Encrypter.class);
        when(encrypter.encrypt("MyPassPhrase")).thenReturn("123456789");

        JSONController controller = mock(JSONController.class);
        when(controller.getPassPhraseProvider()).thenReturn(passPhraseProvider);
        when(controller.getEncrypter()).thenReturn(encrypter);
        when(controller.getExpectedToken()).thenCallRealMethod();

        assertEquals("123456789", controller.getExpectedToken());
    }

    @Test
    public void createSerializer() throws Exception {
        JSONController controller = mock(JSONController.class);
        when(controller.createSerializer()).thenCallRealMethod();

        JSONSerializer serializer = controller.createSerializer();
        assertEquals(1, serializer.getExcludes().size());
        assertEquals("[*,password]", serializer.getExcludes().iterator().next().toString());
    }
}
