package ch.frankel.sonarqube.kotlin.plugin

import ch.frankel.sonarqube.kotlin.plugin.Kotlin.Companion.KEY
import ch.frankel.sonarqube.kotlin.plugin.KotlinChecks.Companion.REPOSITORY_KEY
import org.sonar.api.server.rule.RulesDefinition
import org.sonar.api.server.rule.RulesDefinition.Context
import org.sonar.api.server.rule.RulesDefinition.NewExtendedRepository

class KotlinRulesDefinition : RulesDefinition {

    override fun define(context: Context) {
        context.createRepository(REPOSITORY_KEY, "Kotlin").apply {
            setName("SonarAnalyzer")
            KotlinChecks.checks.forEach {
                createRule(it)
            }
            done()
        }
    }

    private fun NewExtendedRepository.createRule(check: KotlinCheck) = createRule(check.key).apply {
        with(check) {
            setName(name)
            setHtmlDescription(htmlDescription)
            setType(type)
        }
    }
}

