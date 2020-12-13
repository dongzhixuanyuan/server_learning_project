package com.magina.antiswindle.res.model

/**
 * @description 文件描述
 *
 * @author magina (735106520@qq.com)
 * @date 2020/12/13 5:28 下午
 */
class ItemResource(
   var id: Int?,
   var source_account: String?,
   var data: Data?
) {
    constructor() : this(null, null, null)
}

class Data(
   var timestamp: String?,
   var source: String?,
   var title: String?,
   var detail_content: String?,
   var comment: String?,
   var video: String?,
   var image: String?
) {
    constructor() : this(null, null, null, null, null, null, null)
}

//{
//    "source_account": "深圳反诈",
//    "data": {
//    "timestamp": "2020-12-11 17:31:00",
//    "source": "赫山公安分局",
//    "title": "想轻松赚“快钱”？小心成为不法分子的帮凶!",
//    "detail_content": "一分钱不用掏，转转账就能挣钱……你心动了吗？这样看似诱人的网络兼职项目，其实背后蕴藏了极大风险，一不小心就容易落入犯罪分子的圈套，成为其实施违法犯罪的帮凶。目前，全国开展的“断卡”行动，旨在严厉打击、治理、惩戒贩卖“两卡（电话卡和银行卡）”违法犯罪活动，坚决铲除电信网络诈骗犯罪滋生土壤。",
//    "comment": "千万不要因蝇头小利出售手机卡、银行卡，从而沦为犯罪分子的帮凶，请身份证有遗失经历、前期电话卡或者银行卡有异常情况的，一定要抽空去查询本人名下是否有不知情的电话卡或者银行卡存在，以免导致个人信用、财产双重受损，甚至被卷入刑事案件。对于长期未使用的银行卡应及时注销，不得转让、出借",
//    "video": "",
//    "image": ""
//}
//}
