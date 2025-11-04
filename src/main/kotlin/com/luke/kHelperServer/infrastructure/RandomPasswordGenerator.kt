package com.luke.kHelperServer.infrastructure

import com.luke.kHelperServer.application.auth.auto_register.required_port.PasswordGenerator
import com.luke.kHelperServer.domain.account.PASSWORD_MAX_LENGTH
import com.luke.kHelperServer.domain.account.PASSWORD_MIN_LENGTH
import org.springframework.stereotype.Component
import java.lang.Integer.min
import java.security.SecureRandom
import kotlin.math.max

@Component
class RandomPasswordGenerator : PasswordGenerator {
    private val lowerChars = "abcdefghijklmnopqrstuvwxyz"
    private val upperChars = lowerChars.uppercase()
    private val digit = "0123456789"
    private val specialChars = "!@#?"

    private val passwordChars = lowerChars + upperChars + digit + specialChars
    private val randomGenerator = SecureRandom()

    override fun generatePassword(length: Int?): String {
        val passwordLength = length?.let {
            min(PASSWORD_MAX_LENGTH, max(it, PASSWORD_MIN_LENGTH))
        } ?: PASSWORD_MIN_LENGTH

        // 최소한 각 문자 유형에서 하나씩 포함되도록 보장
        val passwordBuilder = StringBuilder(passwordLength)
        passwordBuilder.append(getRandomChar(lowerChars)) // 소문자
        passwordBuilder.append(getRandomChar(upperChars)) // 대문자
        passwordBuilder.append(getRandomChar(digit)) // 숫자
        passwordBuilder.append(getRandomChar(specialChars)) // 특수 문자

        // 나머지 길이를 전체 문자 세트에서 랜덤하게 채움
        for (i in 4 .. passwordLength) {
            passwordBuilder.append(getRandomChar(passwordChars))
        }

        // 생성된 비밀번호 문자열을 섞어 무작위성을 높임
        return passwordBuilder.toString().toCharArray().apply { shuffle() }.joinToString("")
    }

    private fun getRandomChar(characterSet: String): Char {
        val randomIndex = randomGenerator.nextInt(characterSet.length)
        return characterSet[randomIndex]
    }
}
