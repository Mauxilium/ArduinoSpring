package it.mauxilium.springarduino.business;

import it.mauxilium.arduinojavaserialrpc.ArduinoJavaSerialRpc;
import it.mauxilium.springarduino.model.CardModel;

import java.util.Arrays;

public class DiscoverCardByPortAndCardId {
    public static CardModel find(final String port, final String cardName, final CardDiscover scout) {
        return Arrays.stream(CardDiscover.legalBaudRates).mapToObj(r -> {
            CardModel successfully = scout.identifyCard(new ArduinoJavaSerialRpc(port, r));
            if (cardName.equals(successfully.getCardName())) {
                return successfully;
            } else {
                return null;
            }
        }).findFirst().orElse(null);
    }
}
