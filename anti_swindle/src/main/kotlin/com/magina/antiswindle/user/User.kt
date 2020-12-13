package com.magina.antiswindle.user

/**
 * @description User表
 *
 * @author
 * @date 2020/12/7 5:07 下午
 */
class User(
    var id: Int?,
    var name: String,
    var avatar_url: String?,
    var phone: String,
    var password: String,
    var created_at: String?,
    var updated_at: String?
)

class UserOauths(
    var id: Int,
    var user_id: Int,
    var oauth_type: String,
    var oauth_id: String,
    var unionid: String,
    var credential: String,
)