package com.ruoyi.mq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQ 交换机补充配置（用于声明缺失的 chat.store.exchange）
 */
@Configuration
public class ChatExchangeConfig {

    /**
     * 声明聊天消息存储交换机（chat.store.exchange）
     * 匹配代码中使用的交换机名称
     */
    @Bean
    public DirectExchange chatStoreExchange() {
        // 交换机类型使用 direct（与业务代码中使用的类型一致）
        // durable = true 表示持久化交换机（服务重启后不丢失）
        return ExchangeBuilder.directExchange("chat.store.exchange")
                .durable(true)
                .build();
    }

    /**
     * 绑定聊天消息队列到交换机（如果需要）
     * 对应配置中的 chat.store.queue
     */
    @Bean
    public Queue chatStoreQueue() {
        // 队列持久化
        return QueueBuilder.durable("chat.queue")
                .build();
    }

    /**
     * 绑定交换机与队列（使用配置中的路由键 chat-store-router）
     */
    @Bean
    public Binding chatStoreBinding(DirectExchange chatStoreExchange, Queue chatStoreQueue) {
        return BindingBuilder.bind(chatStoreQueue)
                .to(chatStoreExchange)
                .with("chat-store-router"); // 匹配配置中的路由键
    }
}
