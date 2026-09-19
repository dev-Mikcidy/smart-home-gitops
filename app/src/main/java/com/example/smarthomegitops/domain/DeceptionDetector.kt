package com.example.smarthomegitops.domain

class DeceptionDetector {

    fun analyze(text: String): Int {

        val lowerText = text.lowercase()

        var score = 0

        val patterns = listOf(
            Regex("""\bcritical\b|\burgent\b|\bsevere\b"""),
            Regex("""\bimminent\b|\babout\s+to\b|\bimmediately\b"""),
            Regex("""\bdo not lower\b|\bdon't lower\b|\bdo not reduce\b"""),
            Regex("""\bfreez(e|ing|en)\b|\bfreezing\s+conditions?\b"""),
            Regex("""\bvalve\s+(failure|failed|malfunction|malfunctioning)\b"""),
            Regex("""\b(electrical|electric)\s+(failure|fault|issue|problem|emergency)\b"""),
            Regex("""\b(structural\s+crack(s)?|major\s+crack|structural\s+damage)\b"""),
            Regex("""\b(compression\s+blowout|compressor\s+failure)\b""")
        )

        for (pattern in patterns) {
            if (pattern.containsMatchIn(lowerText)) {
                score += 10
            }
        }

        return score.coerceIn(0, 100)
    }
}