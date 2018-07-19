package org.xudl.demo.mq.rocketmq;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(RocketMQProperties.PREFIX)
public class RocketMQProperties {
	public static final String PREFIX = "rocketmq";
	private String nameserverAddr;
	private String producerGroupName;
	private String producerInstanceName;
	private String transactionProducerGroupName;
	private String transactionProducerInstanceName;
	private String cusumerGroupName;
	private String cusumerInstanceName;
	private int consumerBatchMaxSize;
	private boolean consumerBroadcasting;
	private boolean enableHisConsumer;
	private boolean enableOrderConsumer;
	private List<Object> subscribe = new ArrayList<>();

	public String getNameserverAddr() {
		return nameserverAddr;
	}

	public void setNameserverAddr(String nameserverAddr) {
		this.nameserverAddr = nameserverAddr;
	}

	public String getProducerGroupName() {
		return producerGroupName;
	}

	public void setProducerGroupName(String producerGroupName) {
		this.producerGroupName = producerGroupName;
	}

	public String getProducerInstanceName() {
		return producerInstanceName;
	}

	public void setProducerInstanceName(String producerInstanceName) {
		this.producerInstanceName = producerInstanceName;
	}

	public String getTransactionProducerGroupName() {
		return transactionProducerGroupName;
	}

	public void setTransactionProducerGroupName(String transactionProducerGroupName) {
		this.transactionProducerGroupName = transactionProducerGroupName;
	}

	public String getTransactionProducerInstanceName() {
		return transactionProducerInstanceName;
	}

	public void setTransactionProducerInstanceName(String transactionProducerInstanceName) {
		this.transactionProducerInstanceName = transactionProducerInstanceName;
	}

	public String getCusumerGroupName() {
		return cusumerGroupName;
	}

	public void setCusumerGroupName(String cusumerGroupName) {
		this.cusumerGroupName = cusumerGroupName;
	}

	public String getCusumerInstanceName() {
		return cusumerInstanceName;
	}

	public void setCusumerInstanceName(String cusumerInstanceName) {
		this.cusumerInstanceName = cusumerInstanceName;
	}

	public int getConsumerBatchMaxSize() {
		return consumerBatchMaxSize;
	}

	public void setConsumerBatchMaxSize(int consumerBatchMaxSize) {
		this.consumerBatchMaxSize = consumerBatchMaxSize;
	}

	public boolean isConsumerBroadcasting() {
		return consumerBroadcasting;
	}

	public void setConsumerBroadcasting(boolean consumerBroadcasting) {
		this.consumerBroadcasting = consumerBroadcasting;
	}

	public boolean isEnableHisConsumer() {
		return enableHisConsumer;
	}

	public void setEnableHisConsumer(boolean enableHisConsumer) {
		this.enableHisConsumer = enableHisConsumer;
	}

	public boolean isEnableOrderConsumer() {
		return enableOrderConsumer;
	}

	public void setEnableOrderConsumer(boolean enableOrderConsumer) {
		this.enableOrderConsumer = enableOrderConsumer;
	}

	public List<Object> getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(List<Object> subscribe) {
		this.subscribe = subscribe;
	}
}
