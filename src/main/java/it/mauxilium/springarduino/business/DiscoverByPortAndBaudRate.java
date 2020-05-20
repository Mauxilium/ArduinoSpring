package it.mauxilium.springarduino.business;

import it.mauxilium.arduinojavaserialrpc.ArduinoJavaSerialRpc;
import it.mauxilium.springarduino.model.CardModel;

public class DiscoverByPortAndBaudRate {
    public static CardModel find(final String port, final int rate, final CardDiscover scout) {
        return scout.identifyCard(new ArduinoJavaSerialRpc(port, rate));
    }
}
