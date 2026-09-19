package com.example.smarthomegitops.domain

class DeceptionDetector {

    fun analyze(text: String): Int {

        val lowerText = text.lowercase()

        var score = 0

        val patterns = listOf(
            Regex("""\bcritical\b"""),
            Regex("""\bimminent\b"""),
            Regex("""\bdo not lower\b"""),
            Regex("""\bfreez(e|ing|en)\b"""),
            Regex("""\bvalve\s+(failure|failed|malfunction)\b"""),
            Regex("""\b(electrical|electric)\s+(failure|fault|issue)\b"""),
            Regex("""\bstructural\s+crack(s)?\b"""),
            Regex("""\bcompression\s+blowout\b""")
        )

        for (pattern in patterns) {
            if (pattern.containsMatchIn(lowerText)) {
                score += 10
            }
        }

        return score.coerceIn(0, 100)
    }
}