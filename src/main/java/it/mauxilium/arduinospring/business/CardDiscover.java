package it.mauxilium.arduinospring.business;

import it.mauxilium.arduinojavaserialrpc.ArduinoJavaSerialRpc;
import it.mauxilium.arduinojavaserialrpc.exception.ArduinoRpcInitializationError;
import it.mauxilium.arduinojavaserialrpc.exception.ArduinoRpcJavaFailsException;
import it.mauxilium.arduinospring.model.CardModel;

public class CardDiscover {

    public static int[] legalBaudRates = {
            ArduinoJavaSerialRpc.DATA_RATE_300,
            ArduinoJavaSerialRpc.DATA_RATE_600,
            ArduinoJavaSerialRpc.DATA_RATE_1200,
            ArduinoJavaSerialRpc.DATA_RATE_2400,
            ArduinoJavaSerialRpc.DATA_RATE_4800,
            ArduinoJavaSerialRpc.DATA_RATE_9600,
            ArduinoJavaSerialRpc.DATA_RATE_14400,
            ArduinoJavaSerialRpc.DATA_RATE_19200,
            ArduinoJavaSerialRpc.DATA_RATE_28800,
            ArduinoJavaSerialRpc.DATA_RATE_38400,
            ArduinoJavaSerialRpc.DATA_RATE_57600,
            ArduinoJavaSerialRpc.DATA_RATE_115200
    };

    public static boolean isLegalBaudRate(final int baudRate) {
        for (int legalBaudRate : legalBaudRates) {
            if (baudRate == legalBaudRate) {
                return true;
            }
        }
        return false;
    }

    public CardModel identifyCard(final ArduinoJavaSerialRpc connector) {
        if (CardDiscover.isLegalBaudRate(connector.getBaudRate())) {
            try {
                connector.connect();
            } catch (ArduinoRpcInitializationError ex) {
                System.out.println("Failed match searching Arduino card using baud: " + connector.getBaudRate());
                return null;
            }
            final CardModel successfully = new CardModel();
            try {
                successfully.setCardName(connector.getCardName());
            } catch (ArduinoRpcJavaFailsException e) {
                System.out.println("Failed match searching Arduino card using baud: " + connector.getBaudRate());
                return null;
            }
            successfully.setPort(connector.getPortName());
            successfully.setBaudRate(connector.getBaudRate());
            successfully.setConnector(connector);
            return successfully;
        } else {
            return null;
        }
    }

}
