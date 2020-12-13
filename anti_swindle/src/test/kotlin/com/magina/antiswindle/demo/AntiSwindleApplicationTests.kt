package com.magina.antiswindle.demo

import com.magina.antiswindle.const.Env
import com.magina.antiswindle.res.model.Data
import com.magina.antiswindle.res.model.ItemResource
import com.magina.antiswindle.res.model.service.MainContentService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class AntiSwindleApplicationTests {

    @Test
    fun contextLoads() {
    }

    @Autowired
    var mainContentService: MainContentService? = null

    @Test
    fun testResItemInsert() {
        val itemResource = ItemResource(null,
            "深圳反诈", Data(
                "2020-12-11 17:31:00",
                "赫山公安分局",
                "想轻松赚“快钱”？小心成为不法分子的帮凶!",
                "一分钱不用掏，转转账就能挣钱……你心动了吗？这样看似诱人的网络兼职项目，其实背后蕴藏了极大风险，一不小心就容易落入犯罪分子的圈套，成为其实施违法犯罪的帮凶。目前，全国开展的“断卡”行动，旨在严厉打击、治理、惩戒贩卖“两卡（电话卡和银行卡）”违法犯罪活动，坚决铲除电信网络诈骗犯罪滋生土壤。",
                "千万不要因蝇头小利出售手机卡、银行卡，从而沦为犯罪分子的帮凶，请身份证有遗失经历、前期电话卡或者银行卡有异常情况的，一定要抽空去查询本人名下是否有不知情的电话卡或者银行卡存在，以免导致个人信用、财产双重受损，甚至被卷入刑事案件。对于长期未使用的银行卡应及时注销，不得转让、出借",
                "${Env.BASE_DIR}8f7N5lt1lx07IGXucdKo01041200AOiX0E010.mp4", "blank"
            )
        )
        val addResResult = mainContentService!!.addRes(itemResource)
        assert(addResResult > 0)

    }

}
