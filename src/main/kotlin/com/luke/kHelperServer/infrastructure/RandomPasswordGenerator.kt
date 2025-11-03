package com.luke.kHelperServer.infrastructure

import org.springframework.stereotype.Component
import java.security.SecureRandom

@Component
class RandomPasswordGenerator : PasswordGenerator {
    private val lowerChars = "abcdefghijklmnopqrstuvwxyz"
    private val upperChars = lowerChars.uppercase()
    private val digit = "0123456789"
    private val specialChars = "!@#?"

    private val passwordChars = lowerChars + upperChars + digit + specialChars
    private val randomGenerator = SecureRandom()

    val defaultPasswordLength = 12

    override fun generatePassword(length: Int?): String {
        val passwordLength = length ?: defaultPasswordLength

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
