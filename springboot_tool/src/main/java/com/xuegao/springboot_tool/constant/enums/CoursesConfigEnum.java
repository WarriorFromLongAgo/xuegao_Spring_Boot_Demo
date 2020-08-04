// package com.xuegao.springboot_tool.constant.enums;
//
// import java.util.List;
//
// public enum CoursesConfigEnum {
//
//     // 课程来源 id  0 内部 1 外部
//     coursesSourceId("课程来源id", "coursesSourceId", "1") {
//         @Override
//         public Integer saveCourseConfigDao(ITrainCourseDao trainCourseDao, Long coursesId, Object configvalue) {
//             return trainCourseDao.saveCourseConfigDao(coursesId, coursesSourceId.getType(), configvalue.toString());
//         }
//     },
//     // 讲师
//     lecturerName("讲师", "lecturerName", "2") {
//         @Override
//         public Integer saveCourseConfigDao(ITrainCourseDao trainCourseDao, Long coursesId, Object configvalue) {
//             return trainCourseDao.saveCourseConfigDao(coursesId, coursesSourceId.getType(), configvalue.toString());
//         }
//     },
//     // 归属组织
//     organization("归属组织", "organization", "3") {
//         @Override
//         public Integer saveCourseConfigDao(ITrainCourseDao trainCourseDao, Long coursesId, Object configvalue) {
//             return trainCourseDao.saveCourseConfigDao(coursesId, coursesSourceId.getType(), configvalue.toString());
//         }
//     },
//     // 职级
//     titleLevel("职级", "titleLevel", "4") {
//         @Override
//         public Integer saveCourseConfigDao(ITrainCourseDao trainCourseDao, Long coursesId, Object configvalue) {
//             return trainCourseDao.saveCourseConfigDao(coursesId, coursesSourceId.getType(), configvalue.toString());
//         }
//     },
//     // 课程开发人名称
//     courseDeveloperName("课程开发人名称", "courseDeveloperName", "5") {
//         @Override
//         public Integer saveCourseConfigDao(ITrainCourseDao trainCourseDao, Long coursesId, Object configvalue) {
//             return trainCourseDao.saveCourseConfigDao(coursesId, coursesSourceId.getType(), configvalue.toString());
//         }
//     },
//     // 机密类型id
//     secretTypeId("机密类型id", "secretTypeId", "6") {
//         @Override
//         public Integer saveCourseConfigDao(ITrainCourseDao trainCourseDao, Long coursesId, Object configvalue) {
//             return trainCourseDao.saveCourseConfigDao(coursesId, coursesSourceId.getType(), configvalue.toString());
//         }
//     },
//
//     // 能力项————标签
//     capable("能力项", "capable", "7") {
//         @Override
//         public Integer saveCourseConfigDao(ITrainCourseDao trainCourseDao, Long coursesId, Object configvalue) {
//             List<String> stringList = (List<String>) configvalue;
//
//             for (String configvalueStr : stringList) {
//                 trainCourseDao.saveCourseConfigDao(coursesId, capable.getType(), configvalueStr);
//             }
//             return 1;
//         }
//     },
//     // 推荐范围————标签
//     recommendedRange("推荐范围", "recommendedRange", "8") {
//         @Override
//         public Integer saveCourseConfigDao(ITrainCourseDao trainCourseDao, Long coursesId, Object configvalue) {
//             List<String> stringList = (List<String>) configvalue;
//
//             for (String configvalueStr : stringList) {
//                 trainCourseDao.saveCourseConfigDao(coursesId, capable.getType(), configvalueStr);
//             }
//             return 1;
//         }
//     },
//     // 发布范围
//     publishScope("发布范围", "publishScope", "9") {
//         @Override
//         public Integer saveCourseConfigDao(ITrainCourseDao trainCourseDao, Long coursesId, Object configvalue) {
//             List<String> stringList = (List<String>) configvalue;
//
//             for (String configvalueStr : stringList) {
//                 trainCourseDao.saveCourseConfigDao(coursesId, capable.getType(), configvalueStr);
//             }
//             return 1;
//         }
//     },
//     ;
//
//     CoursesConfigEnum(String description, String name, String type) {
//         this.name = name;
//         this.description = description;
//         this.type = type;
//     }
//
//     private String name;
//     private String description;
//     private String type;
//
//     public String getType() {
//         return type;
//     }
//
//     public void setType(String type) {
//         this.type = type;
//     }
//
//     public String getName() {
//         return name;
//     }
//
//     public void setName(String name) {
//         this.name = name;
//     }
//
//     public String getDescription() {
//         return description;
//     }
//
//     public void setDescription(String description) {
//         this.description = description;
//     }
//
//     public static CoursesConfigEnum getEnumByName(String name) {
//         for (CoursesConfigEnum coursesConfigEnum : CoursesConfigEnum.values()) {
//             if (coursesConfigEnum.getName().equals(name)) {
//                 return coursesConfigEnum;
//             }
//         }
//         return null;
//     }
//
//     public abstract Integer saveCourseConfigDao(ITrainCourseDao trainCourseDao, Long coursesId, Object configvalue);
// }