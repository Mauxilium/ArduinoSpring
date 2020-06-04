package it.mauxilium.arduinospring.model;

import it.mauxilium.arduinojavaserialrpc.ArduinoJavaSerialRpc;

public class CardModel {
    private String port;
    private int baudRate;
    private String cardName = "";
    private ArduinoJavaSerialRpc connector;

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public int getBaudRate() {
        return baudRate;
    }

    public void setBaudRate(int baudRate) {
        this.baudRate = baudRate;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public ArduinoJavaSerialRpc getConnector() {
        return connector;
    }

    public void setConnector(ArduinoJavaSerialRpc connector) {
        this.connector = connector;
    }
}
