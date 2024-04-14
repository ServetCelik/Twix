package com.twix.tweetapi.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    @Value("${rabbitmq.exchange}")
    private String exchangeName;

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(exchangeName);
    }

    @Bean
    public Queue queue() {
        return new Queue("user_queue");
    }

    @Bean
    public Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("user_created");
    }
}

//    @Value("${rabbitmq.exchange.tweet.created}")
//    private String exchange;
//    @Value("rabbitmq.queue.name")
//    private String queue;
//    @Value("rabbitmq.routing.key")
//    private String routingKey;
//
//    @Bean
//    Queue queue(){
//        return new Queue(queue, false);
//    }
//
//    @Bean
//    public TopicExchange exchange (){
//        return new TopicExchange(exchange);
//    }
//
//    @Bean
//    public Binding binding(){
//        return BindingBuilder
//                .bind(queue())
//                .to(exchange())
//                .with(routingKey);
//    }
//
//    @Bean
//    MessageConverter messageConverter(){
//        return new Jackson2JsonMessageConverter();
//    }
//
//    @Bean
//    RabbitTemplate rabbitTemplate(ConnectionFactory factory){
//        RabbitTemplate rabbitTemplate = new RabbitTemplate(factory);
//        rabbitTemplate.setMessageConverter(messageConverter());
//        return rabbitTemplate;
//    }


