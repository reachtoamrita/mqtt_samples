package com.sample;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class Subscriber implements MqttCallback {

    private MqttClient client;

    public Subscriber() throws MqttException {
        String broker = "tcp://localhost:1883";
        MqttConnectOptions conOpt = new MqttConnectOptions();
        conOpt.setCleanSession(true);
        conOpt.setUserName("admin");
        conOpt.setPassword("admin".toCharArray());
        this.client = new MqttClient(broker, "MQTT-Java-Example",
                new MemoryPersistence());
        this.client.setCallback(this);
        this.client.connect(conOpt);

        this.client.subscribe("iot_data", 1);
    }

    public void connectionLost(Throwable cause) {
        System.out.println("Connection lost because: " + cause);
        System.exit(1);
    }

    public void messageArrived(String topic, MqttMessage message) throws Exception {
        System.out.println(String.format("[%s] %s", topic, new String(message.getPayload())));
    }

    public void deliveryComplete(IMqttDeliveryToken token) {

    }

    public static void main(String[] args) throws MqttException {
        Subscriber s = new Subscriber();

    }
}
