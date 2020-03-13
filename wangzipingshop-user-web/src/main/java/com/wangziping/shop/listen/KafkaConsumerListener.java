package com.wangziping.shop.listen;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.listener.MessageListener;

import com.github.pagehelper.PageInfo;
import com.wangziping.shop.comm.ShopConstant;
import com.wangziping.shop.pojo.Spu;

public class KafkaConsumerListener implements MessageListener<String, String> {

	// @Autowired
	// private ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	private RedisTemplate<String, PageInfo<Spu>> redisTemplate;

	/**
	 * 日志对象
	 */
	private Logger logger = Logger.getLogger(KafkaConsumerListener.class);

	{
		System.out.println("》》》》》》》》》》》》》》》》》》》》这里被实例化了。。。。。。。。。。。。。");
	}

	public void onMessage(ConsumerRecord<String, String> data) {

		System.out.println("get a message .......... ");
		// 获取key值
		String key = data.key();

		if (key != null) {
			// 判断
			System.out.println("key is " + key);
			String value = data.value();
			System.out.println("value is " + value);

			switch (key) {
			case "addspu":
				System.out.println("新添加的商品是" + value);
				// 清空缓存
				redisTemplate.delete(ShopConstant.SPU_CACHE_KEY);
				break;

			default:
				break;
			}
		} else {
			logger.info("key未null值");
		}

	}

}
