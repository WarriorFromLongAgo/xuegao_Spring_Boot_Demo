package com.xuegao.springboot_tool.sharding.use;// package com.xuegao.springboot_tool.sharding.use;
//
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RestController;
//
// @RestController
// public class Cccc {
//
//     @Override
//     @SlaveDataSource
//     public ResponseData<?> get(@RequestBody request request) {
//         long startTime = System.currentTimeMillis();
//         logger.info("入参={}", JsonUtils.deserializer(request));
//
//         ResponseData<?> resp = new ResponseData<>();
//         resp.setData(customerService.get(request)).ok();
//
//         long endTime = System.currentTimeMillis();
//         logger.info("耗时{}毫秒,出参={}");
//         return resp;
//     }
// }