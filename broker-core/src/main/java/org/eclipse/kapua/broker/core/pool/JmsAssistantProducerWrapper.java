/*******************************************************************************
 * Copyright (c) 2011, 2017 Eurotech and/or its affiliates and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Eurotech - initial API and implementation
 *******************************************************************************/
package org.eclipse.kapua.broker.core.pool;

import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.eclipse.kapua.broker.core.message.JmsUtil;
import org.eclipse.kapua.broker.core.message.MessageConstants;

/**
 * Broker assistant ({@link JmsProducerWrapper}) implementation.<BR>
 * This class provide methods to send messages for the device life cycle (to be send outside to a device specific topic)
 * 
 * @since 1.0
 */
public class JmsAssistantProducerWrapper extends JmsProducerWrapper {

    public JmsAssistantProducerWrapper(ActiveMQConnectionFactory vmconnFactory, String destination, boolean transacted, boolean start) throws JMSException {
        super(vmconnFactory, destination, transacted, start);
    }

    /**
     * Send a text message to the specified topic
     * 
     * @param topic
     * @param message
     * @throws JMSException
     */
    public void send(String topic, String message) throws JMSException {
        TextMessage textMessage = session.createTextMessage();

        textMessage.setStringProperty(MessageConstants.PROPERTY_ORIGINAL_TOPIC, JmsUtil.convertMqttWildCardToJms(topic));
        textMessage.setLongProperty(MessageConstants.PROPERTY_ENQUEUED_TIMESTAMP, System.currentTimeMillis());
        textMessage.setText(message);

        producer.send(textMessage);
    }

}
