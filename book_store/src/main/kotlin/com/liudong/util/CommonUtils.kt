package com.liudong.util

/**
 * @description 文件描述
 *
 * @author liudong (liudong@rd.netease.com)
 * @date 2020/10/31 9:37 下午
 */
class CommonUtils {


    companion object {


        /**
         * @return 验证不通过，返回true
         * */
        fun parameterVerify(vararg parameter: String): Boolean {
            var ok = true
            for (item in parameter) {
                if (item.isEmpty()) {
                    ok = false
                    break
                }
            }
            return ok.not()
        }
    }
}