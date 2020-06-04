package it.mauxilium.arduinospring.controller;

import it.mauxilium.arduinojavaserialrpc.ArduinoJavaSerialRpc;
import it.mauxilium.arduinospring.business.*;
import it.mauxilium.arduinospring.model.CardModel;

import java.util.HashMap;

public class CrossCardManager {
    private static CrossCardManager instance = null;
    private HashMap<String, CardModel> cardMap = new HashMap<>();

    private CrossCardManager() {}
    public static CrossCardManager getInstance() {
        if (instance == null) {
            instance = new CrossCardManager();
        }
        return instance;
    }

    public void addConnectedCard(final String cardName, final String port,
                                 final String baudRate, final String[] baudRates) {
        CardModel card;
        if (port.length() > 0) {
            if (baudRate.length() > 0) {
                card = DiscoverByPortAndBaudRate.find(port, Integer.parseInt(baudRate), new CardDiscover());
            } else if (baudRates.length > 0) {
                card = DiscoverCardByPortAndBaudRateList.find(port, baudRates, new CardDiscover());
            } else if (cardName.length() > 0) {
                card = DiscoverCardByPortAndCardId.find(port, cardName, new CardDiscover());
            } else {
                card = DiscoverCardByPortOnly.find(port);
            }

            if (card.getCardName() != null) {
                cardMap.put(card.getCardName(), card);
            } else {
                System.out.println("Why a card without name?");
                System.exit(-1);
            }
        }
    }

    public String[] getCards() {
        return cardMap.keySet().toArray(new String[0]);
    }

    public ArduinoJavaSerialRpc getCardByName(final String cardName) {
        return cardMap.get(cardName).getConnector();
    }
}
