package unit.business;

import it.mauxilium.mauxarconnector.MauxArConnector;
import it.mauxilium.mauxarconnector.exception.MauxArConnectorActionFailsException;
import it.mauxilium.mauxarconnector.exception.MauxArConnectorInitializationError;
import it.mauxilium.springarduino.business.CardDiscover;
import it.mauxilium.springarduino.model.CardModel;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;

@RunWith(MockitoJUnitRunner.class)
public class CardDiscoverTest extends CardDiscover {

    @Mock
    private MauxArConnector connector;

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
    public void findCardOk() throws MauxArConnectorActionFailsException, MauxArConnectorInitializationError {
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
    public void findCardIllegalBaudRate() throws IOException, MauxArConnectorActionFailsException {
        Mockito.when(connector.getBaudRate()).thenReturn(12);
        Mockito.when(connector.getCardName()).thenReturn("FakeCard2");
        Mockito.when(connector.getPortName()).thenReturn("COM34");

        CardModel result = identifyCard(connector);

        Assert.assertEquals(null, result);

        Mockito.verify(connector, VerificationModeFactory.atMost(1)).getBaudRate();
        Mockito.verifyNoMoreInteractions(connector);
        Mockito.reset(connector);
    }

    @Test
    public void findCardConnectFails() throws IOException, MauxArConnectorActionFailsException, MauxArConnectorInitializationError {
        Mockito.when(connector.getBaudRate()).thenReturn(12);
        Mockito.when(connector.getCardName()).thenReturn("FakeCard2");
        Mockito.when(connector.getPortName()).thenReturn("COM34");
        Mockito.doThrow(new MauxArConnectorInitializationError("TestException")).when(connector).connect();

        CardModel result = identifyCard(connector);

        Assert.assertEquals(null, result);

        Mockito.verify(connector, VerificationModeFactory.atMost(1)).getBaudRate();
        Mockito.verifyNoMoreInteractions(connector);
        Mockito.reset(connector);
    }

    @Test
    public void findCardGetCardNameFails() throws IOException, MauxArConnectorActionFailsException, MauxArConnectorInitializationError {
        Mockito.when(connector.getBaudRate()).thenReturn(12);
        Mockito.when(connector.getPortName()).thenReturn("COM34");
        Mockito.doThrow(new MauxArConnectorActionFailsException("TestException")).when(connector).getCardName();

        CardModel result = identifyCard(connector);

        Assert.assertEquals(null, result);

        Mockito.verify(connector, VerificationModeFactory.atMost(1)).getBaudRate();
        Mockito.verify(connector, VerificationModeFactory.atMost(1)).connect();
        Mockito.verifyNoMoreInteractions(connector);
        Mockito.reset(connector);
    }
}
