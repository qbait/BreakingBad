package io.digitalheart.breakingbad.utils

import org.simmetrics.metrics.StringMetrics
import java.text.Normalizer

fun String.isMatching(searchText: String): Boolean {
    val text = toLowerCase().stripAccents()
    val constraint = searchText.toLowerCase().stripAccents()

    return when {
        text.contains(constraint) -> true
        text.firstLetters().contains(constraint) -> true
        else -> StringMetrics.levenshtein().compare(text, constraint) > 0.5
    }
}

private fun String.stripAccents(): String {
    val string = Normalizer.normalize(this, Normalizer.Form.NFD)
    return Regex("\\p{InCombiningDiacriticalMarks}+").replace(string, "")
}

private fun String.firstLetters() = split(" ").joinToString("") { it.take(1) }