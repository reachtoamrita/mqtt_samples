package com.sample;

import org.eclipse.paho.client.mqttv3.*;

public class Publisher {
    public static void main(String[] args) throws MqttException {

        String broker = "tcp://localhost:1883";
        String topicName = "iot_data";
        int qos = 1;

        MqttClient mqttClient = new MqttClient(broker, String.valueOf(System.nanoTime()));
        
        //Mqtt ConnectOptions is used to set the additional features to mqtt message
        MqttConnectOptions connOpts = new MqttConnectOptions();
        connOpts.setCleanSession(true); //no persistent session
        connOpts.setKeepAliveInterval(1000);

        MqttMessage message = new MqttMessage("Temperature = 25 degree C".getBytes());
        message.setQos(qos);     //sets qos level 1
        message.setRetained(true); //sets retained message

        MqttTopic topic = mqttClient.getTopic(topicName);

        mqttClient.connect(connOpts); //connects the broker with connect options
        topic.publish(message);    // publishes the message to the topic(test/topic)
        System.out.println("Message is published");
    }
}
