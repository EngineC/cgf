package Engine.jredis;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class demo1
{
	@Test
	public void demo1(){
		//设置ip地址，端口
		Jedis jedis=new Jedis("192.168.121.128",6379);
		//保持数据
		//jedis.set("guan","Engvine");
		//获取数据
		System.out.println(jedis.get("Engine"));
		//释放资源
		jedis.close();
	}
	/**
	 * 连接池
	 */
	@Test
	public void demo2(){
		//获得连接池配置对象
		JedisPoolConfig config=new JedisPoolConfig();
		//设置最大空闲连接数
		config.setMaxIdle(10);
		//设置最大连接数
		config.setMaxTotal(30);
		
		//获得连接池
		JedisPool jedisPool=new JedisPool(config,"192.168.121.128",6379);
		//获得核心对象
		Jedis jedis=null;
		try{
			//通过连接池获得连接
			jedis=jedisPool.getResource();
			//设置数据
			jedis.set("Engine", "123");
			String value=jedis.get("Engine");
			System.out.println(value);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			//释放资源
			if(jedis!=null){
				jedis.close();
			}
			if(jedisPool!=null){
				jedisPool.close();
			}
		}
	}
}
