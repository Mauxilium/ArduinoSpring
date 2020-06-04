package it.mauxilium.arduinospring.business;

import it.mauxilium.arduinojavaserialrpc.ArduinoJavaSerialRpc;
import it.mauxilium.arduinospring.model.CardModel;

public class DiscoverByPortAndBaudRate {
    public static CardModel find(final String port, final int rate, final CardDiscover scout) {
        return scout.identifyCard(new ArduinoJavaSerialRpc(port, rate));
    }
}
