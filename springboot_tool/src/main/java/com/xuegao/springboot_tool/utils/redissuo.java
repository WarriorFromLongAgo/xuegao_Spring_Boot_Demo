// package com.xuegao.springboot_tool.utils;
//
// /**
//  * <br/> @PackageName：com.xuegao.springboot_tool.utils
//  * <br/> @ClassName：redissuo
//  * <br/> @Description：
//  * <br/> @author：80004960
//  * <br/> @date：2020/7/22 16:49
//  */
// public class redissuo {
//     public SeckillActivityRequestVO seckillHandle(SeckillActivityRequestVO request) {
//         SeckillActivityRequestVO response;
//         String key = "key:" + request.getSeckillId();
//         String val = UUID.randomUUID().toString();
//         try {
//             Boolean lockFlag = distributedLocker.lock(key, val, 10, TimeUnit.SECONDS);
//             if (!lockFlag) {
//                 // 业务异常
//             }
//
//             // 用户活动校验
//             // 库存校验，基于redis本身的原子性来保证
//             Long currStock = stringRedisTemplate.opsForHash().increment(key + ":info", "stock", -1);
//             if (currStock < 0) { // 说明库存已经扣减完了。
//                 // 业务异常。
//                 log.error("[抢购下单] 无库存");
//             } else {
//                 // 生成订单
//                 // 发布订单创建成功事件
//                 // 构建响应
//             }
//         } finally {
//             distributedLocker.safedUnLock(key, val);
//             // 构建响应
//         }
//         return response;
//     }
//
//     作者：浪漫先生
//     链接：https://juejin.im/post/5f159cd8f265da22e425f71d
//     来源：掘金
//     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
// }