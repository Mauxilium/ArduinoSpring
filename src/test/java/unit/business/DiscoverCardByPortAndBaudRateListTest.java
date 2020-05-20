package unit.business;

import it.mauxilium.arduinojavaserialrpc.ArduinoJavaSerialRpc;
import it.mauxilium.springarduino.business.CardDiscover;
import it.mauxilium.springarduino.business.DiscoverCardByPortAndBaudRateList;
import it.mauxilium.springarduino.model.CardModel;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DiscoverCardByPortAndBaudRateListTest {

    @Mock
    private CardDiscover mockDiscover;

    @Test
    public void discoverOk() {
        ArduinoJavaSerialRpc mockedConnector = Mockito.mock(ArduinoJavaSerialRpc.class);
        Mockito.when(mockedConnector.getBaudRate()).thenReturn(1200, 1200);
        Mockito.when(mockedConnector.getPortName()).thenReturn("COM2");

        CardModel testValues = new CardModel();
        testValues.setBaudRate(1200);
        testValues.setCardName("FakeTestCard");
        testValues.setPort("COM2");
        testValues.setConnector(mockedConnector);

        Mockito.when(mockDiscover.identifyCard(mockedConnector)).thenReturn(testValues);
        CardModel result = DiscoverCardByPortAndBaudRateList.find("COM2", new String[] {"28800", "1200"}, mockDiscover);

        Assert.assertEquals("COM2", result.getPort());
    }
}
