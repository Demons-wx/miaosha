package online.wangxuan.miaosha.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangxuan
 * @date 2018/6/3 上午11:07
 */

@Configuration
public class MQConfig {

    public static final String MIAOSHA_QUEUE = "miaosha.queue";

    public static final String QUEUE = "queue";
    public static final String TOPIC_QUEUE1 = "topic.queue1";
    public static final String TOPIC_QUEUE2 = "topic.queue2";
    public static final String TOPIC_EXCHANGE = "topicExchange";
    public static final String ROUTING_KEY1 = "topic.key1";
    public static final String ROUTING_KEY2 = "topic.#";
    public static final String FANOUT_EXCHANGE = "fanoutExchange";
    public static final String HEADERS_EXCHANGE = "headersExchange";
    public static final String HEADERS_QUEUE = "headers.queue";

    @Bean
    public Queue queue() {
        return new Queue(MIAOSHA_QUEUE, true);
    }

    /**
     * Direct模式
     * @return
     */
//    @Bean
//    public Queue queue() {
//        return new Queue(QUEUE, true);
//    }

    /**
     * Topic模式
     * @return
     */
//    @Bean
//    public Queue topicQueue1() {
//        return new Queue(TOPIC_QUEUE1, true);
//    }
//    @Bean
//    public Queue topicQueue2() {
//        return new Queue(TOPIC_QUEUE2, true);
//    }
//    @Bean
//    public TopicExchange topicExchange() {
//        return new TopicExchange(TOPIC_EXCHANGE);
//    }
//    @Bean
//    public Binding topicBinding1() {
//        return BindingBuilder.bind(topicQueue1())
//                .to(topicExchange())
//                .with(ROUTING_KEY1);
//    }
//    @Bean
//    public Binding topicBinding2() {
//        return BindingBuilder.bind(topicQueue2())
//                .to(topicExchange())
//                .with(ROUTING_KEY2);
//    }
//
//    /**
//     * Fanout 广播模式
//     *
//     */
//    @Bean
//    public FanoutExchange fanoutExchange() {
//        return new FanoutExchange(FANOUT_EXCHANGE);
//    }
//    @Bean
//    public Binding fanoutBinding1() {
//        return BindingBuilder.bind(topicQueue1())
//                .to(fanoutExchange());
//    }
//    @Bean
//    public Binding fanoutBinding2() {
//        return BindingBuilder.bind(topicQueue2())
//                .to(fanoutExchange());
//    }
//
//
//    /**
//     * Headers模式
//     */
//    @Bean
//    public HeadersExchange headersExchange() {
//        return new HeadersExchange(HEADERS_EXCHANGE);
//    }
//    @Bean
//    public Queue headerQueue() {
//        return new Queue(HEADERS_QUEUE, true);
//    }
//    @Bean
//    public Binding headersBinding() {
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("header1", "value1");
//        map.put("header2", "value2");
//        return BindingBuilder.bind(headerQueue())
//                .to(headersExchange())
//                .whereAll(map).match();
//    }
}
