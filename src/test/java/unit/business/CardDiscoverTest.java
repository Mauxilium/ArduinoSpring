package unit.business;

import it.mauxilium.arduinojavaserialrpc.ArduinoJavaSerialRpc;
import it.mauxilium.arduinojavaserialrpc.exception.ArduinoRpcInitializationError;
import it.mauxilium.arduinojavaserialrpc.exception.ArduinoRpcJavaFailsException;
import it.mauxilium.springarduino.business.CardDiscover;
import it.mauxilium.springarduino.model.CardModel;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CardDiscoverTest extends CardDiscover {

    @Mock
    private ArduinoJavaSerialRpc connector;

    @Test
    public void isLegalBaudRateTest() {
        Assert.assertEquals(12, legalBaudRates.length);
        for (int br: legalBaudRates) {
            Assert.assertTrue(CardDiscover.isLegalBaudRate(br));
        }

        Assert.assertFalse(CardDiscover.isLegalBaudRate(1));
        Assert.assertFalse(CardDiscover.isLegalBaudRate(999));
    }

    @Test
    public void findCardOk() throws ArduinoRpcInitializationError, ArduinoRpcJavaFailsException {
        Mockito.when(connector.getBaudRate()).thenReturn(38400, 38400);
        Mockito.when(connector.getCardName()).thenReturn("FakeCard");
        Mockito.when(connector.getPortName()).thenReturn("COM555");

        CardModel result = identifyCard(connector);

        Assert.assertEquals(38400, result.getBaudRate());
        Assert.assertEquals("FakeCard", result.getCardName());
        Assert.assertEquals("COM555", result.getPort());
        Assert.assertEquals(connector, result.getConnector());

        Mockito.verify(connector, VerificationModeFactory.atMost(2)).getBaudRate();
        Mockito.verify(connector, VerificationModeFactory.atLeast(2)).getBaudRate();
        Mockito.verify(connector, VerificationModeFactory.atMost(1)).getCardName();
        Mockito.verify(connector, VerificationModeFactory.atMost(1)).getPortName();
        Mockito.verify(connector, VerificationModeFactory.atMost(1)).connect();
        Mockito.verifyNoMoreInteractions(connector);
        Mockito.reset(connector);
    }

    @Test
    public void findCardIllegalBaudRate() throws ArduinoRpcJavaFailsException {
        Mockito.when(connector.getBaudRate()).thenReturn(12);
        Mockito.when(connector.getCardName()).thenReturn("FakeCard2");
        Mockito.when(connector.getPortName()).thenReturn("COM34");

        CardModel result = identifyCard(connector);

        Assert.assertNull(result);

        Mockito.verify(connector, VerificationModeFactory.atMost(1)).getBaudRate();
        Mockito.verifyNoMoreInteractions(connector);
        Mockito.reset(connector);
    }

    @Test
    public void findCardConnectFails() throws ArduinoRpcInitializationError, ArduinoRpcJavaFailsException {
        Mockito.when(connector.getBaudRate()).thenReturn(12);
        Mockito.when(connector.getCardName()).thenReturn("FakeCard2");
        Mockito.when(connector.getPortName()).thenReturn("COM34");
        Mockito.doThrow(new ArduinoRpcInitializationError("TestException")).when(connector).connect();

        CardModel result = identifyCard(connector);

        Assert.assertNull(result);

        Mockito.verify(connector, VerificationModeFactory.atMost(1)).getBaudRate();
        Mockito.verifyNoMoreInteractions(connector);
        Mockito.reset(connector);
    }

    @Test
    public void findCardGetCardNameFails() throws ArduinoRpcInitializationError, ArduinoRpcJavaFailsException {
        Mockito.when(connector.getBaudRate()).thenReturn(12);
        Mockito.when(connector.getPortName()).thenReturn("COM34");
        Mockito.doThrow(new ArduinoRpcJavaFailsException("TestException")).when(connector).getCardName();

        CardModel result = identifyCard(connector);

        Assert.assertNull(result);

        Mockito.verify(connector, VerificationModeFactory.atMost(1)).getBaudRate();
        Mockito.verify(connector, VerificationModeFactory.atMost(1)).connect();
        Mockito.verifyNoMoreInteractions(connector);
        Mockito.reset(connector);
    }
}
