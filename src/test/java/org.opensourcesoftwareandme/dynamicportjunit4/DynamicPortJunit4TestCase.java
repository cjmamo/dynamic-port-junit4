package org.opensourcesoftwareandme.dynamicportjunit4;

import org.junit.Rule;
import org.junit.Test;
import org.mule.api.MuleMessage;
import org.mule.api.client.MuleClient;
import org.mule.tck.junit4.FunctionalTestCase;
import org.mule.tck.junit4.rule.DynamicPort;
import org.mule.transport.NullPayload;

import static org.junit.Assert.*;

public class DynamicPortJunit4TestCase extends FunctionalTestCase
{
    @Rule
    public DynamicPort port = new DynamicPort("foo");

    protected String getConfigResources()
    {
        return "dynamic-port-junit4-functional-test-config.xml";
    }

    @Test
    public void DynamicPortJunit4() throws Exception
    {
        MuleClient client = muleContext.getClient();
        MuleMessage result = client.send("http://localhost:" + port.getNumber() , "some data", null);

        assertNotNull(result);
        assertNull(result.getExceptionPayload());
        assertFalse(result.getPayload() instanceof NullPayload);

        assertEquals("some data Received", result.getPayloadAsString());
    }
}
