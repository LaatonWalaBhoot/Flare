package io.pantheon.flare

import com.squareup.kotlinpoet.*
import java.io.File

fun generateEventPropertyMappings() {
    // using kotlinpoet here

    val typeBuilder =  TypeSpec.classBuilder("AnalyticsPayload")
        .addModifiers(KModifier.DATA)

    val funSpecBuilder = FunSpec.constructorBuilder()

    constants.forEach {
        funSpecBuilder.addParameter(it.snakeToCamelCase(), String::class)
        typeBuilder.addProperty(PropertySpec.builder(it.snakeToCamelCase(), String::class)
            .initializer(it.snakeToCamelCase())
            .build())
    }

    FileSpec.builder("", "AnalyticsPayload")
        .addType(typeBuilder.primaryConstructor(funSpecBuilder.build()).build())
        .build()
        .writeTo(File("flare/src/main/java/io/pantheon/flare"))

}

fun String.snakeToCamelCase(): String {
    val pattern = "_[a-z]".toRegex()
    return replace(pattern) { it.value.last().uppercase() }
}

