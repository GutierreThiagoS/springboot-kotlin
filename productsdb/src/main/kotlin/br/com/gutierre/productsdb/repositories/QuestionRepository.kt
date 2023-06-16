package br.com.gutierre.productsdb.repositories

import br.com.gutierre.productsdb.model.Question
import org.springframework.data.jpa.repository.JpaRepository

interface QuestionRepository: JpaRepository<Question, Long?> {
}