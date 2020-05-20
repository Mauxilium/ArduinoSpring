package it.mauxilium.springarduino.business;

import it.mauxilium.arduinojavaserialrpc.ArduinoJavaSerialRpc;
import it.mauxilium.springarduino.model.CardModel;

import java.util.Arrays;

public class DiscoverCardByPortAndBaudRateList {
    public static CardModel find(final String port, final String[] rates, final CardDiscover scout) {
        return Arrays.stream(rates)
                .map(r -> scout.identifyCard(new ArduinoJavaSerialRpc(port, Integer.parseInt(r))))
                .findFirst().orElse(null);
    }
}
