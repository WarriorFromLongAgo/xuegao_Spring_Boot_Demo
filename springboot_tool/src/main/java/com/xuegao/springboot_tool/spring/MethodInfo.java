package com.xuegao.springboot_tool.spring;

import java.lang.reflect.Method;

public
class

MethodInfo {


    public

    Object
            obj;


    public

    Method
            method;


    public

    static MethodInfo
    valueOf
            (
                    Method
                            method
                    ,

                    Object
                            obj
            ) {


        MethodInfo
                info
                =

                new

                        MethodInfo
                        ();

        info
                .
                method
                =
                method
        ;

        info
                .
                obj
                =
                obj
        ;


        return
                info
                ;


    }


    public Object
    getObj
            () {


        return
                obj
                ;


    }


    public Method
    getMethod
            () {


        return
                method
                ;


    }

}