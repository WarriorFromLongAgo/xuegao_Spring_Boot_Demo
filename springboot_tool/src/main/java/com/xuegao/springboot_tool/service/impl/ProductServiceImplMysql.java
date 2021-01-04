// package com.xuegao.springboot_tool.service.impl;
//
// import org.springframework.transaction.annotation.Propagation;
// import org.springframework.transaction.annotation.Transactional;
//
// import javax.annotation.Resource;
//
// /**
//  * <br/> @PackageName：com.xuegao.springboot_tool.service.impl
//  * <br/> @ClassName：ProductServiceImplMysql
//  * <br/> @Description：
//  * <br/> @author：xuegao
//  * <br/> @date：2021/01/04 19:39
//  */
// public class ProductServiceImplMysql {
//
//     @Resource
//     private KdOrderMapper orderMapper;
//     @Resource
//     private KdOrderItemMapper orderItemMapper;
//     @Resource
//     private KdProductMapper productMapper;
//     @Resource
//     private DistributeLockMapper distributeLockMapper;
//     //购买商品id
//     private int purchaseProductId = 100100;
//     //购买商品数量
//     private int purchaseProductNum = 1;
//
//     @Transactional(propagation = Propagation.REQUIRED)
//     public  Integer createOrder() throws Exception{
//         log.info("进入了方法");
//         DistributeLock lock = distributeLockMapper.selectDistributeLock("order");
//         if(lock == null) throw new Exception("该业务分布式锁未配置");
//         log.info("拿到了锁");
//         //此处为了手动演示并发，所以我们暂时在这里休眠1分钟
//         Thread.sleep(60000);
//
//         KdProduct product = productMapper.selectByPrimaryKey(purchaseProductId);
//         if (product==null){
//             throw new Exception("购买商品："+purchaseProductId+"不存在");
//         }
//         //商品当前库存
//         Integer currentCount = product.getCount();
//         log.info(Thread.currentThread().getName()+"库存数"+currentCount);
//         //校验库存
//         if (purchaseProductNum > currentCount){
//             throw new Exception("商品"+purchaseProductId+"仅剩"+currentCount+"件，无法购买");
//         }
//
//         //在数据库中完成减量操作
//         productMapper.updateProductCount(purchaseProductNum,"kd",new Date(),product.getId());
//         //生成订单
//         ...次数省略，源代码可以到老猫的github下载：https://github.com/maoba/kd-distribute
//         return order.getId();
//     }
//
//     作者：公众号_程序员老猫
//     链接：https://juejin.cn/post/6913456598094626823
//     来源：掘金
//     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
// }

// SQL的写法如下：
//         select
//         *
//         from distribute_lock
//         where business_code = #{business_code,jdbcType=VARCHAR}
//         for update
//         复制代码
//         以上为主要实现逻辑，关于代码中的注意点：
//
//         createOrder方法必须要有事务，因为只有在事务存在的情况下才能触发select for update的锁。
//         代码中必须要对当前锁的存在性进行判断，如果为空的情况下，会报异常
//
//         我们来看一下最终运行的效果，先看一下console日志，
//         8080的console日志情况：
//         11:49:41  INFO 16360 --- [nio-8080-exec-2] c.k.d.service.MySQLOrderService          : 进入了方法
//         11:49:41  INFO 16360 --- [nio-8080-exec-2] c.k.d.service.MySQLOrderService          : 拿到了锁
//         复制代码
//         8081的console日志情况：
//         11:49:48  INFO 17640 --- [nio-8081-exec-2] c.k.d.service.MySQLOrderService          : 进入了方法
//         复制代码
//         通过日志情况，两个不同的jvm，由于第一个到8080的请求优先拿到了锁，所以8081的请求就处于等待锁释放才会去执行，这说明我们的分布式锁生效了。
//         再看一下完整执行之后的日志情况：
//         8080的请求：
//         11:58:01  INFO 15380 --- [nio-8080-exec-1] c.k.d.service.MySQLOrderService          : 进入了方法
//         11:58:01  INFO 15380 --- [nio-8080-exec-1] c.k.d.service.MySQLOrderService          : 拿到了锁
//         11:58:07  INFO 15380 --- [nio-8080-exec-1] c.k.d.service.MySQLOrderService          : http-nio-8080-exec-1库存数1
//         复制代码
//         8081的请求：
//         11:58:03  INFO 16276 --- [nio-8081-exec-1] c.k.d.service.MySQLOrderService          : 进入了方法
//         11:58:08  INFO 16276 --- [nio-8081-exec-1] c.k.d.service.MySQLOrderService          : 拿到了锁
//         11:58:14  INFO 16276 --- [nio-8081-exec-1] c.k.d.service.MySQLOrderService          : http-nio-8081-exec-1库存数0
//         11:58:14 ERROR 16276 --- [nio-8081-exec-1] o.a.c.c.C.[.[.[/].[dispatcherServlet]    : Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Request processing failed; nested exception is java.lang.Exception: 商品100100仅剩0件，无法购买] with root cause
//
//         java.lang.Exception: 商品100100仅剩0件，无法购买
//         at com.kd.distribute.service.MySQLOrderService.createOrder(MySQLOrderService.java:61) ~[classes/:na]
//         复制代码
//         很明显第二个请求由于没有库存，导致最终购买失败的情况，当然这个场景也是符合我们正常的业务场景的。最终我们数据库的情况是这样的：
//
//
//         很明显，我们到此数据库的库存和订单数量也都正确了。到此我们基于数据库的分布式锁实战演示完成，下面我们来归纳一下如果使用这种锁，有哪些优点以及缺点。
//
//         优点：简单方便、易于理解、易于操作。
//         缺点：并发量大的时候对数据库的压力会比较大。
//         建议：作为锁的数据库和业务数据库分开。
//
//         作者：公众号_程序员老猫
//         链接：https://juejin.cn/post/6913456598094626823
//         来源：掘金
//         著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。


// 三个字段
// id business_code business_name